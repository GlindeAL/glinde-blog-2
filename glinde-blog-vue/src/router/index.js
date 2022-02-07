import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Blogs from "@/views/Home/Blogs";
import Mails from "@/views/Home/Mails";
import SingleBlog from "@/views/Home/SingleBlog";
import Admin from "@/views/Admin";
import MailsManage from "@/views/Admin/MailsManage";
import BlogsEdit from "@/views/Admin/BlogsEdit";
import Login from "@/views/Login";
import BlogWrite from "@/views/Admin/BlogWrite";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    redirect: '/home/blogs',
    component: Home,
    children: [
      { path: "/home/blogs", component: Blogs },
      { path: "/home/mails", component: Mails },
      { path: "/home/blog/:id", component: SingleBlog }
    ]
  },
  {
    path: "/admin",
    redirect: "/admin/blog/write",
    component: Admin,
    meta: {
      requireAuth: true
    },
    children: [
      {
        path: "/admin/mail/manage",
        component: MailsManage,
        meta: {
          requireAuth: true
        }
      },
      {
        path: "/admin/blog/edit",
        component: BlogsEdit,
        meta: {
          requireAuth: true
        }
      },
      {
        path: "/admin/blog/write",
        component: BlogWrite,
        mata: {
          requireAuth: true
        }
      }
    ]
  },
  {
    path: "/login",
    component: Login
  }
]

const router = new VueRouter({
  routes
})

export default router
