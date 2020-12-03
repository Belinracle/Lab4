<template>
  <div id="start" class="w-100  container-fluid d-flex flex-column min-vh-100">
    <Header/>
    <LoginForm/>
    <Footer/>
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
}
</style>
