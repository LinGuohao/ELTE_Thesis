<template>
  <v-container>
    <v-row v-if="info.length == movies.length">
      <template v-for="(n, index) in movies">
      <v-col  cols="4" v-if="searchMethod(info[index][1])" :key="index">
        <v-card height="400" class="mx-auto" elevation="10">
          <v-img
            class="white--text align-end"
            height="200px"
            :src="covers[index]"
          >
            <v-card-title>{{ info[index][1] }}</v-card-title>
          </v-img>

          <v-card-subtitle class="pb-0"> Director and Actors: </v-card-subtitle>

          <v-card-text class="text--primary pb-0">
            <div>{{ n.director }}</div>
            <div>{{ n.actors }}</div>
          </v-card-text>

          <v-card-subtitle class="pb-0"> Rating: </v-card-subtitle>
          <v-card-text class="text--primary pb-0">
            <div>
              IMDb: {{ info[index][2] }} Rotten Tomatoes: {{ info[index][3] }}
            </div>
          </v-card-text>

          <v-card-actions>
            <v-btn color="orange" @click="goDetail(info[index][0])">
              See more
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
      </template>
    </v-row>
  </v-container>
</template>

<script>
var google_protobuf_empty_pb = require("google-protobuf/google/protobuf/empty_pb.js");
import { InfoByIDRequest } from "@/proto/moviedb_pb.js";
//import  {AllMovieIDListResponse} from "@/proto/moviedb_pb.js"

export default {
  name: "HomePage",

  data: () => ({
    prefix: "",
    ossPrefix: "https://movie-db.oss-eu-west-1.aliyuncs.com/",
    items: [{ title: "Home" }],
    drawer: null,
    movies: [],
    info: [],
    covers: [],
    moviesInfo:[],
  }),

  created: function () {
    this.initPage();
  },
  methods: {
    initPage() {
      //this.respone = new AllMovieIDListResponse();
      this.$backend.getAllID(
        new google_protobuf_empty_pb.Empty(),
        {},
        (err, response) => {
          let pList = [];
          let infoList = [];
          let ret = response.toObject().replyList;
          ret.forEach((element) => {
            pList.push(this.getInfoJson(element.id));
            infoList.push(this.getInfo(element.id));
            this.covers.push(this.ossPrefix + element.id + "/cover.jpg");
          });
          Promise.all(pList).then((res) => {
            this.movies = res.map((item) => item.data);
            //console.log(this.movies);
          });
          Promise.all(infoList).then((res) =>{
            this.info = res.map((item) => item);
          })
        }
      );
    },

    getInfoJson(id) {
      return new Promise((reslove) => {
        this.$axios.get(this.ossPrefix + id + "/info.json").then((res) => {
          reslove(res);
        });
      });
    },

    getInfo(id) {
      return new Promise((reslove) =>
        this.$backend.getInfoByID(
          new InfoByIDRequest().setId(id),
          {},
          (err, response) => {
            reslove(response.array);
            //console.log(response.array);
            //this.info.push(response.array);

            //console.log(this.info);
          }
        )
      );
    },
    goDetail(mId) {
      //console.log(this.$router);
      this.$router.push({
        path: "/detail",
        query: {
          id: mId,
        },
      });
    },
    searchMethod(movieName){
      if(this.searchText == ""){
        return true
      }else{
        if(movieName.toLowerCase().indexOf(this.searchText.toLowerCase()) !== -1){
          return true;
        }else{
          return false;
        }

      }
    }
  },
  computed: {
    searchText(){
      return  this.$store.state.searchText;
    }
  }
};
</script>
