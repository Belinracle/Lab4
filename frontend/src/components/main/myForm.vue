<template>
  <div class="col-5">
    <form class="container-fluid text-center ">
      <p>X={{ form.x_value }}
        <strong v-if="errors.x">({{ errors.x }})</strong>
      </p>
      <div class="row">
        <button v-for="num in values"
                @click="setX({num})"
                type="button"
                v-model="form.x_value"
                class="col btn btn-success btn-outline-dark">{{ num }}
        </button>
      </div>
      <label for="y-value">Y={{ form.y_value }}
        <strong v-if="errors.y">({{ errors.y }})</strong>
      </label>
      <input class="col"
             type="text"
             id="y-value"
             @input="validate_Y"
             v-model="form.y_value"
             placeholder="Введите значение У в интервале (-5...3)">
      <div>R ={{ form.r_value }}
        <strong v-if="errors.r">({{ errors.r }})</strong>
      </div>
      <div class="row">
        <button v-for="num in values"
                @click="setR({num})"
                type="button"
                v-model="form.x_value"
                class="col btn btn-dark btn-outline-light btn-secondary">{{ num }}
        </button>
      </div>
      <div class="row">
        <b-button class="col" variant="success">Отправить</b-button>
        <b-button class="col" variant="primary">Очистить</b-button>
      </div>
    </form>
    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ form }}</pre>
      <pre class="m-0">{{ errors }}</pre>
      <pre class="m-0">{{ valid }}</pre>
    </b-card>
  </div>
</template>

<script>

export default {
  name: "myForm",
  data() {
    return {
      values: ['-4', '-3', '-2', '-1', '0', '1', '2', '3', '4'],
      errors: {
        x: '',
        y: '',
        r: ''
      },
      form: {
        x_value: '',
        y_value: '',
        r_value: '',
      }
    }
  },
  computed:{
    valid:function (){
      return this.form.y_value&&
             this.form.x_value&&
             this.form.r_value&&
             !this.errors.y&&
             !this.errors.x&&
             !this.errors.r
    }
  },
  methods: {
    setX: function (e) {
      this.form.x_value = e.num;
      this.validate_X();
    },
    setR: function (e) {
      this.form.r_value = e.num;
      this.validate_R()
    },
    validate_X: function () {
      if (this.form.x_value > 4) {
        this.errors.x = "Значение Х не валидно"
      } else {
        this.errors.x = ''
      }
    },
    validate_R: function () {
      if (this.form.r_value < 1) {
        this.errors.r = "Значение R не валидно. Должно быть >0"
      } else {
        this.errors.r = ''
      }
    },
    validate_Y: function () {
      let y_val = +this.form.y_value
      if (isNaN(y_val)) {
        this.errors.y = "Значение У не является числом"
      } else if (y_val <= -5) {
        this.errors.y = 'Значение У должно быть >-5'
      } else if (y_val >= 3) {
        this.errors.y = 'Значение У должно быть <3'
      } else{
        this.errors.y=''
      }
    }
  },
}
</script>

<style scoped>

</style>