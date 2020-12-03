<template>
  <div class="container-fluid">
    <div class="row justify-content-center">
      <my-picture/>
      <my-form/>
    </div>
    <my-table/>
  </div>
</template>

<script>
import myPicture from "./myPicture.vue";
import myForm from "./myForm.vue"
import myTable from "./myTable.vue";

export default {
  name: "container",
  components: {
    myPicture,
    myForm,
    myTable
  }
}
</script>

<style scoped>

</style>