<template>
  <div id="landingMain">
    <div id="welcomeText">
      <p>欢迎来到</p>
      <p>Glinde的个人网站</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Landing"
}
</script>

<style scoped>
#landingMain{
  position: relative;
  height: 650px;
  width: 100%;
  background-image: url("../assets/img/landing_picture.jpg");
  background-size: cover;
}
#welcomeText{
  width: 600px;
  position: absolute;
  top: 20%;
  left: 58%;
}
p{
  font-size: 70px;
  text-align: center;
  color: white;
  -webkit-text-fill-color: white;/*文字的填充色*/
  -webkit-text-stroke: 2px black;/*描边的像素，也就是粗细，这里指定是2像素的黑色边框*/
}
</style>