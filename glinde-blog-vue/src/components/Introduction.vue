<template>
  <div id="introductionContainer">
    <div id="avatarDiv">
      <el-avatar :src="glindeAvatar" :size="192"></el-avatar>
    </div>
    <div id="rightDiv">
      <article id="introductionText">
        <p>&emsp;目前是一名在校大学生，主要学习后端开发，也了解一些前端知识。</p><br>
        <p>打算继续努力学习理论、了解技术，然后根据自己的兴趣做更多的项目，</p><br>
        <p>尽量在大四之前通过做项目让自己变得越熟练越好。有时会刷刷算法题，</p><br>
        <p>预计在大三下学期也会去准备一些面经，为之后的实习和校招做准备。</p>
      </article>
      <div id="linkDiv">
        <span class="line-between">———————————————————————————————</span>
        <ul>
          <li><a href="https://github.com/GlindeAL" target="_blank">GitHub</a></li>
          <li><a href="https://blog.csdn.net/Glinde" target="_blank">CSDN</a></li>
          <li><a href="https://space.bilibili.com/4211186" target="_blank">bilibili</a></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Introduction",
  data() {
    return {
      glindeAvatar: require('../assets/img/avatar.jpg')
    }
  }
}
</script>

<style scoped>
#introductionContainer{
  height: 250px;
  width: 800px;
  background-color: rgb(102, 153, 204);
  position: absolute;
  top: 520px;
  left: 50%;
  transform: translate(-50%,0);
  z-index: 9;
  border-radius: 50px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}
.el-avatar {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  border: white 3px solid;
}
#avatarDiv {
  float: left;
  position: relative;
  height: 250px;
  width: 250px;
}
#rightDiv {
  float: left;
  height: 250px;
  width: 550px;
}
#introductionText {
  height: 120px;
  width: 550px;
  padding-top: 30px;
}
#linkDiv {
  height: 100px;
  width: 550px;
}
.line-between {
  color: whitesmoke;
}
ul {
  list-style-type: none;
  text-align: center;
  margin: 20px auto;
}
li {
  display: inline-block;
  margin: 0 50px;
}
a {
  display: inline-block;
  font-size: 20px;
  color: white;
}
p {
  line-height: 6px;
  color: white;
}
</style>