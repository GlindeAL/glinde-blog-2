<template>
  <div class="admin-blogs-container">
    <div class="left-container">
      <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getBlogList">
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
      <el-card v-for="blog in blogs" v-bind:key="blog.id">
        <div slot="header">
          <el-button class="title-button" @click="getSingleBlog(blog.id)">{{blog.title}}</el-button>
          <span>{{blog.date}}</span>
        </div>
        <div>
          <p>id：{{blog.id}}</p>
          <p>状态：{{blog.state===1?"显示":"隐藏"}}</p>
          {{blog.description}}
        </div>
      </el-card>
      <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getBlogList">
        <el-button slot="append" @click="getBlogList">搜索</el-button>
      </el-input>
      <el-pagination
          @size-change=handleSizeChange
          @current-change=handleCurrentChange
          :current-page=queryInfo.pageCurrent
          :page-sizes="[5, 10, 20, 30]"
          :page-size=queryInfo.pageSize
          layout="prev, pager, next, jumper, sizes, total"
          :total=total>
      </el-pagination>
    </div>
    <div class="right-container">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef">
        <el-form-item label="标题：" prop="title">
          <el-input v-model="editForm.title"></el-input>
        </el-form-item>
        <el-form-item label="摘要：" prop="description">
          <el-input v-model="editForm.description"></el-input>
        </el-form-item>
        <el-form-item label="内容：" prop="content">
          <br/>
          <mavon-editor v-model="editForm.content" ref="md" @imgAdd="imgAdd" @imgDel="imgDel"></mavon-editor>
        </el-form-item>
        <el-form-item>
          <p>id：{{editForm.id}}</p>
          <p>状态：{{editForm.state?(editForm.state===1?"显示":"隐藏"):""}}</p>
          <el-button type="primary" icon="el-icon-check" @click="updateBlogState(editForm.id,1)"></el-button>
          <el-button type="info" icon="el-icon-view" @click="updateBlogState(editForm.id,2)"></el-button>
          <el-button type="danger" icon="el-icon-delete" @click="deleteBlog(editForm.id)"></el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateBlog">修改</el-button>
          <el-button @click="submitForm">提交</el-button>
          <el-button type="info" @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {formatDate} from "@/utils/date";

export default {
  name: "BlogsEdit",
  mounted() {
    this.getBlogList()
  },
  data(){
    return{
      queryInfo: {
        query: "",   //查询信息
        pageCurrent: 1,  //当前页
        pageSize: 6  //每页最大数
      },
      blogs: [],
      total: 0,
      imgFile: [],
      editForm: {
        id: null,
        title: '',
        description: '',
        content: '',
        state: null
      },
      editFormRules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入介绍', trigger: 'blur' },
          { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请选择内容', trigger: 'blur' },
          { min: 1, max: 10000, message: '长度在 1 到 10000 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    getBlogList(){
      this.getRequest("/blog/admin/list",this.queryInfo).then(resp => {
        if(resp){
          this.total = resp.data.total
          this.blogs = resp.data.records
          for(let i=0;i<this.blogs.length;i++){
            this.blogs[i].date = formatDate(this.blogs[i].date)
          }
        }
      })
    },
    getSingleBlog(id){
      this.getRequest(`/blog/admin/info/${id}`).then(resp => {
        if(resp){
          this.editForm = resp.data
        }
      })
    },
    updateBlogState(id,state){
      this.putRequest(`/blog/admin/state?id=${id}&state=${state}`).then(resp => {
        if(resp){
          this.editForm.state = state
          this.getBlogList()
        }
      })
    },
    updateBlog(){
      this.putRequest("/blog/admin/update",this.editForm).then(resp => {
        if(resp){
          this.getBlogList()
        }
      })
    },
    submitForm(){
      this.postRequest("/blog/admin/save",this.editForm).then(resp => {
        if(resp){
          this.resetForm()
          this.getBlogList()
        }
      })
    },
    deleteBlog(id){
      this.deleteRequest(`/blog/admin/delete/${id}`).then(resp => {
        if(resp){
          this.getBlogList()
        }
      })
    },
    resetForm(){
      this.$refs.editFormRef.resetFields()
    },
    imgAdd(pos,$file){
      let formData = new FormData();
      formData.append('image',$file);
      this.imgFile[pos] = $file
      this.filePost("/minio/post/image",formData).then(resp => {
        if(resp){
          this.$refs.md.$img2Url(pos,resp.data)
        }
      })
    },
    imgDel(pos){
      this.deleteRequest("/minio/delete/image",{url: pos[0]}).then(resp => {
        if(resp){
          delete this.imgFile[pos]
        }
      })
    },
    handleSizeChange(newSize){
      this.queryInfo.pageSize = newSize;
      this.getBlogList();
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage;
      this.getBlogList()
    }
  }
}
</script>

<style scoped>
.left-container {
  float: left;
  width: 49%;
}
.right-container {
  float: right;
  width: 49%;
}
.el-card {
  margin: 10px 0;
}
span {
  float: right;
  color: grey;
}
.title-button {
  font: 23px black;
  border: none;
}
p {
  font-size: 16px;
  line-height: normal;
}
.search {
  width: 365px;
  margin-right: 10px;
  margin-bottom: 5px;
}
</style>