<template>
  <div class="admin-mail-container">
    <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getAdminMails">
      <el-button slot="append" @click="getAdminMails">搜索</el-button>
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
    <div class="admin-mails-container">
      <el-card v-for="mail in mails" v-bind:key="mail.id">
        <div slot="header">
          <el-button type="primary" icon="el-icon-check" @click="updateMailState(mail.id,1)" circle></el-button>
          <el-button type="success" icon="el-icon-minus" @click="updateMailState(mail.id,3)" circle></el-button>
          <el-button type="info" icon="el-icon-view" @click="updateMailState(mail.id,2)" circle></el-button>
          <el-button type="danger" icon="el-icon-delete" @click="deleteMail(mail.id)" circle></el-button>
          <span class="mail-id">id : {{mail.id}}</span>
          <span class="mail-state">状态：{{mail.state}}</span>
          <span class="mail-date" :style="'color:grey'">{{mail.date}}</span>
        </div>
        <article>{{mail.content}}</article>
      </el-card>
    </div>
    <el-input class="search" placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getAdminMails">
      <el-button slot="append" @click="getAdminMails">搜索</el-button>
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
</template>

<script>
import {formatDate} from "@/utils/date";

export default {
  name: "MailsManage",
  mounted() {
    this.getAdminMails()
  },
  data(){
    return{
      mails: [],
      queryInfo: {
        query: "",   //查询信息
        pageCurrent: 1,  //当前页
        pageSize: 16  //每页最大数
      },
      total: 0
    }
  },
  methods:{
    getAdminMails(){
      this.getRequest("/mail/admin/list").then(resp => {
        if(resp){
          for(let i=0;i<resp.data.records.length;i++){
            if(resp.data.records[i].state===1){
              resp.data.records[i].state="显示"
            }else if(resp.data.records[i].state===0){
              resp.data.records[i].state="等待审核"
            }else if(resp.data.records[i].state===2){
              resp.data.records[i].state="私人"
            }else{
              resp.data.records[i].state="不过审"
            }
          }
          this.total = resp.data.total
          this.mails = resp.data.records
          for(let i=0;i<this.mails.length;i++){
            this.mails[i].date = formatDate(this.mails[i].date)
          }
        }
      })
    },
    updateMailState(id,state){
      this.putRequest(`/mail/admin/state?id=${id}&state=${state}`).then(resp => {
        if(resp){
          this.getAdminMails()
        }
      })
    },
    deleteMail(id){
      this.deleteRequest(`/mail/admin/delete/${id}`).then(resp => {
        if(resp){
          this.getAdminMails()
        }
      })
    },
    handleSizeChange(newSize){
      this.queryInfo.pageSize = newSize;
      this.getAdminMails()
    },
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage;
      this.getAdminMails();
    }
  }
}
</script>

<style scoped>
.admin-mail-container {
  width: 60%;
  margin: 0 auto;
}
.el-card {
  margin: 8px auto;
}
.mail-date {
  float: right;
}
.mail-state {
  margin-left: 32px;
}
.mail-id {
  margin-left: 32px;
}
.search {
  float: left;
  width: 365px;
  margin-right: 10px;
  margin-bottom: 5px;
}
</style>