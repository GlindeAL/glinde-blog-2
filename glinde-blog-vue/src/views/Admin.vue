<template>
  <el-container id="adminContainer">
    <el-header id="adminNav"><MainNav></MainNav></el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
            router
            :default-active="this.$route.path"
            background-color="#66829b"
            text-color="#fff"
            active-text-color="#ffd04b">
          <el-menu-item index="/admin/blog/write">
            <i class="el-icon-edit"></i>
            <span slot="title">写博客</span>
          </el-menu-item>
          <el-menu-item index="/admin/blog/edit">
            <i class="el-icon-document"></i>
            <span slot="title">博客编辑</span>
          </el-menu-item>
          <el-menu-item index="/admin/mail/manage">
            <i class="el-icon-box"></i>
            <span slot="title">信箱管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main><router-view></router-view></el-main>
    </el-container>
  </el-container>
</template>

<script>
import MainNav from "@/components/MainNav";
export default {
  name: "Admin",
  components: {MainNav},
  mounted() {
    this.toDefaultChild()
  },
  methods: {
    toDefaultChild(){
      this.$router.replace("/admin/blog/write")
    }
  }
}
</script>

<style scoped>
#adminContainer {
  height: 100vh;
}
#adminNav {
  background-color: rgb(114, 107, 158);
}
.el-aside {
  background-color: rgb(102, 130, 155);
}
</style>