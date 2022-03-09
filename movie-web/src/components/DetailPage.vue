<template>
  <v-container>
    <h1>{{ info[1] }}</h1>
    <v-carousel
      cycle
      height="500"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item v-for="(file, i) in files" :key="i">
        <v-sheet height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <v-img src= ""></v-img>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
  </v-container>
</template>



<script>
import { MoviedbServiceClient } from "@/proto/moviedb_grpc_web_pb.js";
import { InfoByIDRequest } from "@/proto/moviedb_pb.js";
export default {
  name: "DetailPage",
  
  data: () => ({
    prefix: "/public" ,
    info: [],
    files: [],
  }),

  created: function () {
    this.client = new MoviedbServiceClient("http://localhost:9080", null, null);
    this.init();
  },
  methods: {
    init() {
      this.getInfo();
    },

    getInfo() {
      this.client.getInfoByID(
        new InfoByIDRequest().setId(this.$route.query.id),
        {},
        (err, response) => {
          console.log(response.array);
          this.info = response.array;
        }
      );
    },
  },

  mounted() {
     this.files = require.context('../../public/12345/screenshot', false,).keys();
     console.log(this.files, '---');
  },
};
</script>
