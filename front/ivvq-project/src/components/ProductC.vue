<template>
  <v-card
    class="mx-0"
    max-width="344"
  >
    <v-img
      :src="product.productIcon"
      height="200px"
    ></v-img>

    <v-card-title>
      {{ product.productName }}
    </v-card-title>

    <v-card-subtitle>
      {{ product.productPrice}} € Available: {{tempstock}}
    </v-card-subtitle>

    <v-card-actions>

      <v-btn
        class="mx-2"
        fab
        small
        dark
        color="orange"
        @click="minusClick"
      >
        <v-icon dark>mdi-minus</v-icon>
      </v-btn>
      <span>{{quantity}}</span>
      <v-btn
        class="mx-2"
        fab
        small
        dark
        color="orange"
        @click="plusClick"
      >
        <v-icon dark>mdi-plus</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn
        class="mx-2"
        fab
        small
        dark
        color="orange"
        @click="addToShoppingCart"
      >
        <v-icon dark>mdi-cart-plus</v-icon>
      </v-btn>

      <v-spacer></v-spacer>

      <v-btn
        icon
        @click="show = !show"
      >
        <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
      </v-btn>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>
        <v-card-text>
          {{product.productDescription}}
        </v-card-text>
      </div>
    </v-expand-transition>
  </v-card>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import { Product } from "@/api/endpoints";
import { MainModule } from "./mainStoreModule";

@Component
export default class ProductC extends Vue {
  show = false;
  @Prop({ required: true })
  product!: Product;

  get tempstock() {
    const v = MainModule.shoppingCart.find(
      v => v.product.productId === this.product.productId
    );
    const [quantity, stock] = [v?.quantity || 0, v?.product.productStock];
    if (!quantity || !stock) return undefined;
    return stock - quantity;
  }

  get quantity() {
    return MainModule.shoppingCart.find(
      v => v.product.productId === this.product.productId
    )?.quantity;
  }

  plusClick = () => MainModule.addOneProduct(this.product.productId);

  minusClick = () => MainModule.removeOneProduct(this.product.productId);

  addToShoppingCart = () =>
    MainModule.addToShoppingCart(this.product.productId);
}
</script>
