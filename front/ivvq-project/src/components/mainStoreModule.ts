import { VuexModule, Module, Mutation, Action, getModule } from "vuex-module-decorators";
import store from '@/store';
import { User } from '@/api/endpoints';
import { Result, Err } from '@/api/wrapper';

export const moduleName = "Main";
export type stateType = {
    user?: User,
    shoppingCart: any[],
    error?: any,
};

@Module({
    store,
    namespaced: true,
    name: moduleName,
    dynamic: true,
})
export class Register extends VuexModule {
    public mState: stateType = {
        user: undefined,
        shoppingCart: [],
        error: undefined,
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
    public async login({ user, loginf }: { user: User; loginf: (user: User) => Promise<Result<User, unknown>> }) {
        const result = await loginf(user);
        switch (result.type) {
            case "Err": this.setSate({ ...this.getState, error: result.value }); break;
            case "Ok": this.setSate({ ...this.getState )
        }
    }

    @Action({ rawError: true })
    public cleanError() {
        this.setSate({ ...this.getState, error: undefined });
    }
}
export const RegisterModule = getModule(Register)