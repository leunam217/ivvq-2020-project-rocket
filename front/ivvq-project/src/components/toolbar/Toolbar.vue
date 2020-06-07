<template>
  <v-toolbar
    class="green"
    dark
  >
    <v-spacer></v-spacer>

    <v-toolbar-title>
      <span class="d-flex ">
        <h1 class="white--text pr-3">Rocket Shop</h1>
        <v-icon dark>fa-rocket</v-icon>
      </span></v-toolbar-title>
    <v-spacer></v-spacer>
    <Account :mode="mode"></Account>
    <Cart></Cart>
  </v-toolbar>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import Cart from "./Cart.vue";
import Account from "./Account.vue";
import { Mode } from "../../api/wrapper";
import { MainModule } from "../mainStoreModule";

@Component({
  components: {
    Cart,
    Account
  }
})
export default class Toolbar extends Vue {
  @Prop({ required: true })
  mode!: Mode;
  userMode = () => MainModule.mState.jwtResponse?.role === "ROLE_CUSTOMER";
  adminMode = () => this.mode === "AdminMode";
}
</script>