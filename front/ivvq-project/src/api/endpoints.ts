/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.22.595 on 2020-05-27 19:58:25.

export interface Order extends Serializable {
    orderId: number;
    products: ProductOrdered[];
    buyerEmail: string;
    buyerName: string;
    buyerPhone: string;
    buyerAddress: string;
    orderAmount: number;
    orderStatus: string;
    createTime: Date;
    updateTime: Date;
}

export interface ResponseEntity<T> extends HttpEntity<T> {
    statusCode: HttpStatus;
    statusCodeValue: number;
}

export interface Product extends Serializable {
    productId: string;
    productName: string;
    productPrice: number;
    productStock: number;
    productDescription: string;
    productIcon: string;
}

export interface ItemForm {
    quantity: number;
    productId: string;
}

export interface LuhnAlgorithm {
    cartNum: string;
}

export interface ShoppingCart extends Serializable {
    cartId: number;
    products: ProductOrdered[];
}

export interface ProductOrdered {
    id: number;
    productId: string;
    productName: string;
    productDescription: string;
    productIcon: string;
    productPrice: number;
    productStock: number;
    count: number;
}

export interface AuthentificationForm {
    username: string;
    password: string;
}

export interface User extends Serializable {
    id: number;
    email: string;
    password: string;
    name: string;
    phone: string;
    address: string;
    active: boolean;
    role: string;
}

export interface Serializable {
}

export interface HttpEntity<T> {
    headers: { [index: string]: any };
    body: T;
}

export interface HttpClient<O> {

    request<R>(requestConfig: { method: string; url: string; queryParams?: any; data?: any; copyFn?: (data: R) => R; options?: O; }): RestResponse<R>;
}

export class ProductControllerClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP GET /product
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ProductController.findAllProduct
     */
    findAllProduct(options?: O): RestResponse<Product[]> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`product`, options: options });
    }

    /**
     * HTTP GET /product/{productId}
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ProductController.showOneProduct
     */
    showOneProduct(productId: string, options?: O): RestResponse<Product> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`product/${productId}`, options: options });
    }

    /**
     * HTTP POST /seller/product/new
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ProductController.createProduct
     */
    createProduct(product: Product, options?: O): RestResponse<ResponseEntity<any>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`seller/product/new`, data: product, options: options });
    }

    /**
     * HTTP DELETE /seller/product/{id}/delete
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ProductController.deleteProduct
     */
    deleteProduct(id: string, options?: O): RestResponse<ResponseEntity<any>> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`seller/product/${id}/delete`, options: options });
    }

    /**
     * HTTP PUT /seller/product/{id}/edit
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ProductController.editProduct
     */
    editProduct(id: string, product: Product, options?: O): RestResponse<ResponseEntity<any>> {
        return this.httpClient.request({ method: "PUT", url: uriEncoding`seller/product/${id}/edit`, data: product, options: options });
    }
}

export class UserControllerClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP POST /login
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.UserController.login
     */
    login(authentificationForm: AuthentificationForm, options?: O): RestResponse<any> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`login`, data: authentificationForm, options: options });
    }

    /**
     * HTTP POST /register
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.UserController.save
     */
    save(user: User, options?: O): RestResponse<User> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`register`, data: user, options: options });
    }
}

export class HelloControllerClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP GET /
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.HelloController.index
     */
    index(options?: O): RestResponse<string> {
        return this.httpClient.request({ method: "GET", url: uriEncoding``, options: options });
    }
}

export class OrderControllerClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP GET /order
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.OrderController.orderHistoric
     */
    orderHistoric(options?: O): RestResponse<Order[]> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`order`, options: options });
    }

    /**
     * HTTP PATCH /order/cancel/{id}
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.OrderController.cancelOrder
     */
    cancelOrder(id: number, options?: O): RestResponse<Order> {
        return this.httpClient.request({ method: "PATCH", url: uriEncoding`order/cancel/${id}`, options: options });
    }

    /**
     * HTTP PATCH /order/finish/{id}
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.OrderController.finishOrder
     */
    finishOrder(id: number, options?: O): RestResponse<Order> {
        return this.httpClient.request({ method: "PATCH", url: uriEncoding`order/finish/${id}`, options: options });
    }

    /**
     * HTTP GET /order/{id}
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.OrderController.showOneOrder
     */
    showOneOrder(id: number, options?: O): RestResponse<ResponseEntity<any>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`order/${id}`, options: options });
    }
}

export class ShoppingCartControllerClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP POST /cart
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ShoppingCartController.finalCart
     */
    finalCart(productInOrders: ProductOrdered[], options?: O): RestResponse<ShoppingCart> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`cart`, data: productInOrders, options: options });
    }

    /**
     * HTTP GET /cart
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ShoppingCartController.getCart
     */
    getCart(options?: O): RestResponse<ShoppingCart> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`cart`, options: options });
    }

    /**
     * HTTP POST /cart/add
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ShoppingCartController.addItemToCart
     */
    addItemToCart(form: ItemForm, options?: O): RestResponse<boolean> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`cart/add`, data: form, options: options });
    }

    /**
     * HTTP POST /cart/checkout
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ShoppingCartController.checkoutCart
     */
    checkoutCart(luhnAlgorithm: LuhnAlgorithm, options?: O): RestResponse<ResponseEntity<any>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`cart/checkout`, data: luhnAlgorithm, options: options });
    }

    /**
     * HTTP DELETE /cart/{itemId}
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ShoppingCartController.deleteItem
     */
    deleteItem(itemId: string, options?: O): RestResponse<void> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`cart/${itemId}`, options: options });
    }

    /**
     * HTTP PUT /cart/{itemId}
     * Java method: com.teamrocket.projetdevop.ivvqproject.controller.ShoppingCartController.modifyItem
     */
    modifyItem(itemId: string, quantity: number, options?: O): RestResponse<ProductOrdered> {
        return this.httpClient.request({ method: "PUT", url: uriEncoding`cart/${itemId}`, data: quantity, options: options });
    }
}

