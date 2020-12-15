<template>
  <div id="start" class="container d-flex flex-column min-vh-100">
    <Header class="flex-grow-0"/>
    <LoginForm class="flex-grow-1"/>
    <Footer class="flex-grow-0"/>
  </div>
</template>

<script>
import Header from '../components/common/Header.vue'
import LoginForm from "../components/start/LoginForm.vue";
import Footer from "../components/common/Footer.vue";
export default {
  name: 'start',
  components:{
    Header,
    LoginForm,
    Footer
  }
}
</script>

<style>
html, body{
  height: 100%;
  min-width: 200px;
}
#start{
  min-width: 200px!important;
}

</style>
