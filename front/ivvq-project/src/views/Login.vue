<template>
  <div class="d-flex flex-column justify-space-between fill-height">
    <Toolbar> </Toolbar>
    <LoginForm class="align-self-center"> </LoginForm>
    <v-spacer></v-spacer>
    <v-spacer></v-spacer>

  </div>
</template>

<script>
import Toolbar from "@/components/toolbar/Toolbar.vue";
import LoginForm from "@/components/login/LoginForm.vue";
import { Component, Vue } from "vue-property-decorator";

@Component({
  components: {
    Toolbar,
    LoginForm
  }
})
export default class Login extends Vue {}
</script>
