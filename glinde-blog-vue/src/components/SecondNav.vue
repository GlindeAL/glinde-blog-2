<template>
  <div id="secondNavContainer">
    <ul>
      <li><router-link class="second-link" to="/home/blogs">博客</router-link></li>
      <li><router-link class="second-link" to="/home/mails">匿名邮箱</router-link></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "SecondNav"
}
</script>

<style scoped>
#secondNavContainer {
  position: relative;
  height: 250px;
}
ul {
  position: absolute;
  height: 60px;
  width: 500px;
  top: 175px;
  left: 50%;
  background-color: rgb(102, 153, 204);
  border-radius: 20px;
  list-style-type: none;
  text-align: center;
  transform: translate(-50%,-50%);
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
li {
  display: inline-block;
  margin: 7px 50px;
}
.second-link {
  display: inline-block;
  width: 150px;
  font-size: 25px;
  font-family: Hellowords;
  line-height: 45px;
  color: white;
  border-radius: 20px;
  transition: background-color 0.2s;
}
.router-link-active {
  background-color: rgba(0,0,0,.1);
}
.second-link:hover {
  background-color: rgba(0,0,0,.1);
}
</style>