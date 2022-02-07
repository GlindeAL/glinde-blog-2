<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item to="/home">首页</el-breadcrumb-item>
      <el-breadcrumb>匿名信箱</el-breadcrumb>
    </el-breadcrumb>
    <el-form :model="mailForm" :rules="mailFormRules" ref="mailFormRef">
      <el-form-item label="匿名信（经过审核才能展示）" prop="content">
        <el-input type="textarea" :rows="6" v-model="mailForm.content"></el-input>
      </el-form-item>
      <el-form-item label="是否公开：" prop="state">
        <el-checkbox v-model="mailForm.state" true-label="0" false-label="2" checked>是</el-checkbox>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitMail">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
    <div class="input-pagination-container">
      <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getAllMails">
        <el-button slot="append" @click="getAllMails">搜索</el-button>
      </el-input>
      <el-pagination
          @size-change=handleSizeChange
          @current-change=handleCurrentChange
          :current-page=queryInfo.pageCurrent
          :page-sizes="[8, 16, 32, 48]"
          :page-size=queryInfo.pageSize
          layout="prev, pager, next, jumper, sizes, total"
          :total=total>
      </el-pagination>
    </div>
    <div class="mails-container" v-loading="loading">
      <el-card v-for="mail in mails" :key="mail.id">
        <div slot="header">
          <span :style="'color:grey'">{{mail.date}}</span>
        </div>
        <article>{{mail.content}}</article>
      </el-card>
    </div>
    <div class="input-pagination-container">
      <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getAllMails">
        <el-button slot="append" @click="getAllMails">搜索</el-button>
      </el-input>
      <el-pagination
          @size-change=handleSizeChange
          @current-change=handleCurrentChange
          :current-page=queryInfo.pageCurrent
          :page-sizes="[8, 16, 32, 48]"
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
  name: "Mails",
  mounted() {
    this.getAllMails()
  },
  data(){
    return {
      mailForm: {
        content: '',
        state: 0
      },
      mails: [],
      mailFormRules: {
        content: [
          { required: true, message: '请输入匿名信内容', trigger: 'blur' },
          { min: 1, max: 300, message: '长度在 1 到 300 个字符', trigger: 'blur' }
        ],
      },
      queryInfo: {
        query: "",   //查询信息
        pageCurrent: 1,  //当前页
        pageSize: 16  //每页最大数
      },
      total: 0,
      loading: true
    }
  },
  methods:{
    resetForm(){
      this.$refs.mailFormRef.resetFields()
    },
    getAllMails(){
      this.getRequest("/mail/home/list",this.queryInfo).then(resp => {
        if(resp){
          this.total = resp.data.total
          this.mails = resp.data.records
          for(let i=0;i<this.mails.length;i++){
            this.mails[i].date = formatDate(this.mails[i].date)
          }
          this.loading = false;
        }
      })
    },
    submitMail(){
      this.$refs.mailFormRef.validate(valid => {
        if(!valid) return
        this.postRequest("/mail/save",this.mailForm).then(resp => {
          if(resp){
            this.resetForm()
            this.getAllMails()
          }
        })
      })
    },
    handleSizeChange(newSize){
      this.queryInfo.pageSize = newSize;
      this.getAllMails();
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageCurrent = newPage;
      this.getAllMails()
    }
  }
}
</script>

<style scoped>
.el-form {
  width: 750px;
  margin: 0 auto;
}
.input-pagination-container {
  width: 500px;
  margin: 0 auto;
}
.mails-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-content: flex-start;
}
.el-card {
  max-width: 400px;
  width: auto;
  margin: 20px 20px;
}
</style>