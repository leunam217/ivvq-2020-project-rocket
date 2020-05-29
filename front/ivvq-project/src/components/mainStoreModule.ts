import { VuexModule, Module, Mutation, Action, getModule } from "vuex-module-decorators";
import store from '@/store';
import { JwtResponse, AuthentificationForm, Product as ProductType } from '@/api/endpoints';
import { Result, Err, ProductApi } from '@/api/wrapper';
import router from '@/router';

export const moduleName = "Main";
export const tokenKey = "rocketKey"
export type stateType = {
    jwtResponse?: JwtResponse;
    authForm: AuthentificationForm;
    shoppingCart: { quantity: number; product: ProductType }[];
    error?: any;
    products: ProductType[];
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
        products: []
    };

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

    @Action({ rawError: true })
    public async login({ authForm, loginf }:
        { authForm: AuthentificationForm; loginf: (authForm: AuthentificationForm) => Promise<Result<JwtResponse, unknown>> }) {
        const result = await loginf(authForm);
        switch (result.type) {
            case "Err": this.setSate({ ...this.getState, error: result.value }); break;
            case "Ok": this.setSate({ ...this.getState, jwtResponse: result.value }); this.setTokenToLocalStorage(result.value.token); router.push("/")
        }
    }

    @Action({ rawError: true })
    public cleanError() {
        this.setSate({ ...this.getState, error: undefined });
    }

    @Action({ rawError: true })
    public async loadProducts() {
        if (this.getState.jwtResponse?.token === undefined) {
            this.setSate({ ...this.getState, error: "You are not connected" })
        }
        const result = await ProductApi.getProducts(this.getState.jwtResponse?.token as string)
        switch (result.type) {
            case "Err": this.setSate({ ...this.getState, error: result.value }); break;
            case "Ok": this.setSate({ ...this.getState, products: result.value });
        }
    }

    @Action({ rawError: true })
    public getTokenFromLocalStorage() {
        localStorage.getItem(tokenKey)
    }

    @Action({ rawError: true })
    public setTokenToLocalStorage(token: string) {
        localStorage.setItem(tokenKey, token);
    }
}
export const MainModule = getModule(MainModule2)