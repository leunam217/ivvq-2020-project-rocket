<template>
  <v-content>
    <v-toolbar class="d-flex justify-center green">
      <span class="d-flex">
        <h1 class="white--text pr-4">Rocket Shop</h1>
        <v-icon dark>fa-rocket</v-icon>
      </span>
    </v-toolbar>
  </v-content>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class Toolbar extends Vue {}
</script>