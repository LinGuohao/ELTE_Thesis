<template>
  <div>
    <v-alert
      shaped
      dark
      color="info"
      class="pb-0 mb-0"
      dense
      v-if="userName != 'Guest'"
    >
      <v-row justify="space-around" class="mb-0">
        <v-icon dense style="cursor: pointer" @click="add()"
          >mdi-account-edit-outline</v-icon
        >
      </v-row>
    </v-alert>

    <v-dialog
      max-width="600"
      v-model="showEditing"
      transition="dialog-bottom-transition"
    >
      <v-card>
        <v-toolbar color="primary" dark>Add a comment</v-toolbar>
        <v-card>
          <v-card-title>content</v-card-title>
          <v-card-text>
            <v-textarea
              name="editedContent"
              solo
              auto-grow
              v-model="editedContent"
              color="teal"
            ></v-textarea>
          </v-card-text>

          <v-alert dense outlined type="error" v-model="showError">
            An unknown error has occurred
          </v-alert>
        </v-card>
        <v-card-actions>
          <v-btn text @click="showEditing = false">Close</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="addComment()"> Comment </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-card color="blue lighten-3" flat outlined shaped>
      <v-list-item-title v-if="commentsList.length == 0" style="color: black">
        No Comment yet
      </v-list-item-title>
        <v-list v-for="(item, index) in commentsList" :key="index">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title style="font-weight: 800" v-html="item[1]">
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
    </v-card>

     <v-snackbar v-model="snackbar" :timeout="1000">
      {{ snackbartext }}
    </v-snackbar>
  </div>
</template>
<script>
import { InfoByIDRequest, CommentInfo } from "@/proto/moviedb_pb.js";
export default {
  name: "UserComment",
  data: () => ({
    commentsList: [],
    showEditing: false,
    showError: false,
    editedContent: null,
    snackbartext : null,
    snackbar:false,
  }),
  created: function () {
    this.init();
  },
  methods: {
    init() {
      this.$backend.getCommentByMovieID(
        new InfoByIDRequest().setId(this.detailInfo[0]),
        {},
        (err, response) => {
          this.commentsList = response.array[0];
          console.log(this.commentsList);
        }
      );
    },
    add() {
      this.showEditing = true;
      this.showError = false;
    },
    addComment() {
      if (this.editedContent) {
        console.log(this.editedContent);
        this.$backend.insertComment(
          new CommentInfo()
            .setCommentid("-1")
            .setUsername(this.userInfo)
            .setMovieid(this.detailInfo[0])
            .setContent(this.editedContent),
          {},
          (err, response) => {
            if (response.array[0] == 1) {
              window.location.reload(true);
            } else {
              this.showError = true;
            }
          }
        );
      } else {
        this.showEditing = false;
      }
    },
    deleteComment(item) {
      this.$backend.deleteComment(new InfoByIDRequest().setId(item),{},(err,response)=>{
          if(response.array[0] == 1){
              this.init();
          }else{
              this.snackbar = true;
              this.snackbartext = "An error occurred"
          }
      })
    },
    showDelete(username) {
      if (username == this.userInfo || this.isAdmin) {
        return true;
      } else {
        return false;
      }
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
    detailInfo() {
      return this.$store.state.detailInfo;
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

<style scoped>
</style>