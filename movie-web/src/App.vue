<template>
  <v-app>
    <v-app-bar app class="grey darken-4 white--text">
      <v-toolbar-title @click="goHome()" style="cursor: pointer">
        Home
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-col cols="12" sm="6" md="3">
        <v-text-field label="Search" solo class="pt-8" v-model="searchText">
          <v-icon slot="append" color="red"> mdi-magnify </v-icon></v-text-field
        >
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

        <v-list-item link v-if="isAdmin">
          <v-list-item-content class="white--text">
            <v-list-item-title @click="add()"> Add a Movie </v-list-item-title>
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

    <v-dialog
      max-width="600"
      v-model="showAdd"
      transition="dialog-bottom-transition"
    >
      <v-card>
        <v-toolbar color="primary" dark>Add movie</v-toolbar>
        <v-card>
          <v-card-text>
            <v-card-title>Edit</v-card-title>
            <v-card-text>
              <v-text-field
                label="Name"
                v-model="name"
                :rules="[() => !!name || 'This field is required']"
                ref="name"
              ></v-text-field>
              <v-select
                v-model="imdb"
                :items="IMDblist"
                label="IMDb"
                persistent-hint
                :rules="[() => !!imdb || 'This field is required']"
                ref="IMDb"
              ></v-select>
              <v-subheader class="pl-0 pb-10"> Rotten tomatoes </v-subheader>
              <v-slider
                v-model="tomatoes"
                thumb-label="always"
                color="red darken-4"
                track-color="orange darken-2"
              ></v-slider>
              <v-file-input
                accept="image/jpeg"
                placeholder="Pick a cover"
                prepend-icon="mdi-camera"
                label="Cover"
                v-model="cover"
                :rules="[() => !!cover || 'This field is required']"
                ref="cover"
              ></v-file-input>
            </v-card-text>
          </v-card-text>
          <v-alert dense outlined type="error" v-model="showError">
            An unknown error has occurred
          </v-alert>
        </v-card>
        <v-card-actions>
          <v-btn text @click="showAdd = false">Close</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="addMovie()"> Add </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-app>
</template>

<script>
import Home from "./components/HomePage.vue";
import {
  InfoRequest,
  TextUploadRequest,
  FileUploadRequest,
} from "@/proto/moviedb_pb.js";
export default {
  components: { Home },
  name: "App",

  data: () => ({
    searchText: null,
    showAdd: false,
    IMDblist: [],
    name: null,
    imdb: null,
    tomatoes: null,
    cover: null,
    newid: null,
    showError: false,
  }),

  created: function () {
    console.log(process.env.VUE_APP_HOSTNAME);
    console.log(process.env.NODE_ENV);
    this.init();
  },
  methods: {
    init() {
      var index = 0;
      for (var i = 0.0; i < 10.0; i = i + 0.1) {
        this.IMDblist[index] = i.toFixed(1);
        index++;
      }
    },
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
      window.location.reload(true);
    },
    goProfile() {
      this.$router
        .push({
          path: "/profile",
        })
        .catch((err) => err);
    },
    add() {
      this.showAdd = true;
      this.showError = false;
    },
    addMovie() {
      if (this.name && this.imdb && this.cover) {
        this.$backend.insertMovie(
          new InfoRequest()
            .setId("-1")
            .setName(this.name)
            .setImdb(this.imdb)
            .setTomatoes(this.tomatoes),
          {},
          (err, response) => {
            this.newid = response.array[0];
            let path = this.newid + "/info.json";
            this.$backend.uploadTextToOSS(
              new TextUploadRequest()
                .setObjectname(path)
                .setContent(this.getInitJson()),
              {},
              () => {
                this.uploadPhoto();
              }
            );
          }
        );
      } else {
        if (!this.cover) {
          this.$refs.cover.focus();
          this.$refs.cover.blur();
        }
        if (!this.IMDb) {
          this.$refs.IMDb.focus();
          this.$refs.IMDb.blur();
        }
        if (!this.name) {
          this.$refs.name.focus();
          this.$refs.name.blur();
        }
      }
    },
    uploadPhoto() {
      this.getfileByBase64(this.cover);
    },

    getfileByBase64(file) {
      var reader = new FileReader();
      // 传入一个参数对象即可得到基于该参数对象的文本内容
      reader.readAsDataURL(file);

      reader.onload = (e) => {
        // target.result 该属性表示目标对象的DataURL
        //console.log(this);
        this.$backend.uploadFileToOSS(
          new FileUploadRequest()
            .setObjectpath(this.newid + "/")
            .setType("jpg")
            .setContent(e.target.result)
            .setObjectname("cover"),
          {},
          (err, response) => {
            if (response.array[0] == -1) {
              this.showError = true;
            } else {
              window.location.reload(true);
            }
          }
        );
      };
    },
    getInitJson() {
      var jsonString =
        "{" +
        '"director":"' +
        "No data" +
        '",\n' +
        '"actors":"' +
        "No data" +
        '"}';
      return jsonString;
    },
  },

  watch: {
    searchText(newValue) {
      this.$store.state.searchText = newValue;
    },
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
    isAdmin() {
      if (localStorage.getItem("roles") == "admin") {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>
