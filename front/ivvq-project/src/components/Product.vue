<template>
  <v-card
    class="mx-0"
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
      1 000 000 000 €
    </v-card-subtitle>

    <v-card-actions>

      <v-btn
        class="mx-2"
        fab
        small
        dark
        color="orange"
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
  quantity = 0;
  name = "Rocket";
  @Prop(String) readonly imageUrl: string =
    "https://img.pixers.pics/pho_wat(s3:700/FO/43/38/99/86/700_FO43389986_e1a030f717849f99319d1dec9c53186b.jpg,700,700,cms:2018/10/5bd1b6b8d04b8_220x50-watermark.png,over,480,650,jpg)/posters-flying-metal-rocket-isoated-vector-icon-3d.jpg.jpg";
}
</script>
