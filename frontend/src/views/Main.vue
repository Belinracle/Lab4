<template>
  <div id="main" class="d-flex flex-column">
    <Header/>
    <my-container/>
    <router-link to="/">start</router-link>
    <Footer/>
  </div>
</template>

<script>
import Header from "../components/common/Header.vue";
import Footer from "../components/common/Footer.vue";
import myContainer from "../components/main/myContainer.vue";
import {pointFetches} from "../resources/scripts/PointFetches";

export default {
  name: "Main",
  created() {
    pointFetches.getUserPointsFetch(JSON.parse(atob(localStorage.getItem("jwt").split('.')[1])).sub)
        .then((response) => {
          if (response.ok) {
            response.text().then(text => console.log(text));
          } else response.text().then(text => console.error(text));
        });
  },
  components:{
    Header,
    Footer,
    myContainer
  }
}
</script>

<style scoped>
#main{
  height: 100%;
}
</style>