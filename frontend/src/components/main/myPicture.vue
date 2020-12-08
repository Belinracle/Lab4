<template>
  <div class="col-auto">
    <svg ref="svg"
        class="image"
         @click="handleClick"
         width="500"
         height="500"
         xmlns="http://www.w3.org/2000/svg">
      <path fill="#6ADCFB" d="M150 250 A100 100 0 0 0 250 350 L250 250"></path>
      <rect fill="#6ADCFB" width="200" height="100" x="250" y="250"></rect>
      <path fill="#6ADCFB" stroke="#6ADCFB" stroke-width="2" d="M250 150 L450 250 L250 250"></path>
      <g id="coordLines">
        <path fill="none" stroke="#000000" stroke-width="2" d="M0 250 H 500 M250 0 v500 M50 245 v10 M150 245 v10 M350 245 v10 M450 245 v10
                      M245 50 h10 M245 150 h10 M245 350 h10 M245 450 h10
                      M240 10 L250 0 L260 10
                      M490 240 L500 250 L490 260"></path>
      </g>
      <g id="grafText">
        <text x="45" y="240">-R</text>
        <text x="140" y="240">-R/2</text>
        <text x="340" y="240">R/2</text>
        <text x="445" y="240">R</text>
        <text x="257" y="55">R</text>
        <text x="257" y="155">R/2</text>
        <text x="257" y="353">-R/2</text>
        <text x="257" y="453">-R</text>
      </g>
      <g class="pointPlace">
        <circle v-for="point in points"
                v-if="r===point.r"
                :class="{hit:point.hit,}"
                :cx="computeX(point.x,point.r)"
                :cy="computeY(point.y,point.r)"
                r="3"
        >
        </circle>
      </g>
    </svg>
<!--    <b-card class="mt-3" header="Form Data Result">-->
<!--      <pre class="m-0">{{ r }}</pre>-->
<!--    </b-card>-->
  </div>
</template>

<script>
import {pointFetches} from "../../resources/scripts/PointFetches";
import router from "../../router";

export default {
  name: "myPicture",
  props: ['points', 'r'],
  data() {
    return {
      x_value: '',
      y_value: '',
    }
  },
  methods: {
    computeX: function (x, r) {
      return 250 + x / r * 200;
    },
    computeY: function (y, r) {
      return 250 - y / r * 200;
    },
    handleClick: function (event) {
      let svg = this.$refs.svg
      if(this.r===''){
        alert("Выберите R")
      }
      if (+this.r > 0) {
        let pt = svg.createSVGPoint();
        pt.x = event.clientX;
        pt.y = event.clientY;
        let gp = pt.matrixTransform(svg.getScreenCTM().inverse())
        this.x_value = (gp.x - 250) / 200 * this.r;
        this.y_value = (gp.y - 250) / (-200) * this.r;
        this.postPoint(this.generateRequest())
      }
    },
    generateRequest: function () {
      return {
        x: this.x_value,
        y: this.y_value,
        r: this.r,
        username: JSON.parse(atob(localStorage.getItem("jwt").split('.')[1])).sub
      }
    },
    postPoint: function (request) {
      // console.log(request)
      pointFetches.postPointFetch(request,localStorage.getItem("jwt"))
          .then((response) => {
            if (response.ok) {
              response.json().then(json => {
                this.addPoint(json)
                // console.log(json)
              });
            }else if (response.status===401){
              router.push('/')
            } else response.text().then(text => console.error(text));
          });
    },
    addPoint: function (point) {
      this.$emit('add-point', point)
    },
  }
}
</script>

<style scoped>
.image:hover{
  box-shadow: 0 0 10px rgba(0,0,0,0.5);
}
.hit {
  fill: #e20000;
}
</style>