<template>
  <form class="container">
    <div class="row">
      <div class="form-group col-12 text-center">
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
               class="form-control mb-4 sm-"
               placeholder="Login">
        <input type="password"
               v-model="password"
               class="form-control mb-4"
               placeholder="Password">
      </div>
    </div>
    <div class="row justify-content-center">
      <button @click.prevent="authorize" class="btn btn-info btn-block my-4" id="log-in">Sign in</button>
      <button @click.prevent="register" class="btn btn-info btn-block my-4" id="register">Register</button>
      <router-link v-if="authorized" to="/main">
        <button>Main</button>
      </router-link>
    </div>
  </form>
</template>

<script>
import {userFetches} from '../../resources/scripts/UserFetches.js'

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
  created() {
    let token = localStorage.getItem("jwt");
    if( token ){
      this.authorized=true;
      this.info = "Вы авторизованы как пользователь: " + JSON.parse(atob(token.split('.')[1])).sub
    }
  },
  methods: {
    register: function () {
      this.checkForm();
      if (this.login && this.password) {
        userFetches.registerFetch(this.request)
            .then((response) => {
              if (response.ok) {
                response.text().then(text => this.info = text);
              } else response.text().then(text => this.errors.push(text));
            });
      }
    },
    authorize: function () {
      this.checkForm();
      if (this.login && this.password) {
        userFetches.loginFetch(this.request).then((response) => {
          if (response.ok) {
            response.text().then(text => localStorage.setItem('jwt', text))
                .then(() => this.authorized = true)
                .then(() => this.info = "Вы авторизованы как пользователь: " + JSON.parse(atob(localStorage.getItem("jwt").split('.')[1])).sub);
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