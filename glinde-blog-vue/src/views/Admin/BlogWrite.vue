<template>
  <div class="write-container">
    <el-form :model="writeForm" :rules="writeFormRules" ref="writeFormRef">
      <el-form-item label="标题：" prop="title">
        <el-input v-model="writeForm.title"></el-input>
      </el-form-item>
      <el-form-item label="摘要：" prop="description">
        <el-input v-model="writeForm.description"></el-input>
      </el-form-item>
      <el-form-item label="内容：" prop="content">
        <mavon-editor v-model="writeForm.content" ref="md" @imgAdd="imgAdd" @imgDel="imgDel"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "BlogWrite",
  data(){
    return{
      writeForm: {
        title: '',
        description: '',
        content: '',
      },
      imgFile: [],
      writeFormRules: {
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
    resetForm(){
      this.$refs.writeFormRef.resetFields()
    },
    submitForm(){
      this.$refs.writeFormRef.validate(valid => {
        if(!valid) return
        this.postRequest("/blog/admin/save",this.writeForm).then(resp => {
          if(resp){
            this.resetForm()
          }
        })
      })
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
    }
  }
}
</script>

<style scoped>

</style>