<template>
  <div id="introductionContainer">
    <div id="avatarDiv">
      <el-avatar :src="glindeAvatar" :size="192"></el-avatar>
    </div>
    <div id="rightDiv">
      <article id="introductionText">
        <p>&emsp;24届毕业生，目前在找实习，以及为之后的校招做准备。</p>
        <p>&emsp;大学在校期间自学了后端开发的相应技术栈以及一些架构知识。</p>
        <p>&emsp;出于兴趣和练习技术，根据自己的设计制作了glinta视频网站、glinyun网盘、glindexam在线考试这三个项目。</p>
        <p>&emsp;最近还做了一个基于Netty+spring-cloud-bootstrap的配置中心，取名为Jarrjes，有时间的话会考虑再做一个微服务调用框架。</p>
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
  height: 155px;
  width: 530px;
  padding-right: 20px;
  padding-top: 20px;
}
#linkDiv {
  height: 75px;
  width: 550px;
}
.line-between {
  color: whitesmoke;
}
ul {
  list-style-type: none;
  text-align: center;
  margin: 8px auto 0;
}
li {
  display: inline-block;
  margin-right: 90px;
}
a {
  display: inline-block;
  font-size: 20px;
  color: white;
}
p {
  line-height: 25px;
  color: white;
}
</style>