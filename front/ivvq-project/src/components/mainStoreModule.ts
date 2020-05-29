import { VuexModule, Module, Mutation, Action, getModule } from "vuex-module-decorators";
import store from '@/store';
import { JwtResponse, AuthentificationForm } from '@/api/endpoints';
import { Result, Err } from '@/api/wrapper';
import router from '@/router';

export const moduleName = "Main";
export type stateType = {
    jwtResponse?: JwtResponse;
    authForm: AuthentificationForm;
    shoppingCart: any[];
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
        authForm: { password: "", username: "" }
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
            case "Ok": this.setSate({ ...this.getState, jwtResponse: result.value }); router.push("/")
        }
    }

    @Action({ rawError: true })
    public cleanError() {
        this.setSate({ ...this.getState, error: undefined });
    }
}
export const MainModule = getModule(MainModule2)