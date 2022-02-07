<template>
  <div id="singleBlogContainer">
    <el-breadcrumb>
      <el-breadcrumb-item :to="'/home'">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="'/home/blogs'">博客</el-breadcrumb-item>
      <el-breadcrumb-item>{{this.blog.title}}</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="blog" v-loading="loading">
      <span>{{this.blog.title}}</span>
      <article class="markdown-body" v-html="this.blog.content"></article>
    </div>
  </div>
</template>

<script>
export default {
  name: "SingleBlog",
  mounted() {
    this.getSingleBlog()
  },
  data(){
    return {
      id: this.$route.params.id,
      blog: {},
      loading: true
    }
  },
  methods:{
    getSingleBlog(){
      this.getRequest(`/blog/home/info/${this.id}`).then(resp => {
        if(resp){
          this.blog = resp.data
          this.loading = false
          let md = require('markdown-it')({
            breaks: true,
          });
          this.blog.content = md.render(resp.data.content);
        }
      })
    }
  }
}
</script>

<style scoped>
.blog {
  min-height: 500px;
  height: auto;
  margin-top: 20px;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.blog span {
  display: block;
  margin: 20px 20px 50px;
  font-size: 30px;
}
>>> .blog article p a {
  color: #5e90cb;
}
>>> .blog article p img {
  max-width: 700px;
  width: auto;
}
#singleBlogContainer {
  width: 1000px;
  margin: 0 auto;
}
</style>