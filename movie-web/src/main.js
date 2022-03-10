import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import axios from 'axios';
import router from "./router/index.js"
import { MoviedbServiceClient } from "@/proto/moviedb_grpc_web_pb.js";
Vue.config.productionTip = false
Vue.prototype.$axios = axios;
Vue.prototype.$backend = new MoviedbServiceClient("http://localhost:9080", null, null);
let OSS = require('ali-oss')

let client = new OSS({
  endpoint: "oss-eu-west-1.aliyuncs.com",
  accessKeyId: "LTAI5tLGTNLk9oDuRfty5Qta",
  accessKeySecret:"xfn8jzOzI5SQ6H1p2vmDoOSdmChUti",
  bucket:"movie-db"
})

Vue.prototype.$ossClient = client
new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
