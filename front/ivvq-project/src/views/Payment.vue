<template>
  <div class="d-flex flex-column justify-space-between fill-height">
    <div>
      <Toolbar> </Toolbar>
    </div>
    <v-spacer></v-spacer>

    <PaymentForm class="align-self-center"> </PaymentForm>
    <v-spacer></v-spacer>
    <v-spacer></v-spacer>

  </div>
</template>

<script>
import Toolbar from "@/components/toolbar/Toolbar.vue";
import PaymentForm from "@/components/PaymentForm.vue";
import { Component, Vue } from "vue-property-decorator";

@Component({
  components: {
    Toolbar,
    PaymentForm
  }
})
export default class Payment extends Vue {}
</script>
