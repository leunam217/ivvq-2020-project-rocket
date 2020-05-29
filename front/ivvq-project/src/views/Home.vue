<template>
  <v-row>
    <v-col>
      <router-link
        to=/register>
        <Product
      >
        </Product>
      </router-link>
    </v-col>
    <v-col>
      <Product></Product>
    </v-col>
    <v-col>
      <Product></Product>
    </v-col>
    <v-col>
      <Product></Product>
    </v-col>
    <v-col>
      <Product></Product>
    </v-col>
    <v-col>
      <Product></Product>
    </v-col>
    <v-col>
      <Product></Product>
    </v-col>
    <v-col>
      <Product></Product>
    </v-col>

  </v-row>
</template>

<script>
// @ is an alias to /src
import Product from "@/components/Product";
import Toolbar from "@/components/toolbar/Toolbar";
import { Component, Vue } from "vue-property-decorator";

@Component({
  components: {
    Toolbar,
    Product
  }
})
export default class Login extends Vue {}
</script>
