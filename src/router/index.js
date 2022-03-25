import { createRouter, createWebHistory } from 'vue-router'
import fa from "element-plus/packages/locale/lang/fa";
const routes = [
  {
    path:'/',
    // 异步懒加载 推荐使用
    component: () => import('../views/new_index')
  },
  {
    path:'/search',
    // 异步懒加载 推荐使用
    component: () => import('../views/search'),
    beforeEnter(to,from){
      let userData = JSON.parse(localStorage.getItem("user-data"))
      // console.log(userData);
      if(
          userData.searchfunction==true
      ){
        return(true)
      }
      else {
        return('/router')
      }
    }
  },
  {
    path:'/report',
    // 异步懒加载 推荐使用
    component: () => import('../views/report')
  },
  {
    path:'/report2',
    // 异步懒加载 推荐使用
    component: () => import('../views/report2')
  },
  ,{
    path:'/uploadEnterprise',
    // 异步懒加载 推荐使用
    component: () => import('../views/uploadEnterprise')
  }
  ,{
    path:'/enterpriseRegistration',
    // 异步懒加载 推荐使用
    component: () => import('../views/enterpriseRegistration')
  },
  {
    path:'/backStage',
    // 异步懒加载 推荐使用
    component: () => import('../views/backStage'),
    beforeEnter: (to, from) => {
      let userData = JSON.parse(localStorage.getItem("user-data"))
      if(
          userData.searchfunction==true
      ){
        return(true)
      }
      else {
        return('/router')
      }

  }
  },
  {
  path:'/test',
    // 异步懒加载 推荐使用
    component: () => import('../views/test')
},
  {
    path:'/test3',
    // 异步懒加载 推荐使用
    component: () => import('../views/test3')
  },
  {
    path:'/router',
    // 异步懒加载 推荐使用
    component: () => import('../views/router')
  },
  // {
  //   path:'/prosearch',
  //   // 异步懒加载 推荐使用
  //   component: () => import('../views/proSearch'),
  //   beforeEnter: (to, from) => {
  //     let userData = JSON.parse(localStorage.getItem("user-data"))
  //     // console.log(userData);
  //     if(
  //         userData.searchfunction==true
  //   ){
  //       return(true)
  //     }
  //     else {
  //       return('/router')
  //     }
  //   }
  // },
  {
    path:'/sysmonitor',
    // 异步懒加载 推荐使用
    component: () => import('../views/sysMonitor'),
    beforeEnter: (to, from) => {
      let userData = JSON.parse(localStorage.getItem("user-data"))
      if (
          userData.monitorf == true
      ) {
        return (true)
      } else {
        return ('/router')
      }
    }
  }
  ]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from) => {
  let userData = JSON.parse(localStorage.getItem("user-data"))
  // console.log(userData);
  let token = localStorage.getItem("access-admin")
  // console.log(token)
  if(to.path==='/')
    return true
  if(token==null){
    return('/')
  }else{
    return true
  }
  // if(
  //     userData.searchfunction==true
  // ){
  //   return(true)
  // }
  // else {
  //   return('/router')
  // }
  return true
})

export default router
