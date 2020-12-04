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
export default {
  name: "Main",
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