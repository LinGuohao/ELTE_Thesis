<template>
  <v-container>
    <div class="pl-50 pr-50">
      <h1>{{ userName }}</h1>
      <h3>
        <font color="gray">{{ userInfo }}</font>
      </h3>
    </div>
    <v-divider dark></v-divider>

    <v-row>
      <v-col cols="6" class="pr-0">
        <v-card class="mx-auto">
          <v-img src="profile1.png" height="300px" dark>
            <v-row class="fill-height" align="center">
              <v-card-title class="white--text mx-auto">
                <div class="text-h4 text-center">My favourite</div>
              </v-card-title>
            </v-row>
          </v-img>
          <v-list-item v-if="movieList.length == 0">
          <v-list-item-title
            style="font-weight: 800"
          >
            No favourite movie yet
          </v-list-item-title>
          </v-list-item>

          <v-list v-for="(item, index) in movieList" :key="index">
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title style="font-weight: 800" v-html="item[1]">
                </v-list-item-title>
              </v-list-item-content>
              <v-list-item-action>
                <v-btn icon @click="goDetail(item[0])">
                  <v-icon>mdi-arrow-right</v-icon>
                </v-btn>
              </v-list-item-action>
            </v-list-item>
            <v-divider></v-divider>
          </v-list>
        </v-card>
      </v-col>

      <v-col cols="6" class="pl-0">
        <v-card class="mx-auto" max-width="500">
          <v-app-bar color="blue" dark>
            <v-toolbar-title>My comment</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-app-bar>

          <v-list>
            <v-list-item-group>
              <v-list-item-content v-if="commentsList.length == 0">
                <v-list-item-title style="font-weight: 800">
                  No comment yet
                </v-list-item-title>
              </v-list-item-content>
              <v-list v-for="(item, index) in commentsList" :key="index">
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title
                      style="font-weight: 800"
                      v-html="item[4]"
                    >
                    </v-list-item-title>

                    <v-list-item-content v-html="item[3]"></v-list-item-content>
                  </v-list-item-content>
                  <v-list-item-action v-if="showDelete(item[1])">
                    <v-btn icon @click="deleteComment(item[0])">
                      <v-icon>mdi-delete-outline</v-icon>
                    </v-btn>
                  </v-list-item-action>
                </v-list-item>
                <v-divider></v-divider>
              </v-list>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { UsernameRequest, InfoByIDRequest } from "@/proto/moviedb_pb";
export default {
  name: "ProfilePage",
  data: () => ({
    movieList: [],
    UserLikeList: [],
    commentsList: [],
  }),
  created: function () {
    this.init();
    //this.audio.autoplay = true;
  },
  methods: {
    init() {
      this.$backend.getFavoriteMovieList(
        new UsernameRequest().setUsername(this.userInfo),
        {},
        (err, response) => {
          this.movieList = response.array[0];
          //console.log(this.movieList);
        }
      );
      this.$backend.getCommentByUserName(
        new UsernameRequest().setUsername(this.userInfo),
        {},
        (err, response) => {
          this.commentsList = response.array[0];
          this.commentsList.forEach((e) => {
            this.$backend.getInfoByID(
              new InfoByIDRequest().setId(e[2]),
              {},
              (err, response) => {
                e.push(response.array[1]);
              }
            );
          });
        }
      );
    },
    goDetail(mId) {
      console.log(mId);
      //console.log(this.$router);
      this.$router.push({
        path: "/detail",
        query: {
          id: mId,
        },
      });
    },
    showDelete(username) {
      if (username == this.userInfo || this.isAdmin) {
        return true;
      } else {
        return false;
      }
    },
    deleteComment(item) {
      this.$backend.deleteComment(
        new InfoByIDRequest().setId(item),
        {},
        (err, response) => {
          if (response.array[0] == 1) {
            this.init();
          } else {
            this.snackbar = true;
            this.snackbartext = "An error occurred";
          }
        }
      );
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
  },
};
</script>
