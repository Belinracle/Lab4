<template>
  <div class="container-fluid ">
    <div class="row justify-content-center">
    <b-table class="text-center col-8 " striped hover outlined bordered head-variant="light" :items="items" :fields="fields"></b-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "myTable",
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
          key: 'result',
          sortable: true
        }
      ],
      items: [
        {isActive: true, age: 40, first_name: 'Dickerson', last_name: 'Macdonald'},
        {isActive: false, age: 21, first_name: 'Larsen', last_name: 'Shaw'},
        {isActive: false, age: 89, first_name: 'Geneva', last_name: 'Wilson'},
        {isActive: true, age: 38, first_name: 'Jami', last_name: 'Carney'}
      ]
    }
  }
}
</script>

<style scoped>

</style>