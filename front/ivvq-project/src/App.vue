<template>
  <v-app>
    <v-content>
      <div class="d-flex flex-column justify-space-between fill-height">
        <div>
          <Toolbar :mode="mode()"> </Toolbar>
        </div>
        <v-alert
          v-if="getError() !== undefined"
          type="error"
          :dismissible="true"
          @input="onCloseError"
        >
          {{getError()}}
        </v-alert>
        <v-alert
          v-if="getSuccess() !== undefined"
          type="success"
          :dismissible="true"
          @input="onCloseSucces"
        >
          {{getSuccess()}}
        </v-alert>
        <v-spacer></v-spacer>
        <router-view>
        </router-view>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
      </div>
    </v-content>
  </v-app>
</template>

<script lang="ts">
import { RegisterModule } from "./components/RegisterFormts";
import { MainModule } from "./components/mainStoreModule";
import { Component, Vue } from "vue-property-decorator";
import Toolbar from "@/components/toolbar/Toolbar.vue";
import { Role, Mode } from "./api/wrapper";

@Component({
  components: {
    Toolbar
  }
})
export default class App extends Vue {
  getError = () => MainModule.getState.error || RegisterModule.getState.error;
  getSuccess = () => MainModule.getState.success;

  onCloseError() {
    if (MainModule.getState.error) MainModule.cleanError();
    else RegisterModule.cleanError();
  }
  onCloseSucces() {
    MainModule.cleanSuccess();
  }
  mode = (): Mode => {
    switch (MainModule.getState.jwtResponse?.role as Role) {
      case "ROLE_CUSTOMER":
        return "UserMode";
      case "ROLE_SELLER":
        return "AdminMode";
    }
    return "UserMode";
  };
}
</script>
