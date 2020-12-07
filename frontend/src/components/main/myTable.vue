<template>
  <div class="container-fluid ">
    <div class="row justify-content-center">
    <b-table class="text-center col-8 " striped hover outlined bordered head-variant="light" :items="points" :fields="fields"></b-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "myTable",
  props: ['points'],
  data() {
    return {
      fields: [
        {
          key: 'x',
          sortable: true
        },
        {
          key: 'y',
          sortable: true
        },
        {
          key: 'r',
          sortable: true
        },
        {
          key: 'hit',
          sortable: true
        }
      ],
    }
  }
}
</script>

<style scoped>

</style>