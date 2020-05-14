<!--<template>
  <v-card
    class="mx-auto"
    max-width="344"
  >
    <v-img
      :src="imageUrl"
      height="200px"
    ></v-img>

    <v-card-title>
        {{ name}}
    </v-card-title>

    <v-card-subtitle>
      1,000 miles of wonder
    </v-card-subtitle>

    <v-card-actions>
      <v-btn text>Share</v-btn>

      <v-btn
        color="purple"
        text
      >
        Explore
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
          I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
        </v-card-text>
      </div>
    </v-expand-transition>
  </v-card>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";

@Component
export default class Product extends Vue {
  show = false;
  @Prop(String) readonly imageUrl: string =
    "https://img.pixers.pics/pho_wat(s3:700/FO/43/38/99/86/700_FO43389986_e1a030f717849f99319d1dec9c53186b.jpg,700,700,cms:2018/10/5bd1b6b8d04b8_220x50-watermark.png,over,480,650,jpg)/posters-flying-metal-rocket-isoated-vector-icon-3d.jpg.jpg";
}
</script>
-->
<template>
  <v-container>
    <h2 class="display-2 mb-4">Basket</h2>

    <v-list two-line>
      <template v-for="(product, index) in products">
        <v-list-tile
          :key="product.productId"
          avatar
        >
          <v-list-tile-avatar>
            <img :src="product.photo">
          </v-list-tile-avatar>

          <v-list-tile-content>
            <v-list-tile-title v-html="product.title"></v-list-tile-title>
            <v-list-tile-sub-title v-html="product.subTitle"></v-list-tile-sub-title>
          </v-list-tile-content>

          <v-list-tile>
            {{product.price}}$
          </v-list-tile>

          <v-list-tile-action>
            <v-text-field
              label="Quantity"
              reverse
              :value="product.qty"
            ></v-text-field>
          </v-list-tile-action>

          <v-list-tile>
            {{product.price * product.qty}}$
          </v-list-tile>

          <v-list-tile-action>
            <v-btn
              icon
              ripple
            >
              <v-icon color="red lighten-1">delete</v-icon>
            </v-btn>
          </v-list-tile-action>
        </v-list-tile>

        <v-divider
          v-if="index + 1 < products.length"
          :key="index"
        ></v-divider>
      </template>
    </v-list>

    <v-container>
      <v-btn
        color="success"
        larger
        style="float: right;"
      >Go to payment</v-btn>
    </v-container>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    products: [
      {
        productId: "kitchen-1",
        photo: "https://cdn.vuetifyjs.com/images/cards/kitchen.png",
        title: "QW cooking utensils",
        subTitle:
          "Our vintage kitchen utenils delight any chef. Made of bamboo by hand",
        price: 14.99,
        qty: 1
      },
      {
        productId: "kitchen-1",
        photo: "https://cdn.vuetifyjs.com/images/cards/kitchen.png",
        title: "QW cooking utensils",
        subTitle:
          "Our vintage kitchen utenils delight any chef. Made of bamboo by hand",
        price: 44.99,
        qty: 2
      },
      {
        productId: "kitchen-1",
        photo: "https://cdn.vuetifyjs.com/images/cards/kitchen.png",
        title: "QW cooking utensils",
        subTitle:
          "Our vintage kitchen utenils delight any chef. Made of bamboo by hand",
        price: 64.99,
        qty: 2
      }
    ]
  })
};
</script>

<style scoped>
</style>