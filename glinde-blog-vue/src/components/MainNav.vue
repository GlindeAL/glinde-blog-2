<template>
  <nav>
    <p>Glinde's Blog</p>
    <ul>
      <li><router-link class="main-link" to="/home">首页</router-link></li>
      <li><router-link class="main-link" to="/admin">管理</router-link></li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "MainNav"
}
</script>

<style scoped>
p {
  float: left;
  font-size: 30px;
  font-family: Hellowords;
  line-height: 45px;
  color: white;
}
ul {
  list-style-type: none;
  text-align: center;
  margin: 3px auto;
}
li {
  display: inline-block;
  margin: 0 50px;
}
.main-link {
  display: inline-block;
  width: 100px;
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
.main-link:hover {
  background-color: rgba(0,0,0,.1);
}
</style>