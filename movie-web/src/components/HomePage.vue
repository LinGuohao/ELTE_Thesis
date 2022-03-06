<template>
  <v-container>
    <v-row>   
      <v-col v-for="n in movies" :key="n" cols="4">
        {{getInfo(n)}}
        <v-card height="400" class="mx-auto" elevation="10">
          <v-img
            class="white--text align-end"
            height="200px"
            src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
          >
            <v-card-title>test</v-card-title>
          </v-img>

          <v-card-subtitle class="pb-0"> Director and Actors: </v-card-subtitle>

          <v-card-text class="text--primary pb-0">
            <div>Director name</div>
            <div>演员名字</div>
          </v-card-text>

          <v-card-subtitle class="pb-0"> Rating: </v-card-subtitle>
          <v-card-text class="text--primary pb-0">
            <div>IMDb: {{this.info.IMDb}} 烂番茄: 100</div>
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
    infoJson: null,
    info: null,
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
          this.movies = response.toObject().replyList.map((x) => x.id);
          console.log(this.movies);
        }
      );
    },

      getInfoJson(id) {
         this.$axios.get(this.prefix + id + "/info.json").then((res) => {
         this.infoJson = res;
        console.log(this.infoJson);
      });
    },

    getInfo(id) {
      this.client.getInfoByID(
        new InfoByIDRequest().setId(id),
        {},
        (err, response) => {
          this.info = response.toObject();
        }
      );
      return this.response;
    },
  },
};
</script>
