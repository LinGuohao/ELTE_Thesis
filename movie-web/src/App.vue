<template>
  <v-app>
    <v-app-bar app class="grey darken-4 white--text">
      <v-toolbar-title @click="goHome()" style="cursor: pointer">
        Home
      </v-toolbar-title>
      <v-spacer></v-spacer>
        <v-col cols="12" sm="6" md="3">
          <v-text-field label="Search" solo class="pt-8" v-model="searchText"> <v-icon slot="append" color="red"> mdi-magnify </v-icon></v-text-field>
        </v-col>
    </v-app-bar>
    <v-navigation-drawer app class="grey darken-4">
      <v-divider></v-divider>
      <v-list nav>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="text-h6 white--text">{{
              userName
            }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-content class="white--text">
            <v-list-item-title @click="goHome()"> Home </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link v-if="userInfo">
          <v-list-item-content class="white--text">
            <v-list-item-title @click="goProfile()">
              Profile
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item link v-if="userInfo == null">
          <v-list-item-content class="white--text">
            <v-list-item-title @click="goSignUp()"> Sign up </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <template v-slot:append>
        <div class="pa-2">
          <v-btn block v-if="userInfo == null" @click="goLogin()">
            Log In
          </v-btn>
          <v-btn block v-else @click="logOut()"> LogOut </v-btn>
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
    searchText: null
  }),

  methods: {
    goHome() {
      this.$router
        .push({
          path: "/",
        })
        .catch((err) => err);
    },
    goSignUp() {
      this.$router
        .push({
          path: "/signup",
        })
        .catch((err) => err);
    },
    goLogin() {
      this.$router.push({ path: "/login" }).catch((err) => err);
    },
    logOut() {
      localStorage.removeItem("fullname");
      localStorage.removeItem("username");
      localStorage.removeItem("roles");
      window.location.reload();
    },
    goProfile() {
      this.$router
        .push({
          path: "/profile",
        })
        .catch((err) => err);
    },
  },

  watch:{
    searchText(newValue){
       this.$store.state.searchText = newValue;
    }
  },

  computed: {
    userInfo() {
      if (localStorage.getItem("username") == null) {
        return null;
      } else {
        return localStorage.getItem("username");
      }
    },

    userName() {
      if (localStorage.getItem("username") == null) {
        return "Guest";
      } else {
        return localStorage.getItem("fullname");
      }
    },
  },
};
</script>
