<template>
  <v-card
    width="400"
    class="ma-3"
  >
    <v-card-title class="pb-0">
      <h1>Login</h1>
    </v-card-title>
    <v-card-text>
      <v-form>
        <v-text-field
          label="Email"
          prepend-icon="mdi-account-circle"
          @input="(username) => updateState({... state, username})"
        />
        <v-text-field
          :type="showPassword ? 'text' : 'password'"
          label="Password"
          prepend-icon="mdi-lock"
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          @click:append="showPassword = !showPassword"
          @input="(password) => updateState({... state, password})"
        />
      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-btn
        color="success"
        @click="goToRegister"
      >Register</v-btn>
      <v-btn
        color="info"
        @click="doLogin"
      >Login</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import { MainModule } from "../mainStoreModule";
import { AuthentificationForm, JwtResponse } from "../../api/endpoints";
import { Result } from "../../api/wrapper";
import router from "../../router";

@Component
export default class LoginForm extends Vue {
  showPassword = false;

  @Prop({ required: true }) loginf!: (
    authForm: AuthentificationForm
  ) => Promise<Result<JwtResponse, unknown>>;
  get state() {
    return MainModule.getState.authForm;
  }
  updateState(authForm: AuthentificationForm) {
    MainModule.updateSate({ ...MainModule.getState, authForm });
  }

  doLogin() {
    MainModule.login({ authForm: this.state, loginf: this.loginf });
  }

  goToRegister() {
    router.push("Register");
  }
}
</script>