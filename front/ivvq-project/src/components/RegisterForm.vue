<template>
  <v-card
    width="400"
    class="ma-3"
  >
    <v-card-title class="pb-0">
      <h1>Register</h1>
    </v-card-title>
    <v-card-text>
      <v-form>
        <v-text-field
          label="E-mail"
          prepend-icon="mdi-email"
          :value="state.email"
          @input="(v) => updateState({... state,email:v})"
        ></v-text-field>
        <v-text-field
          label="Enter your password"
          type="password"
          prepend-icon="mdi-key"
          :value="state.password"
          @input="(v) => updateState({...state,password:v})"
        ></v-text-field>
        <v-text-field
          label="Name"
          prepend-icon="mdi-account"
          :value="state.name"
          @input="(v) => updateState({...state,name:v})"
        />
        <v-text-field
          label="Phone"
          prepend-icon="mdi-phone"
          :value="state.phone"
          @input="(v) => updateState({...state,phone:v})"
        />
        <v-text-field
          label="Address"
          prepend-icon="mdi-home"
          :value="state.address"
          @input="(v) => updateState({...state,address:v})"
        />
        <v-text-field
          label="Role (leave empty if client)"
          prepend-icon="mdi-home"
          :value="state.address"
          @input="(v) => updateState({...state,role:v})"
        />
      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="orange"
        @click="() => doRegister()"
      >Register</v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { moduleName, stateType, RegisterModule } from "./RegisterFormts";
import { User } from "../api/endpoints";
import { Result } from "../api/wrapper";

@Component
export default class RegisterForm extends Vue {
  @Prop({ required: true }) registerf!: (
    user: User
  ) => Promise<Result<User, unknown>>;

  get state() {
    return RegisterModule.getState;
  }
  updateState(state: stateType) {
    RegisterModule.updateSate(state);
  }

  doRegister() {
    const {
      email,
      password,
      name,
      address,
      phone,
      role
    } = RegisterModule.getState;
    const user: User = {
      email,
      password,
      name,
      address,
      phone,
      id: 0,
      active: true,
      role: role === "" ? "ROLE_CUSTOMER" : role
    };
    RegisterModule.register({ user, registerf: this.registerf });
  }
}
</script>