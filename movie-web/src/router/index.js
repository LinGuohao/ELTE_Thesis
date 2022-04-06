import HomePage from "@/components/HomePage";
import DetailPage from "@/components/DetailPage";
import SignUpPage from "@/components/SignUpPage";
import LoginPage from "@/components/LogInPage";
import ProfilePage from "@/components/ProfilePage";
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
      path: '/detail',
      name: "DetailPage",
      component: DetailPage,
    },
    {
      path:"/signup",
      name: "SignUp",
      component: SignUpPage,
    },
    {
      path:"/login",
      name:"LogIn",
      component:LoginPage,
    },
    {
      path:"/profile",
      name:"Profile",
      component: ProfilePage,
    }


  ]
})



export default router;