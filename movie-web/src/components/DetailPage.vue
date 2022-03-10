<template>
  <v-container>
    <h1>{{ info[1] }}</h1>
    <v-carousel
      cycle
      height="500"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item v-for="(image, index) in images" :key="index">
        <v-sheet height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <v-img :src= "image"></v-img>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
  </v-container>
</template>



<script>
import { InfoByIDRequest, ObjectListRequest } from "@/proto/moviedb_pb.js";
export default {
  name: "DetailPage",

  data: () => ({
    prefix: "/public",
    ossPrefix:"https://movie-db.oss-eu-west-1.aliyuncs.com/",
    info: [],
    files: [],
    images:[],
  }),

  created: function () {
    this.init();
    this.getAllImages();
  },
  methods: {
    init() {
      this.getInfo();
    },

    getInfo() {
      this.$backend.getInfoByID(
        new InfoByIDRequest().setId(this.$route.query.id),
        {},
        (err, response) => {
          console.log(response.array);
          this.info = response.array;
        }
      );
    },

    getAllImages() {
      this.$backend.getOssObjectList(
        new ObjectListRequest()
          .setBucketname("movie-db")
          .setKeyprefix(this.$route.query.id + "/screenshot"),
        {},
        (err, response) => {
         console.log(response.array[0]);
         this.images = response.array[0].map((elment)=>this.ossPrefix+(elment[0]));
        }
      );
    },
  },
};
</script>
