<template>
  <PaymentForm class="align-self-center"> </PaymentForm>
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
