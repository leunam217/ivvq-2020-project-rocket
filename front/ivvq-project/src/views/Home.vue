<template>
  <v-container>
    <v-row>
      <v-col
        v-for="product in products"
        v-bind:key="product.productId"
      >
        <ProductC :product="product">
        </ProductC>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts" >
// @ is an alias to /src
import ProductC from "@/components/ProductC.vue";
import Toolbar from "@/components/toolbar/Toolbar.vue";
import { Component, Vue } from "vue-property-decorator";
import { Product } from "@/api/endpoints";
import { MainModule } from "@/components/mainStoreModule";

@Component({
  components: {
    Toolbar,
    ProductC
  }
})
export default class Login extends Vue {
  products: Product[] = MainModule.getState.shoppingCart.map(v => v.product);
  created() {
    MainModule.loadProducts();
  }
}
</script>