export type RestResponse<R> = Promise<Axios.GenericAxiosResponse<R>>;

export type HttpStatus = "CONTINUE" | "SWITCHING_PROTOCOLS" | "PROCESSING" | "CHECKPOINT" | "OK" | "CREATED" | "ACCEPTED" | "NON_AUTHORITATIVE_INFORMATION" | "NO_CONTENT" | "RESET_CONTENT" | "PARTIAL_CONTENT" | "MULTI_STATUS" | "ALREADY_REPORTED" | "IM_USED" | "MULTIPLE_CHOICES" | "MOVED_PERMANENTLY" | "FOUND" | "MOVED_TEMPORARILY" | "SEE_OTHER" | "NOT_MODIFIED" | "USE_PROXY" | "TEMPORARY_REDIRECT" | "PERMANENT_REDIRECT" | "BAD_REQUEST" | "UNAUTHORIZED" | "PAYMENT_REQUIRED" | "FORBIDDEN" | "NOT_FOUND" | "METHOD_NOT_ALLOWED" | "NOT_ACCEPTABLE" | "PROXY_AUTHENTICATION_REQUIRED" | "REQUEST_TIMEOUT" | "CONFLICT" | "GONE" | "LENGTH_REQUIRED" | "PRECONDITION_FAILED" | "PAYLOAD_TOO_LARGE" | "REQUEST_ENTITY_TOO_LARGE" | "URI_TOO_LONG" | "REQUEST_URI_TOO_LONG" | "UNSUPPORTED_MEDIA_TYPE" | "REQUESTED_RANGE_NOT_SATISFIABLE" | "EXPECTATION_FAILED" | "I_AM_A_TEAPOT" | "INSUFFICIENT_SPACE_ON_RESOURCE" | "METHOD_FAILURE" | "DESTINATION_LOCKED" | "UNPROCESSABLE_ENTITY" | "LOCKED" | "FAILED_DEPENDENCY" | "TOO_EARLY" | "UPGRADE_REQUIRED" | "PRECONDITION_REQUIRED" | "TOO_MANY_REQUESTS" | "REQUEST_HEADER_FIELDS_TOO_LARGE" | "UNAVAILABLE_FOR_LEGAL_REASONS" | "INTERNAL_SERVER_ERROR" | "NOT_IMPLEMENTED" | "BAD_GATEWAY" | "SERVICE_UNAVAILABLE" | "GATEWAY_TIMEOUT" | "HTTP_VERSION_NOT_SUPPORTED" | "VARIANT_ALSO_NEGOTIATES" | "INSUFFICIENT_STORAGE" | "LOOP_DETECTED" | "BANDWIDTH_LIMIT_EXCEEDED" | "NOT_EXTENDED" | "NETWORK_AUTHENTICATION_REQUIRED";

function uriEncoding(template: TemplateStringsArray, ...substitutions: any[]): string {
    let result = "";
    for (let i = 0; i < substitutions.length; i++) {
        result += template[i];
        result += encodeURIComponent(substitutions[i]);
    }
    result += template[template.length - 1];
    return result;
}


// Added by 'AxiosClientExtension' extension

import axios from "axios";
import * as Axios from "axios";

declare module "axios" {
    export interface GenericAxiosResponse<R> extends Axios.AxiosResponse {
        data: R;
    }
}

class AxiosHttpClient implements HttpClient<Axios.AxiosRequestConfig> {

    constructor(private axios: Axios.AxiosInstance) {
    }

    request<R>(requestConfig: { method: string; url: string; queryParams?: any; data?: any; copyFn?: (data: R) => R; options?: Axios.AxiosRequestConfig; }): RestResponse<R> {
        function assign(target: any, source?: any) {
            if (source != undefined) {
                for (const key in source) {
                    if (source.hasOwnProperty(key)) {
                        target[key] = source[key];
                    }
                }
            }
            return target;
        }

        const config: Axios.AxiosRequestConfig = {};
        config.method = requestConfig.method as typeof config.method;  // `string` in axios 0.16.0, `Method` in axios 0.19.0
        config.url = requestConfig.url;
        config.params = requestConfig.queryParams;
        config.data = requestConfig.data;
        assign(config, requestConfig.options);
        const copyFn = requestConfig.copyFn;

        const axiosResponse = this.axios.request(config);
        return axiosResponse.then(axiosResponse => {
            if (copyFn && axiosResponse.data) {
                (axiosResponse as any).originalData = axiosResponse.data;
                axiosResponse.data = copyFn(axiosResponse.data);
            }
            return axiosResponse;
        });
    }
}

export class AxiosProductControllerClient extends ProductControllerClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}

export class AxiosUserControllerClient extends UserControllerClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}

export class AxiosHelloControllerClient extends HelloControllerClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}

export class AxiosOrderControllerClient extends OrderControllerClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}

export class AxiosShoppingCartControllerClient extends ShoppingCartControllerClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}
