import HomePage from "@/components/HomePage";
import DetailPage from "@/components/DetailPage";
import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const router = new VueRouter({
  routes:[
    {
      path: "/",
      name: "HomePage",
      component: HomePage,
    },
    {
      path: "/detail",
      name: "DetailPage",
      component: DetailPage,
    }


  ]
})



export default router;