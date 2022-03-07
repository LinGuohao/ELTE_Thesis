<template>
  <v-container>
    <v-row v-if="info.length== movies.length">
      <v-col v-for="(n, index) in movies" :key="index" cols="4">
        <v-card height="400" class="mx-auto" elevation="10">
          <v-img
            class="white--text align-end"
            height="200px"
            :src= "cover[index]"
          >
            <v-card-title>{{info[index][1]}}</v-card-title>
          </v-img>

          <v-card-subtitle class="pb-0"> Director and Actors: </v-card-subtitle>

          <v-card-text class="text--primary pb-0">
            <div>{{ n.director }}</div>
            <div>{{ n.actors }}</div>
          </v-card-text>

          <v-card-subtitle class="pb-0"> Rating: </v-card-subtitle>
          <v-card-text class="text--primary pb-0">
            <div>IMDb: {{info[index][2]}} Rotten Tomatoes: {{info[index][3]}}</div>
          </v-card-text>

          <v-card-actions>
            <v-btn color="orange" text> See more </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
var google_protobuf_empty_pb = require("google-protobuf/google/protobuf/empty_pb.js");
import { MoviedbServiceClient } from "@/proto/moviedb_grpc_web_pb.js";
import { InfoByIDRequest } from "@/proto/moviedb_pb.js";
//import  {AllMovieIDListResponse} from "@/proto/moviedb_pb.js"

export default {
  name: "HomePage",

  data: () => ({
    prefix: "",
    items: [{ title: "Home" }],
    cards: ["Today"],
    links: [
      ["mdi-inbox-arrow-down", "Inbox"],
      ["mdi-send", "Send"],
      ["mdi-delete", "Trash"],
      ["mdi-alert-octagon", "Spam"],
    ],
    drawer: null,
    numberOfID: 10,
    movies: [],
    info: [],
    cover:[],
  }),

  created: function () {
    this.client = new MoviedbServiceClient("http://localhost:9080", null, null);
    this.initPage();
  },
  methods: {
    initPage() {
      //this.respone = new AllMovieIDListResponse();
      this.client.getAllID(
        new google_protobuf_empty_pb.Empty(),
        {},
        (err, response) => {
          let pList = [];
          let ret = response.toObject().replyList;
          ret.forEach((element) => {
            pList.push(this.getInfoJson(element.id));
            this.getInfo(element.id);
            this.cover.push(this.prefix + element.id + "/cover.jpg");
            //console.log(this.info);
          });
          Promise.all(pList).then((res) => {
            this.movies = res.map((item) => item.data);
            //console.log(this.movies);
          });
        }
      );
    },

    getInfoJson(id) {
      return new Promise((reslove) => {
        this.$axios.get(this.prefix + id + "/info.json").then((res) => {
          reslove(res);
        });
      });
    },

    getInfo(id) {
      this.client.getInfoByID(
        new InfoByIDRequest().setId(id),
        {},
        (err, response) => {
          //console.log(response.array);
          this.info.push(response.array)
        }
      );
    },
  },
};
</script>
