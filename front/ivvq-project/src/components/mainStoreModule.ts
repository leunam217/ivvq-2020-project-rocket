import { VuexModule, Module, Mutation, Action, getModule } from "vuex-module-decorators";
import store from '@/store';
import { JwtResponse, AuthentificationForm, Product, ProductOrdered, Order } from '@/api/endpoints';
import { Result, Err, ProductApi, ShoppingCartApi, OrderApi, Mode, Role } from '@/api/wrapper';
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

function getMode(this: MainModule2): Mode {
    switch (this.mState.jwtResponse?.role as Role | string) {
        case "ROLE_CUSTOMER":
            return "UserMode";
        case "ROLE_SELLER":
            return "AdminMode";
    }
    return "UserMode";
}

/* luhn_checksum
 * Implement the Luhn algorithm to calculate the Luhn check digit.
 * Return the check digit.
 */
function luhnChecksum(code: any) {
    const len = code.length
    const parity = len % 2
    let sum = 0
    for (let i = len - 1; i >= 0; i--) {
        let d = parseInt(code.charAt(i))
        if (i % 2 == parity) { d *= 2 }
        if (d > 9) { d -= 9 }
        sum += d
    }
    return sum % 10
}

/* luhn_caclulate
 * Return a full code (including check digit), from the specified partial code (without check digit).
 */
function luhnCaclulate(partcode: any) {
    const checksum = luhnChecksum(partcode + "0")
    return checksum == 0 ? 0 : 10 - checksum
}

/* luhn_validate
 * Return true if specified code (with check digit) is valid.
 */
function luhnValidate(fullcode: any) {
    return luhnChecksum(fullcode) == 0
}

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
            this.showError("You are not connected"); return
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
        if (quantity >= product.productStock && getMode.bind(this)() === "UserMode") {
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
        if ((quantity <= 0 && getMode.bind(this)() === "UserMode") ||
            (quantity + v.product.productStock <= 0 && getMode.bind(this)() === "AdminMode"))
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

    /* this function is for admin home */
    @Action
    public async addToStore(id: any) {
        const v = this.mState.shoppingCart.find(v => id === v.product.productId);
        if (v === undefined) {
            this.showError("Unknown product");
            return;
        }
        const token = this.getState.jwtResponse?.token;
        if (token === undefined) {
            this.showError("You are not connected");
            return;
        }
        const modifiedProduct: Product = { ...v.product, productStock: v.quantity + v.product.productStock }
        const result = await ProductApi.modifyProduct(token, modifiedProduct)
        switch (result.type) {
            case "Err": this.showError(result.value); break;
            case "Ok": this.addToStoreCompleted(); this.loadProducts();
        }
    }

    @Mutation
    public addToStoreCompleted() {
        this.mState.success = "The product has been updated";
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
        if (!luhnValidate(cartNum)) {
            this.showError("Your credit card is not valid according to luhn's algorithm");
            return;
        }
        const result = await ShoppingCartApi.pay(token, products, { cartNum })
        switch (result.type) {
            case "Err": this.showError(`An error happened : ${result.value}`); break;
            case "Ok": this.showSuccess(" Your purchase happened!"); router.push("/")

        }
    }

    /* general stuff */

    @Mutation
    public cleanError() {
        this.mState.error = undefined;
    }

    @Mutation
    public showError(error: any) {
        router.push("/login");
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