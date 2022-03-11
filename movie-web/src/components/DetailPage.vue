<template>
  <v-container>
    <v-row>
      <h1>{{ info[1] }}</h1>
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
      <v-col cols="8">
        <MovieLines/>
      </v-col>

      <v-col cols="4">
        <DetailRight > </DetailRight>
      </v-col>
    </v-row>
  </v-container>
</template>



<script>
import { InfoByIDRequest, ObjectListRequest } from "@/proto/moviedb_pb.js";
import DetailRight from "./DetailRight.vue";
import MovieLines from "./MovieLines.vue";
export default {
  components : {DetailRight,MovieLines},
  name: "DetailPage",

  data: () => ({
    prefix: "/public",
    ossPrefix: "https://movie-db.oss-eu-west-1.aliyuncs.com/",
    info: [],
    files: [],
    images: [],
    start: false,
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
          this.$store.state.detailInfo = response.array;
          this.start = true;
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
          this.images = response.array[0].map(
            (elment) => this.ossPrefix + elment[0]
          );
        }
      );
    },
  },
};
</script>
