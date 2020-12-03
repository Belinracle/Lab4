<template>
<div class="col-auto">
  <svg class="image" width="500" height="500" xmlns="http://www.w3.org/2000/svg">
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
      <circle style=""/>
    </g>
  </svg>
</div>
</template>

<script>
export default {
  name: "myPicture"
}
</script>

<style scoped>

</style>