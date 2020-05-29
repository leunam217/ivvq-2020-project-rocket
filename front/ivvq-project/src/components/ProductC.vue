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
      {{ product.productPrice}} € Available: {{tempstock()}}
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
      <span>{{selectedQuantity}}</span>
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

@Component
export default class ProductC extends Vue {
  show = false;
  @Prop({ required: true })
  product!: Product;

  selectedQuantity = 0;
  tempstock() {
    return this.product.productStock - this.selectedQuantity;
  }

  plusClick() {
    this.selectedQuantity++;
  }

  minusClick() {
    this.selectedQuantity--;
  }
}
</script>
