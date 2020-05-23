<template>
  <v-card
    width="400"
    class="ma-3"
  >
    <v-card-title class="pb-0">
      <h1>Payment</h1>
    </v-card-title>
    <v-card-text>
      <br />
      Total : 5 000 000 000 000€
      <v-form>
        <v-text-field
          label="Card number"
          prepend-icon="mdi-account-circle"
        />
      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="orange">Pay</v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class LoginForm extends Vue {
  showPassword = false;
}
</script>