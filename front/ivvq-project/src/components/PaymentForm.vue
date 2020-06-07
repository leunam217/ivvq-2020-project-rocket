<template>
  <v-card
    width="400"
    class="ma-3"
  >
    <v-card-title class="pb-0">
      <h1>Payment</h1>
    </v-card-title>
    <v-card-text>
      <br />
      <span
        v-for="(product,i) in list"
        v-bind:key="i"
      >
        {{product}}
        <br />
      </span>
      Total : {{total}}€
      <v-form>
        <v-text-field
          label="Card number"
          prepend-icon="mdi-account-circle"
          @input="(v) => updateState({...state,cardNumber:v})"
        />
      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="orange"
        @click="pay"
      >Pay</v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { MainModule } from "./mainStoreModule";
import { ProductOrdered } from "../api/endpoints";
import { stateType, RegisterModule } from "./RegisterFormts";

@Component
export default class LoginForm extends Vue {
  get state() {
    return MainModule.getState;
  }

  get list() {
    return MainModule.shoppingCart
      .filter(v => v.show && v.quantity >= 1)
      .map(
        v =>
          `${v.product.productName}(x ${v.quantity}) : ${v.quantity *
            v.product.productPrice}€`
      );
  }
  get total() {
    return MainModule.shoppingCart
      .filter(v => v.show && v.quantity >= 1)
      .reduce(
        (acc, curr) => acc + curr.quantity * curr.product.productPrice,
        0
      );
  }

  updateState(state: stateType) {
    RegisterModule.updateSate(state);
  }

  pay() {
    MainModule.pay();
  }
}
</script>