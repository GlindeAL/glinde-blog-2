<template>
  <div id="loginContainer">
    <div id="loginDiv">
      <p>登录</p>
      <el-form :model="loginForm" :rules="loginFormRules" ref="loginFormRef" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password"></el-input>
        </el-form-item>
      </el-form>
      <el-button id="loginButton" type="primary" @click="login">登录</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      loginForm: {
        username: '',
        password: ''
      },
      loginFormRules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 5, max: 30, message: '长度在 5 到 30 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods:{
    login(){
      this.$refs.loginFormRef.validate(valid => {
        if(!valid) return;
        this.postRequest("/login",this.loginForm).then(resp => {
          if(resp){
            window.localStorage.setItem("token",resp.data.token)
            window.localStorage.setItem("username",resp.data.username)
            window.localStorage.setItem("userId",resp.data.userId)
            this.$router.push({path: "/admin"})
          }
        })
      })
    }
  }
}
</script>

<style scoped>
#loginContainer{
  position: relative;
  height: 100vh;
  background-image: url("../assets/img/landing_picture.jpg");
  background-size: cover;
}
#loginDiv{
  position: absolute;
  top: 35%;
  left: 50%;
  transform: translate(-50%,-50%);;
  width: 600px;
  padding: 20px;
  background-color: rgba(255,255,255,.5);
  border-radius: 30px;
}
p{
  text-align: center;
  font-size: 20px;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  line-height: 1.5;
  margin-bottom: 20px;
}
#loginButton {
  display: block;
  margin: 0 auto;
}
</style>