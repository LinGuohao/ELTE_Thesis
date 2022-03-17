<template>
  <v-card>
    <v-img
      :src="$ossPrefix + detailInfo[0] + '/cover.jpg'"
      height="200px"
    ></v-img>

    <v-card-title>{{ clicked[2] }}</v-card-title>

    <v-card-subtitle>{{ clicked[3] }}</v-card-subtitle>

    <v-card-actions>
      <v-spacer></v-spacer>

      <v-btn icon @click="show = !show">
        <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
      </v-btn>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-card-text v-on:click="changeMusic(n)"
          v-for="(n, index) in musicList"
          :key="index"
          class="mt-0 mb-0 pb-1 pt-0" style="cursor : pointer"
        >
          {{ n[2] }}
        </v-card-text>
      </div>
    </v-expand-transition>
  </v-card>
</template>

<script>
import { InfoByIDRequest } from "@/proto/moviedb_pb.js";
export default {
  data: () => ({
    show: false,
    musicInfo: [],
    clicked: [],
  }),

  created: function () {
    this.initMusicInfo();
  },
  methods: {
    createMusicInfo(address, musicName, artist) {
      var object = new Object();
      object.address = address;
      object.musicName = musicName;
      object.artist = artist;
    },

    initMusicInfo() {
      this.musicInfo = this.$backend.getMusics(
        new InfoByIDRequest().setId(this.detailInfo[0]),
        {},
        (err, response) => {
          this.musicInfo = response.array[0];
          this.clicked = this.musicInfo[0];
          //console.log(Object.keys(this.clicked));
          //console.log(this.clicked);
        }
      );
    },
    changeMusic(music){
        this.clicked = music
    }
  },
  computed: {
    detailInfo() {
      return this.$store.state.detailInfo;
    },
    musicList() {
      var musicList = [];
      for (var i = 0; i < this.musicInfo.length; i++) {
        if (this.musicInfo[i][1] != this.clicked[1]) {
          //console.log(this.clicked[1]);
          musicList.push(this.musicInfo[i]);
          //console.log(this.musicInfo[i]);
        }
      }
      //console.log(musicList);
      return musicList;
    },
  },
};
</script>