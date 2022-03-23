<template>
  <v-app>
    <v-app-bar app class="grey darken-4 white--text">
      <v-toolbar-title> Home </v-toolbar-title>
      <v-spacer></v-spacer>

      <v-btn>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </v-app-bar>
    <v-navigation-drawer app class="grey darken-4">
      <v-divider></v-divider>
      <v-list nav>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="text-h6 white--text">{{userName}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item  link>
          <v-list-item-content class="white--text">
            <v-list-item-title @click="goHome()">
              Home
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item  link>
          <v-list-item-content class="white--text">
            <v-list-item-title v-if="userInfo == null" @click="goSignUp()">
              Sign up  
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <template v-slot:append>
        <div class="pa-2">
          <v-btn block v-if="userInfo == null" @click="goLogin()"> Log In </v-btn>
          <v-btn block v-else> LogOut </v-btn>
        </div>
      </template>
    </v-navigation-drawer>

    <v-main class="blue-grey darken-4 white--text">
      <router-view :key="$route.fullPath"><home /></router-view>
    </v-main>
  </v-app>
</template>

<script>
import Home from "./components/HomePage.vue";
export default {
  components: { Home },
  name: "App",

  data: () => ({
    items: [{ title: "Home" }],
    cards: ["Today"],
    links: [
      ["mdi-inbox-arrow-down", "Inbox"],
      ["mdi-send", "Send"],
      ["mdi-delete", "Trash"],
      ["mdi-alert-octagon", "Spam"],
    ],
  }),

  methods: {
    goHome() {
      this.$router
        .push({
          path: "/",
        })
        .catch((err) => err);
    },
    goSignUp(){
      this.$router
        .push({
          path:"/signup",
        }).catch((err)=>err)
    }
  },

  computed: {
    userInfo() {
      return this.$store.state.userInfo;
    },

    userName(){
      if(this.userInfo == null){
        return "Guest"
      }else{
        return this.userInfo.fullname
      }
    }
  }
};
</script>
