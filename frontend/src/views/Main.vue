<template>
  <div id="main" class="d-flex flex-column">
    <Header/>
    <my-container
      :points="points"
      @add-point="addPoint"
      @clear-points="clear"
    />
    <router-link to="/">start</router-link>
    <Footer/>
<!--    <b-card class="mt-3" header="Form Data Result">-->
<!--      <pre class="m-0">{{ points }}</pre>-->
<!--    </b-card>-->
  </div>
</template>

<script>
import Header from "../components/common/Header.vue";
import Footer from "../components/common/Footer.vue";
import myContainer from "../components/main/myContainer.vue";
import {pointFetches} from "../resources/scripts/PointFetches";

export default {
  name: "Main",
  data(){
    return{
      points:[]
    }
  },
  beforeCreate() {
    pointFetches.getUserPointsFetch(localStorage.getItem("jwt"))
        .then((response) => {
          if (response.ok) {
            response.json().then(json =>this.points=json)
          } else response.text().then(text => console.error(text));
        });
  },
  components:{
    Header,
    Footer,
    myContainer
  },
  methods:{
    addPoint:function (point){
      this.points.push(point)
    },
    clear:function (){
      this.points=[]
    }
  }
}
</script>

<style scoped>
#main{
  height: 100%;
}
</style>