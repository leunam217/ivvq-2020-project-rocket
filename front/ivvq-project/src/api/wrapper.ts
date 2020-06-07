import { AxiosUserControllerClient, User, RestResponse, AxiosProductControllerClient, ProductOrdered, AxiosShoppingCartControllerClient, LuhnAlgorithm, AxiosOrderControllerClient, Product } from './endpoints'
import Axios from 'axios'

const baseUrl = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port

export type Result<T, E> = Ok<T> | Err<E>

// we need this because back-end role is stringly type todo update
export type Role = "ROLE_CUSTOMER" | "ROLE_SELLER"

export type Mode = "UserMode" | "AdminMode";

export type Ok<T> = {
    type: "Ok";
    value: T;
}

export function Ok<T>(value: T): Ok<T> {
    return {
        type: "Ok",
        value
    }
}

export type Err<E> = {
    type: "Err";
    value: E;
}

export function Err<E>(value: E): Err<E> {
    return {
        type: "Err",
        value
    }
}


export function moveError<T>(p: RestResponse<T>) {
    return p.then(v => Ok(v.data)).catch(e => Err(e));
}


export class UserApi {
    private static client = new AxiosUserControllerClient(baseUrl)

    static register(user: User) {
        return moveError(this.client.save(user));
    }

    static login(authForm: { username: string; password: string }) {
        return moveError(this.client.login(authForm));
    }
}

function getAxiosWithAuthHeader(token: string) {
    return Axios.create({ headers: { "Authorization": `Bearer ${token}` } })
}



export class ProductApi {
    static client = (token: string) => new AxiosProductControllerClient(baseUrl, getAxiosWithAuthHeader(token))

    static getProducts(token: string) {
        return moveError(this.client(token).findAllProduct());
    }

    static modifyProduct(token: string, productToUpdate: Product) {
        return moveError(this.client(token).editProduct(productToUpdate.productId, productToUpdate))
    }
}

export class ShoppingCartApi {
    static client = (token: string) => new AxiosShoppingCartControllerClient(baseUrl, getAxiosWithAuthHeader(token))

    static pay(token: string, products: ProductOrdered[], creditCard: LuhnAlgorithm) {
        return moveError(this.client(token).finalCart(products)
            .then(() => this.client(token).checkoutCart(creditCard)))
    }
}

export class OrderApi {
    static client = (token: string) => new AxiosOrderControllerClient(baseUrl, getAxiosWithAuthHeader(token))

    static async getOrderHistoric(token: string) {
        console.log("lol");
        const orders = await this.client(token).orderHistoric().then(v => Ok(v.data)).catch(e => Err(e));
        if (orders.type === "Err")
            return orders;
        return Promise.all(orders.value.map(o => this.client(token).showOneOrder(o.orderId)))
            .then(v => Ok(v.map(va => va.data)))
            .catch(e => Err(e))
    }


}
