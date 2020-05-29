<template>
  <LoginForm
    class="align-self-center"
    :loginf="loginf"
  > </LoginForm>
</template>

<script>
import Toolbar from "@/components/toolbar/Toolbar.vue";
import LoginForm from "@/components/login/LoginForm.vue";
import { Component, Vue } from "vue-property-decorator";
import { MainModule } from "../components/mainStoreModule";
import router from "../router";
import { UserApi } from "../api/wrapper";

@Component({
  components: {
    Toolbar,
    LoginForm
  }
})
export default class Login extends Vue {
  loginf = authForm => UserApi.login(authForm);
}
</script>
