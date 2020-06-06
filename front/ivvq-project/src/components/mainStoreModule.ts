import { VuexModule, Module, Mutation, Action, getModule } from "vuex-module-decorators";
import store from '@/store';
import { JwtResponse, AuthentificationForm, Product, ProductOrdered, Order } from '@/api/endpoints';
import { Result, Err, ProductApi, ShoppingCartApi, OrderApi } from '@/api/wrapper';
import router from '@/router';

export const moduleName = "Main";
export const tokenKey = "rocketKey"
export type stateType = {
    jwtResponse?: JwtResponse;
    authForm: AuthentificationForm;
    shoppingCart: { quantity: number; product: Product; show: boolean }[];
    error?: any;
    success?: string;
    cardNumber: string;
    previousOrders: Order[];
};

@Module({
    store,
    namespaced: true,
    name: moduleName,
    dynamic: true,
})
export class MainModule2 extends VuexModule {
    public mState: stateType = {
        jwtResponse: undefined,
        shoppingCart: [],
        error: undefined,
        authForm: { password: "", username: "" },
        cardNumber: "",
        success: undefined,
        previousOrders: []
    };

    /*login logic */
    @Action({ rawError: true })
    public async login({ authForm, loginf }:
        { authForm: AuthentificationForm; loginf: (authForm: AuthentificationForm) => Promise<Result<JwtResponse, unknown>> }) {
        const result = await loginf(authForm);
        switch (result.type) {
            case "Err": this.setSate({ ...this.getState, error: result.value }); break;
            case "Ok": this.setSate({ ...this.getState, jwtResponse: result.value }); router.push("/")
        }
    }


    /* home logic */
    @Action({ rawError: true })
    public async loadProducts() {
        if (this.getState.jwtResponse?.token === undefined) {
            this.showError("You are not connected")
        }
        const result = await ProductApi.getProducts(this.getState.jwtResponse?.token as string)
        switch (result.type) {
            case "Err": this.showError(result.value); break;
            case "Ok": this.loadProductsCompleted(result.value);
        }
    }

    @Mutation
    public loadProductsCompleted(products: Product[]) {
        this.mState.shoppingCart = products.map(product => ({ product, quantity: 0, show: false }))
    }

    @Mutation
    public addOneProduct(id: any) {
        const v = this.mState.shoppingCart.find(v => id === v.product.productId);
        if (v === undefined) {
            this.showError("Unknown product");
            return;
        }
        const { quantity, product } = v
        if (quantity >= product.productStock) {
            this.showError(
                `There are not more ${product.productName} in sotck`
            );
            return;
        }
        this.mState.shoppingCart = this.mState.shoppingCart
            .filter(v => v.product.productId !== id)
            .concat({ ...v, quantity: quantity + 1 })
    }

    @Mutation
    public removeOneProduct(id: any) {
        const v = this.mState.shoppingCart.find(v => id === v.product.productId);
        if (v === undefined) {
            this.showError("Unknown product");
            return;
        }
        const { quantity } = v
        if (quantity <= 0)
            return;
        this.mState.shoppingCart = this.mState.shoppingCart
            .filter(v => v.product.productId !== id)
            .concat({ ...v, quantity: quantity - 1 })
    }

    @Mutation
    public deleteFromShoppingCart(id: any) {
        const v = this.mState.shoppingCart.find(v => id === v.product.productId);
        if (v === undefined) {
            this.showError("Unknown product");
            return;
        }
        this.mState.shoppingCart = this.mState.shoppingCart
            .filter(v => v.product.productId !== id)
            .concat({ ...v, show: false, quantity: 0 })
    }

    @Mutation
    public addToShoppingCart(id: any) {
        const v = this.mState.shoppingCart.find(v => id === v.product.productId);
        if (v === undefined) {
            this.showError("Unknown product");
            return;
        }
        this.mState.shoppingCart = this.mState.shoppingCart
            .filter(v => v.product.productId !== id)
            .concat({ ...v, show: true })
    }

    get shoppingCart() {
        return this.mState.shoppingCart.sort((a, b) => a.product.productName.localeCompare(b.product.productName));
    }

    /* order historic logic */

    @Action
    public async loadOrderHistory() {
        if (this.getState.jwtResponse?.token === undefined) {
            this.showError("You are not connected")
        }
        const result = await OrderApi.getOrderHistoric(this.getState.jwtResponse?.token as string)
        switch (result.type) {
            case "Err": this.showError(result.value); break;
            case "Ok": this.loadOrderHistoricCompleted(result.value);
        }
    }

    @Mutation
    public loadOrderHistoricCompleted(orders: Order[]) {
        this.mState.previousOrders = orders;
    }

    /* payment */
    @Action
    public async pay() {
        const products: ProductOrdered[] = this.shoppingCart
            .filter(v => v.show && v.quantity >= 1)
            .map(v => ({
                count: v.quantity,
                id: 0,
                ...v.product
            }));
        const token = this.getState.jwtResponse?.token;
        if (!token) return;
        const cartNum = this.getState.cardNumber;
        const result = await ShoppingCartApi.pay(token, products, { cartNum })
        switch (result.type) {
            case "Err": this.showError(`An error happened : ${result.value}`); break;
            case "Ok": this.showSuccess(" Your purchase happened!"); router.push("/")

        }
    }

    /* general stuff */
    @Mutation
    public getStateFromLocalStorage() {
        if (localStorage.getItem(tokenKey))
            this.mState = JSON.parse(localStorage.getItem(tokenKey) as string)
    }

    @Mutation
    public setStateToLocalStorage() {
        localStorage.setItem(tokenKey, JSON.stringify(this.mState));
    }

    @Mutation
    public cleanError() {
        this.mState.error = undefined;
    }

    @Mutation
    public showError(error: any) {
        this.mState.error = error;
    }

    @Mutation
    public showSuccess(s: any) {
        this.mState.success = s;
    }

    @Mutation
    public cleanSuccess() {
        this.mState.success = undefined;
    }

    @Mutation
    public setSate(newSate: stateType): void {
        this.mState = newSate;
    }

    @Action({ rawError: true })
    public updateSate(newSate: stateType) {
        this.setSate(newSate)
    }
    get getState() {
        return this.mState;
    }
}
export const MainModule = getModule(MainModule2)