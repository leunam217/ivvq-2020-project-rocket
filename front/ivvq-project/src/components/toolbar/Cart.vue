<template>
  <v-menu
    offset-y
    :close-on-content-click="false"
  >
    <template v-slot:activator="{ on }">
      <v-btn
        icon
        v-on="on"
      >
        <v-badge
          :content="shoppingCart.length"
          :value="shoppingCart.length"
          color="red"
        >
          <v-icon large>mdi-cart</v-icon>
        </v-badge>
      </v-btn>

    </template>
    <v-list three-line>
      <template v-for="(item, index) in shoppingCart">

        <v-list-item :key="index+'lol'">
          <v-list-item-avatar>
            <v-img :src="item.product.productIcon"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{item.product.productName}}</v-list-item-title>
            <v-list-item-subtitle>
              <v-btn
                class="mx-2"
                fab
                small
                dark
                color="orange"
                @click="() => minus(item.product.productId)"
              >
                <v-icon dark>mdi-minus</v-icon>
              </v-btn>
              <span>{{item.quantity}}</span>
              <v-btn
                class="mx-2"
                fab
                small
                dark
                color="orange"
                @click="() => plus(item.product.productId)"
              >
                <v-icon dark>mdi-plus</v-icon>
              </v-btn>
              <v-btn
                class="mx-2"
                fab
                small
                dark
                color="orange"
                @click="() => doDelete(item.product.productId)"
              >
                <v-icon dark>mdi-delete</v-icon>
              </v-btn>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider
          :key="index"
          inset
        ></v-divider>

      </template>

      <v-divider inset></v-divider>
      <v-list-item>
        <v-spacer></v-spacer>
        <v-btn
          class="mx-2"
          large
          dark
          color="orange"
        >Pay
        </v-btn>
        <v-spacer></v-spacer>
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { MainModule } from "@/components/mainStoreModule";

@Component
export default class Cart extends Vue {
  get shoppingCart() {
    return MainModule.getState.shoppingCart.filter(v => v.show);
  }
  plus = MainModule.addOneProduct;

  minus = MainModule.removeOneProduct;

  doDelete = MainModule.deleteFromShoppingCart;
}
</script>