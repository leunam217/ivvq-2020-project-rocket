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
    <Account></Account>
    <Cart></Cart>
  </v-toolbar>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Cart from "./Cart.vue";
import Account from "./Account.vue";

@Component({
  components: {
    Cart,
    Account
  }
})
export default class Toolbar extends Vue {
  messages = 1;
  items = [
    { title: "Click Me" },
    { title: "Click Me" },
    { title: "Click Me" },
    { title: "Click Me 2" }
  ];
}
</script>