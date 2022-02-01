<template>
  <el-container id="homeContainer">
    <el-header id="nav"></el-header>
    <el-main id="homeMain">
      <Landing></Landing>
      <Introduction></Introduction>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
import Landing from "@/components/Landing";
import Introduction from "@/components/Introduction";
export default {
  name: 'Home',
  components: {Introduction, Landing}
}
</script>

<style scoped>
#homeContainer{
  height: 1000px;
  position: relative;
}
.el-main{
  margin: 0;
  padding: 0;
  position: relative;
}
#nav{
  position: absolute;
  height: 60px;
  width: 100%;
  z-index: 9;
}
#homeMain{
  background-color: lightblue;
}
</style>
