<template>
  <v-container>
    <v-row>
      <v-col
        v-for="product in getProducts()"
        v-bind:key="product.productId"
      >
        <ProductC
          :product="product"
          :addProduct="addProduct"
          :tempstockf="tempstockf"
        >
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
import { Mode, Role } from "../api/wrapper";

@Component({
  components: {
    Toolbar,
    ProductC
  }
})
export default class Login extends Vue {
  getProducts = (): Product[] =>
    MainModule.getState.shoppingCart.map(v => v.product);

  addProduct = (id: any) => {
    switch (this.mode()) {
      case "UserMode":
        return MainModule.addToShoppingCart(id);
      case "AdminMode":
        return MainModule.addToStore(id);
    }
  };

  tempstockf(id: any) {
    const v = MainModule.shoppingCart.find(v => v.product.productId === id);
    const [quantity, stock] = [v?.quantity || 0, v?.product.productStock];
    if (quantity === undefined || stock === undefined) return undefined;
    switch (this.mode()) {
      case "UserMode":
        return stock - quantity;
      case "AdminMode":
        return stock + quantity;
    }
  }
  created() {
    MainModule.loadProducts();
    MainModule.loadOrderHistory();
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
