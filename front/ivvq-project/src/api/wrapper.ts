import { AxiosUserControllerClient, User, RestResponse } from './endpoints'

const baseUrl = "localhost:8080/api/"

export type Result<T, E> = Ok<T> | Err<E>

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
    return p.then(v => Ok(v.data)).catch(e => Err(e))
}


export class UserApi {
    private static client = new AxiosUserControllerClient(baseUrl)

    static register(user: User) {
        return moveError(this.client.save(user))
    }
}
