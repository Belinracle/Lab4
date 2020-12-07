<template>
  <div class="container-fluid ">
    <div class="row justify-content-center">
      <my-picture
          :r="r"
          :points="points"
          @add-point="addPoint"/>
      <my-form @add-point="addPoint"
               @r="handleR"
               @clear-points="clearPoints"
      />
    </div>
    <my-table :points="points"/>
  </div>
</template>

<script>
import myPicture from "./myPicture.vue";
import myForm from "./myForm.vue"
import myTable from "./myTable.vue";

export default {
  name: "container",
  props: ['points'],
  data(){
    return {
      r:''
    }
  },
  components: {
    myPicture,
    myForm,
    myTable
  },
  methods: {
    addPoint: function (elem) {
      this.$emit('add-point', elem)
    },
    handleR:function(r){
      this.r=r;
    },
    clearPoints:function (){
      this.$emit("clear-points")
    }
  }
}
</script>

<style scoped>
div {

}
</style>