<template>
  <v-container>
    <v-row>
      <div>
        <v-app-bar class="blue-grey darken-4 white--text" :elevation="0">
          <v-toolbar-title>
            <h1 v-if="info[1]">{{ info[1] }}</h1>
            <h1 v-else>Movie Name</h1>
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-icon
            v-if="userInfo && !isFavorite"
            right
            large
            dark
            style="cursor: pointer"
            @click="addFavorite()"
          >
            mdi-cards-heart-outline</v-icon
          >
          <v-icon
            v-if="userInfo && isFavorite"
            right
            large
            dark
            style="cursor: pointer"
            color="red"
            @click="deleteFavorite()"
          >
            mdi-cards-heart</v-icon
          >
        </v-app-bar>

        <v-alert
          shaped
          dark
          color="info"
          class="pb-0 mb-0"
          dense
          v-if="isAdmin"
        >
          <v-row justify="space-around" class="mb-0">
            <v-icon dense style="cursor: pointer" @click="add()"
              >mdi-account-edit-outline</v-icon
            >
            <v-icon dense style="cursor: pointer" @click="upload()"
              >mdi-tray-arrow-up</v-icon
            >
          </v-row>
        </v-alert>
      </div>

      <v-dialog
        max-width="600"
        v-model="showEditing"
        transition="dialog-bottom-transition"
      >
        <v-card>
          <v-toolbar color="primary" dark>Edit Movie Photos and Name</v-toolbar>
          <v-card>
            <v-card-text>
              <v-card-title>Edit</v-card-title>
              <v-card-text>
                <v-text-field
                  label="Movie name"
                  v-model="editedName"
                ></v-text-field>
              </v-card-text>

              <v-card-text>
                <v-item-group v-model="selected" multiple>
                  <v-row>
                    <v-col
                      v-for="(image, index) in images"
                      :key="index"
                      cols="12"
                      md="6"
                    >
                      <v-item v-slot="{ active, toggle }">
                        <v-img
                          :src="image"
                          height="150"
                          class="text-right pa-2"
                          @click="toggle"
                        >
                          <v-btn icon dark>
                            <v-icon>
                              {{ active ? "mdi-delete" : "mdi-delete-outline" }}
                            </v-icon>
                          </v-btn>
                        </v-img>
                      </v-item>
                    </v-col>
                  </v-row>
                </v-item-group>
              </v-card-text>
            </v-card-text>

            <v-alert dense outlined type="error" v-model="showError">
              An unknown error has occurred
            </v-alert>
          </v-card>
          <v-card-actions>
            <v-btn text @click="showEditing = false">Close</v-btn>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="Edit()"> Edit </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog
        max-width="600"
        v-model="showUpload"
        transition="dialog-bottom-transition"
      >
        <v-card>
          <v-toolbar color="primary" dark>Upload Photo</v-toolbar>
          <v-card>
            <v-card-text>
              <v-file-input
                accept="image/png, image/jpeg, image/bmp"
                placeholder="Pick a Photo"
                prepend-icon="mdi-camera"
                label="Photo"
                v-model="photoInfo"
              ></v-file-input>
            </v-card-text>

            <v-alert dense outlined type="error" v-model="showError">
              An unknown error has occurred
            </v-alert>
          </v-card>
          <v-card-actions>
            <v-btn text @click="showUpload = false">Close</v-btn>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="uploadPhoto()"> Upload </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-carousel
        cycle
        height="500"
        hide-delimiter-background
        show-arrows-on-hover
        delimiter-icon="mdi-minus"
      >
        <v-carousel-item v-for="(image, index) in images" :key="index">
          <v-sheet height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <v-img :src="image"></v-img>
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
    </v-row>
    <v-row v-if="start">
      <v-col cols="7">
        <MovieLines />
        <v-col>
          <DescriptionPage />
          <UserComment />
        </v-col>
      </v-col>
      <v-col cols="5" style="padding-left: 50px">
        <DetailRight> </DetailRight>
        <div class="mt-4">
          <MusicComponent />
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>



