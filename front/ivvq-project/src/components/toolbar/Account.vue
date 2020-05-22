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
          <v-icon large>mdi-account</v-icon>
        </v-badge>
      </v-btn>

    </template>
    <v-list>
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

          <v-list-item-content>
            <v-list-item-title v-html="item.title"></v-list-item-title>
            <v-list-item-subtitle v-html="item.info">
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
export default class Account extends Vue {
  messages = 1;
  quantity = 1;
  items = [
    {
      title: "Command 1",
      info:
        " &mdash; Rocket 1 (x5) &nbsp; &mdash; 1 000 000 000€ (x5) &mdash; 5 000 000 000€ <br/>" +
        "\n &mdash; Rocket 2 (x5) &nbsp; &mdash; 1 000 000 000€ (x5) &mdash; 5 000 000 000€  <br/>" +
        "\n &mdash; Toal = 10 000 000 000€ "
    },
    { divider: true, inset: true },
    {
      title: "blabla",
      info: "idem"
    }
  ];
}
</script>