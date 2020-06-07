import { VuexModule, Module, Mutation, Action, getModule } from "vuex-module-decorators";
import store from '@/store';
import { User } from '@/api/endpoints';
import { Result, Err } from '@/api/wrapper';
import router from '@/router';

export const moduleName = "Register";
export type stateType = {
    email: string;
    password: string;
    name: string;
    phone: string;
    address: string;
    error?: any;
    registred: boolean;
    role: string;
};

@Module({
    store,
    namespaced: true,
    name: moduleName,
    dynamic: true,
})
export class Register extends VuexModule {
    public mState: stateType = {
        email: "",
        password: "",
        name: "",
        phone: "",
        address: "",
        error: undefined,
        registred: false,
        role: ""
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
    public async register({ user, registerf }: { user: User; registerf: (user: User) => Promise<Result<User, unknown>> }) {
        const result = await registerf(user);
        switch (result.type) {
            case "Err": this.setSate({ ...this.getState, error: result.value }); break;
            case "Ok": this.setSate({ ...this.getState, registred: true }); router.push("Login");
        }
    }

    @Action({ rawError: true })
    public cleanError() {
        this.setSate({ ...this.getState, error: undefined });
    }
}
export const RegisterModule = getModule(Register)