<script>
import {
  InfoByIDRequest,
  ObjectListRequest,
  FileUploadRequest,
  FileDeleteRequest,
  InfoRequest,
  UsernameRequest,
  UserLikeInfo,
} from "@/proto/moviedb_pb.js";
import { getFileExtension } from "@/utils/fileTools.js";
import DetailRight from "./DetailRight.vue";
import MovieLines from "./MovieLines.vue";
import DescriptionPage from "./DescriptionPage.vue";
import MusicComponent from "./MusicComponent.vue";
import UserComment from "./UserComment.vue";

export default {
  components: {
    DetailRight,
    MovieLines,
    DescriptionPage,
    MusicComponent,
    UserComment,
  },
  name: "DetailPage",

  data: () => ({
    prefix: "/public",
    ossPrefix: "https://movie-db.oss-eu-west-1.aliyuncs.com/",
    info: [],
    files: [],
    images: [],
    imagesPath: [],
    start: false,
    showError: false,
    showEditing: false,
    editedName: null,
    selected: [],
    showUpload: false,
    photoInfo: null,
    pList: [],
    isFavorite: false,
    userLikeList: [],
  }),

  created: function () {
    this.init();
    this.getAllImages();
  },
  methods: {
    init() {
      this.getInfo();
      this.getUserLikeList();
    },

    getInfo() {
      this.$backend.getInfoByID(
        new InfoByIDRequest().setId(this.$route.query.id),
        {},
        (err, response) => {
          console.log(response.array);
          this.$store.state.detailInfo = response.array;
          this.start = true;
          this.info = response.array;
          this.editedName = this.info[1];
        }
      );
    },

    getUserLikeList() {
      if (this.userInfo) {
        this.$backend.getUserLikes(
          new UsernameRequest().setUsername(this.userInfo),
          {},
          (err, response) => {
            this.userLikeList = response.array[0];
            this.userLikeList.forEach((e)=>{
              if(e.indexOf(this.info[0])>-1){
                this.isFavorite = true;
              }
              })
          }
        );
      }
    },

    getAllImages() {
      this.$backend.getOssObjectList(
        new ObjectListRequest()
          .setBucketname("movie-db")
          .setKeyprefix(this.$route.query.id + "/screenshot"),
        {},
        (err, response) => {
          console.log(response.array[0]);
          this.imagesPath = response.array[0];
          this.images = response.array[0].map(
            (elment) => this.ossPrefix + elment[0]
          );
        }
      );
    },
    add() {
      this.showError = false;
      this.showEditing = true;
    },
    Edit() {
      this.selected.forEach((e) => {
        this.pList.push(this.deleteOperation(this.imagesPath[e][0]));
      });
      this.pList.push(this.editNameOperation());
      Promise.all(this.pList).then(window.location.reload(true));
    },

    editNameOperation() {
      return new Promise(() => {
        this.$backend.updateByID(
          new InfoRequest()
            .setId(this.info[0])
            .setName(this.editedName)
            .setImdb(this.info[2])
            .setTomatoes(this.info[3])
        );
      });
    },

    deleteOperation(path) {
      return new Promise(() =>
        this.$backend.deleteFileFromOSS(
          new FileDeleteRequest().setFilepath(path),
          {},
          () => {}
        )
      );
    },

    upload() {
      this.showError = false;
      this.showUpload = true;
    },
    uploadPhoto() {
      this.getfileByBase64(this.photoInfo);
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
            .setObjectpath(this.info[0] + "/screenshot/")
            .setType(getFileExtension(this.photoInfo))
            .setContent(e.target.result),
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
    addFavorite() {
      this.$backend.insertUserLike(
        new UserLikeInfo().setUsername(this.userInfo).setId(this.info[0]),
        {},
        (err, response) => {
          if(response.array[0] == 1){
            this.isFavorite = true;
          }
        }
      );
    },
    deleteFavorite() {
       this.$backend.deleteUserLike(
        new UserLikeInfo().setUsername(this.userInfo).setId(this.info[0]),
        {},
        (err, response) => {
          if(response.array[0] == 1){
            this.isFavorite = false;
          }
        }
      );
    },
  },
  computed: {
    isAdmin() {
      if (localStorage.getItem("roles") == "admin") {
        return true;
      } else {
        return false;
      }
    },
    userInfo() {
      if (localStorage.getItem("username") == null) {
        return null;
      } else {
        return localStorage.getItem("username");
      }
    },
  },
};
</script>

