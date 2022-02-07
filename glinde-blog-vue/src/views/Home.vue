<template>
  <el-container id="homeContainer">
    <el-header id="homeNav"><MainNav></MainNav></el-header>
    <el-main id="homeMain">
      <Landing></Landing>
      <Introduction></Introduction>
      <SecondNav></SecondNav>
      <div id="homeViewContainer">
        <router-view></router-view>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import Landing from "@/components/Landing";
import Introduction from "@/components/Introduction";
import MainNav from "@/components/MainNav";
import SecondNav from "@/components/SecondNav";
export default {
  name: 'Home',
  components: {SecondNav, MainNav, Introduction, Landing}
}
</script>

<style scoped>
#homeContainer{
  position: relative;
}
.el-main{
  margin: 0;
  padding: 0;
  position: relative;
}
#homeNav{
  position: absolute;
  height: 60px;
  width: 100%;
  z-index: 9;
}
#homeViewContainer {
  min-height: 500px;
  height: auto;
  width: 80%;
  margin: 0 auto;
}
</style>
