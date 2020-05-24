<template>
  <div class="d-flex flex-column justify-space-between fill-height">
    <div>
      <Toolbar> </Toolbar>
    </div>
    <v-alert
      v-if="getError() !== undefined"
      type="error"
      :dismissible="true"
    >
      {{getError()}}
    </v-alert>
    <v-spacer></v-spacer>
    <RegisterForm
      class="align-self-center"
      :register="register"
    > </RegisterForm>
    <v-spacer></v-spacer>
    <v-spacer></v-spacer>

  </div>
</template>

<script>
import Toolbar from "@/components/toolbar/Toolbar.vue";
import RegisterForm from "@/components/RegisterForm.vue";
import { Component, Vue } from "vue-property-decorator";
import { RegisterModule } from "@/components/RegisterFormts";
import { UserApi } from "../api/wrapper";

@Component({
  components: {
    Toolbar,
    RegisterForm
  }
})
export default class Payment extends Vue {
  getError = () => RegisterModule.getState.error;
  register = u => UserApi.register(u);
}
</script>
