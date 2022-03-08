<template>
  <v-container>
    <h1>{{info[1]}}</h1>
    <v-carousel
      cycle
      height="500"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item v-for="(slide, i) in slides" :key="i">
        <v-sheet :color="colors[i]" height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <div class="text-h2">{{ slide }} Slide</div>
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
    info: [],
    colors: [
      "indigo",
      "warning",
      "pink darken-2",
      "red lighten-1",
      "deep-purple accent-4",
    ],
    slides: ["First", "Second", "Third", "Fourth", "Fifth"],
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
    console.log(this.$route);
  },
};
</script>
