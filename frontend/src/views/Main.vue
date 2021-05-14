<template>
  <div id="main" v-if="valid" class="d-flex flex-column">
    <Header />
    <my-container
        class="flex-grow-1"
        :points="points"
        @add-point="addPoint"
        @clear-points="clear"
    />
      <router-link class="text-center flex-grow-0" to="/">
        <button
            @click="logOut"
            class="btn btn-success p-1 btn-lg">To start page and Log out
        </button>
      </router-link>
    <Footer class="flex-grow-0"/>
    <!--        <b-card class="mt-3" header="Form Data Result">-->
    <!--          <pre class="m-0">{{ points }}</pre>-->
    <!--        </b-card>-->
  </div>
</template>

<script>
import Header from "../components/common/Header.vue";
import Footer from "../components/common/Footer.vue";
import myContainer from "../components/main/myContainer.vue";
import {pointFetches} from "../resources/scripts/PointFetches";
import {userFetches} from "../resources/scripts/UserFetches";

export default {
  name: "Main",
  data() {
    return {
      points: [],
      valid: false
    }
  },
  created() {
    if (localStorage.getItem("jwt") == null) {
      alert("Авторизируйся, чорт");
      this.valid = false;
    } else {
      userFetches.checkFetch(localStorage.getItem("jwt")).then((response) => {
        if (response.ok) {
          this.valid = true
          pointFetches.getUserPointsFetch(localStorage.getItem("jwt"))
              .then((response) => {
                if (response.ok) {
                  response.json().then(json => this.points = json)
                } else response.text().then(text => console.error(text));
              });
        } else {
          alert("Неизвестный токен, пройдите авторизацию повторно");
          this.valid = false;
        }
      }).catch(() => alert("Ошибка сети, проверьте проброшенные порты"))
    }
  },
  components: {
    Header,
    Footer,
    myContainer
  }
  ,
  methods: {
    addPoint: function (point) {
      this.points.push(point)
    }
    ,
    clear: function () {
      this.points = []
    }
    ,
    logOut: function () {
      pointFetches.removeFromProfiling(localStorage.getItem("jwt"))
      localStorage.removeItem("jwt")
    }
  }
}
</script>

<style>
#main{
  height: 100%;
  min-width: 520px;
}
html, body{
  height:100%;
}
</style>