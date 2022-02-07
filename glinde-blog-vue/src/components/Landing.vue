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
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
#welcomeText{
  width: 600px;
  position: absolute;
  top: 20%;
  left: 58%;
}
@font-face{
  font-family: Hellowords;
  src: url('../assets/font/Hellwords2.31.ttf')
}
p{
  font-size: 70px;
  text-align: center;
  color: white;
  font-family: Hellowords;
}
</style>