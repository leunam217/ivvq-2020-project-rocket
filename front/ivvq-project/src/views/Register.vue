<template>
  <RegisterForm
    class="align-self-center"
    :registerf="registerf"
  > </RegisterForm>
</template>

<script>
import Toolbar from "@/components/toolbar/Toolbar.vue";
import RegisterForm from "@/components/RegisterForm.vue";
import { Component, Vue } from "vue-property-decorator";
import { UserApi } from "../api/wrapper";
import { RegisterModule } from "../components/RegisterFormts";

@Component({
  components: {
    Toolbar,
    RegisterForm
  }
})
export default class Register extends Vue {
  registerf = u => UserApi.register(u);
}
</script>
