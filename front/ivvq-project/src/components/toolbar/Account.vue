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
          color="red"
          :content="0"
          :value="0"
        >
          <v-icon large>mdi-account</v-icon>
        </v-badge>
      </v-btn>

    </template>
    <v-list>
      <template v-for="(item, index) in items">
        <v-list-item :key="item.title">

          <v-list-item-content>
            <v-list-item-title v-html="item.title"></v-list-item-title>
            <v-list-item-subtitle v-html="item.info">
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider
          :key="index"
          :inset="true"
        ></v-divider>

      </template>

      <v-divider
        :key="-1"
        :inset="true"
      ></v-divider>
    </v-list>
  </v-menu>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";
import { MainModule } from "../mainStoreModule";
import { Order } from "../../api/endpoints";
import { Mode } from "../../api/wrapper";

@Component
export default class Account extends Vue {
  @Prop({ required: true })
  mode!: Mode;

  format(order: Order) {
    const by = this.mode === "AdminMode" ? "(by " + order.buyerEmail + ")" : "";
    const title = "Command " + order.orderId + by;
    const infoList = order.products
      .map(
        p => `&mdash; ${p.productName} (x${p.count}) &nbsp; \
      ${p.productPrice}€ (x${p.count}) &mdash; \
      ${p.count * p.productPrice}€ <br/> `
      )
      .reduce((acc, curr) => acc + curr, "");

    const infoTotal = `&mdash; Total = ${order.orderAmount}€ `;
    const info = infoList + infoTotal + "<br/> No code used";
    return {
      title,
      info
    };
  }
  get items() {
    return MainModule.getState.previousOrders
      .filter(
        v =>
          MainModule.getState.jwtResponse?.type === "ROLLE_SELLER" ||
          v.buyerName === MainModule.getState.jwtResponse?.name
      )
      .map(this.format);
  }
}
</script>