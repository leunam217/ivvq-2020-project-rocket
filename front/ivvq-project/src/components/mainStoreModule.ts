import { VuexModule, Module, Mutation, Action, getModule } from "vuex-module-decorators";
import store from '@/store';
import { JwtResponse, AuthentificationForm, Product } from '@/api/endpoints';
import { Result, Err, ProductApi } from '@/api/wrapper';
import router from '@/router';

export const moduleName = "Main";
export const tokenKey = "rocketKey"
export type stateType = {
    jwtResponse?: JwtResponse;
    authForm: AuthentificationForm;
    shoppingCart: { quantity: number; product: Product; show: boolean }[];
    error?: any;
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
    };

    /*login logic */
    @Action({ rawError: true })
    public async login({ authForm, loginf }:
        { authForm: AuthentificationForm; loginf: (authForm: AuthentificationForm) => Promise<Result<JwtResponse, unknown>> }) {
        const result = await loginf(authForm);
        switch (result.type) {
            case "Err": this.setSate({ ...this.getState, error: result.value }); break;
            case "Ok": this.setSate({ ...this.getState, jwtResponse: result.value }); this.setTokenToLocalStorage(result.value); router.push("/")
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
        return this.mState.shoppingCart;
    }
    /* general stuff */
    @Action({ rawError: true })
    public getTokenFromLocalStorage() {
        if (localStorage.getItem(tokenKey) !== null)
            this.setSate({ ...this.getState, jwtResponse: JSON.parse(localStorage.getItem(tokenKey) as string) })
    }

    @Action({ rawError: true })
    public setTokenToLocalStorage(jwtResponse: Record<string, any>) {
        localStorage.setItem(tokenKey, JSON.stringify(jwtResponse));
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