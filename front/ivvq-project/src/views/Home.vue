<template>
  <div class="d-flex flex-column justify-space-between fill-height">
    <Toolbar> </Toolbar>
    <v-container class="align-self-center">
      <v-row>
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
        <v-col>
          <Product></Product>
        </v-col>

      </v-row>
    </v-container>

    <v-spacer></v-spacer>
    <v-spacer></v-spacer>

  </div>
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
