<template>
  <div class="row justify-content-center flex-grow-1 ">
    <form class="text-center p-5 col-8 col-lg-4">
      <div v-if="errors.length">
        <b>Пожалуйста исправьте ошибки: </b>
        <ul>
          <li v-for="error in errors">{{ error }}</li>
        </ul>
      </div>
      <div v-if="info">
        <strong>{{ info }}</strong>
      </div>
      <p class="h4 mb-4">Sign in</p>
      <input type="text"
             v-model="login"
             class="form-control mb-4"
             placeholder="Login">
      <input type="password"
             v-model="password"
             class="form-control mb-4"
             placeholder="Password">
      <!-- Sign in button -->
      <button @click.prevent="authorize" class="btn btn-info btn-block my-4" id="log-in">Sign in</button>
      <button @click.prevent="register" class="btn btn-info btn-block my-4" id="register">Register</button>
      <router-link to="/main"><button>Main</button></router-link>
    </form>
  </div>
</template>

<script>
import {fetches} from '../../resources/scripts/fetches.js'

export default {
  name: "LoginForm",

  computed: {
    request: function () {
      return {name: this.login, pass: this.password}
    },
  },


  data() {
    return {
      login: null,
      password: null,
      errors: [],
      info: '',
      authorized: false
    }
  },


  methods: {
    register: function () {
      this.checkForm();
      if (this.login && this.password) {
        fetches.registerFetch(this.request).then((response) => {
          if (response.ok) {
            response.text().then(text => this.info = text);
          } else response.text().then(text => this.errors.push(text));
        });
      }
    },

    authorize: function () {
      this.checkForm();
      if (this.login && this.password) {
        fetches.loginFetch(this.request).then((response) => {
          if (response.ok) {
            response.text().then(text => localStorage.setItem('jwt',text)).then(()=>this.authorized=true);
          } else response.text().then(text => this.errors.push(text));
        });
      }
    },

    checkForm: function (e) {
      this.errors = [];
      this.info = '';
      if (!this.login) {
        this.errors.push('Чего это вы не заполнили логин?')
      }
      if (!this.password) {
        this.errors.push('А пароль я сам должен заполнять за вас?')
      }
    }
  }
}
</script>

<style scoped>

</style>