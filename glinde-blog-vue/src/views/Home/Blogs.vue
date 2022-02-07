<template>
  <div id="blogsContainer">
    <div class="input-pagination-container">
      <el-input placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getBlogList">
        <el-button slot="append" @click="getBlogList">搜索</el-button>
      </el-input>
      <el-pagination
          @size-change=handleSizeChange
          @current-change=handleCurrentChange
          :current-page=queryInfo.pageCurrent
          :page-sizes="[6, 12, 24, 36]"
          :page-size=queryInfo.pageSize
          layout="prev, pager, next, jumper, sizes, total"
          :total=total>
      </el-pagination>
    </div>
    <div id="blogsMain" v-loading="this.loading">
      <el-card v-for="blog in blogs" v-bind:key="blog.id">
        <div slot="header">
          <span class="blog-date">{{blog.date}}</span>
          <router-link class="blog-link" :to="'/home/blog/'+blog.id">{{blog.title}}</router-link>
        </div>
        <div>
          {{blog.description}}
        </div>
      </el-card>
    </div>
    <div class="input-pagination-container">
      <el-input placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getBlogList">
        <el-button slot="append" @click="getBlogList">搜索</el-button>
      </el-input>
      <el-pagination
          @size-change=handleSizeChange
          @current-change=handleCurrentChange
          :current-page=queryInfo.pageCurrent
          :page-sizes="[6, 12, 24, 36]"
          :page-size=queryInfo.pageSize
          layout="prev, pager, next, jumper, sizes, total"
          :total=total>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {formatDate} from "@/utils/date";

export default {
  name: "Blogs",
  mounted() {
    this.getBlogList()
  },
  data(){
    return{
      queryInfo: {
        query: "",
        pageCurrent: 1,
        pageSize: 6
      },
      blogs: [],
      total: 0,
      loading: true
    }
  },
  methods: {
    getBlogList(){
      this.getRequest("/blog/home/list",this.queryInfo).then(resp => {
        if(resp){
          this.total = resp.data.total
          this.blogs = resp.data.records
          for(let i=0;i<this.blogs.length;i++){
            this.blogs[i].date = formatDate(this.blogs[i].date)
          }
          this.loading = false
        }
      })
    },
    handleSizeChange(newSize){
      this.queryInfo.pageSize = newSize;
      this.getBlogList();
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageCurrent = newPage;
      this.getBlogList()
    }
  }
}
</script>

<style scoped>
#blogsMain {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-content: flex-start;
}
.input-pagination-container {
  width: 500px;
  margin: 0 auto;
}
.el-input {
  width: 500px;
}
.el-card {
  height: auto;
  width: 550px;
  margin: 20px 20px;
}
.blog-link {
  font-size: 30px;
}
.blog-date {
  float: right;
  color: grey;
}
</style>