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
          :content="messages"
          :value="messages"
          color="red"
        >
          <v-icon large>mdi-cart</v-icon>
        </v-badge>
      </v-btn>

    </template>
    <v-list three-line>
      <template v-for="(item, index) in items">

        <v-divider
          v-if="item.divider"
          :key="index"
          :inset="item.inset"
        ></v-divider>

        <v-list-item
          v-else
          :key="item.title"
        >
          <v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-html="item.title"></v-list-item-title>
            <v-list-item-subtitle>
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
              <v-btn
                class="mx-2"
                fab
                small
                dark
                color="orange"
              >
                <v-icon dark>mdi-delete</v-icon>
              </v-btn>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>

      <v-divider
        :key="-1"
        :inset="true"
      ></v-divider>
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

@Component
export default class Cart extends Vue {
  messages = 1;
  quantity = 1;
  items = [
    {
      avatar:
        "https://img.pixers.pics/pho_wat(s3:700/FO/43/38/99/86/700_FO43389986_e1a030f717849f99319d1dec9c53186b.jpg,700,700,cms:2018/10/5bd1b6b8d04b8_220x50-watermark.png,over,480,650,jpg)/posters-flying-metal-rocket-isoated-vector-icon-3d.jpg.jpg",
      title: "Rocket 1"
    },
    { divider: true, inset: true },
    {
      avatar:
        "https://img.pixers.pics/pho_wat(s3:700/FO/43/38/99/86/700_FO43389986_e1a030f717849f99319d1dec9c53186b.jpg,700,700,cms:2018/10/5bd1b6b8d04b8_220x50-watermark.png,over,480,650,jpg)/posters-flying-metal-rocket-isoated-vector-icon-3d.jpg.jpg",
      title: "Rocket 2"
    }
  ];
}
</script>