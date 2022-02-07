import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/global.css'
import mavonEditer from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import {filePost, postRequest} from "@/utils/api";
import {putRequest} from "@/utils/api";
import {getRequest} from "@/utils/api";
import {deleteRequest} from "@/utils/api";

Vue.config.productionTip = false

Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.filePost = filePost

Vue.use(Element)
Vue.use(mavonEditer)

router.beforeEach((to,from,next) => {
  if(to.meta.requireAuth){
    const token = window.localStorage.getItem("token");
    if(!token){
      if(from.meta.requireAuth){
        next("/home")
      }else{
        next(from.path)
      }
    }
    getRequest("/check").then(resp => {
      if(!resp){
        if(from.meta.requireAuth){
          next("/home")
        }else{
          next(from.path)
        }
      }
    })
  }
  return next();
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
