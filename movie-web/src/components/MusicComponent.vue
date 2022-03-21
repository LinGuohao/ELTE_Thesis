<template>
  <div>
    <v-card v-if="noMusic == false">
      <v-img
        :src="$ossPrefix + detailInfo[0] + '/cover.jpg'"
        height="200px"
      ></v-img>

      <v-card-title>{{ clicked[2] }}</v-card-title>

      <v-card-subtitle>{{ clicked[3] }}</v-card-subtitle>

      <v-slider
        v-model="progress"
        @mousedown="down"
        @mouseup="up"
        :color="'orange darken-3'"
      ></v-slider>
      <v-btn icon color="pink" v-on:click="changeStatus()">
        <v-icon>{{ musicIcon }}</v-icon>
      </v-btn>
      <v-chip class="ma-2" color="blue lighten-3"> {{ timestamp }} </v-chip>
      <v-card-actions>
        <v-spacer></v-spacer>

        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>

          <v-card-text
            v-on:click="changeMusic(n)"
            v-for="(n, index) in musicList"
            :key="index"
            class="mt-0 mb-0 pb-1 pt-0"
            style="cursor: pointer"
          >
            {{ n[2] }}
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
    <v-card v-if="noMusic == true">
      <v-img
        :src="$ossPrefix + detailInfo[0] + '/cover.jpg'"
        height="200px"
      ></v-img>
      <v-card-title>No music</v-card-title>
    </v-card>
  </div>
</template>
    
<script>
import { InfoByIDRequest } from "@/proto/moviedb_pb.js";
export default {
  data: () => ({
    show: false,
    musicInfo: [],
    clicked: [],
    audio: new Audio(),
    musicIcon: "mdi-play",
    musicLength: 0,
    progress: 0,
    currentTime: 0,
    noMusic: false,
    isDown: false,
  }),

  created: function () {
    this.initMusicInfo();
    //this.audio.autoplay = true;
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
          if (this.musicInfo.length != 0) {
            this.noMusic = false;
            this.clicked = this.musicInfo[0];
            this.prepareMusic(this.$ossPrefix + this.clicked[1]);
          } else {
            this.noMusic = true;
          }
        }
      );
    },
    changeMusic(music) {
      this.clicked = music;
      this.prepareMusic(this.$ossPrefix + this.clicked[1]);
      this.audio.play();
      this.musicIcon = "mdi-pause";
    },
    changeStatus() {
      if (this.isPasued()) {
        this.musicIcon = "mdi-pause";
        this.audio.play();
      } else {
        this.musicIcon = "mdi-play";
        this.audio.pause();
      }
    },

    isPasued() {
      return this.audio.paused;
    },

    loadingFinish() {
      this.musicLength = this.audio.duration;
    },

    prepareMusic(src) {
      this.audio.pause();
      this.audio.src = src;
      this.audio.load();
      //this.audio.play();
      //console.log(this.audio.src);
      //console.log(this.audio.duration);
      //console.log(this.clicked);
      this.audio.addEventListener("canplay", () => this.loadingFinish());
      this.audio.addEventListener("timeupdate", () => this.update());
      this.audio.addEventListener("ended", () => this.endMusic());
    },

    update() {
      this.currentTime = this.audio.currentTime;
      this.progress = (this.currentTime / this.musicLength) * 100;
      //console.log(this.progress);
    },

    formatTime(value) {
      let second = 0;
      let minute = 0;
      minute = parseInt(value / 60);
      second = parseInt(value % 60);
      // 补0
      minute = minute < 10 ? "0" + minute : minute;
      second = second < 10 ? "0" + second : second;
      return minute + ":" + second;
    },

    down() {
      this.isDown = true;
      this.musicIcon = "mdi-play";
      this.audio.pause();
    },
    up() {
      this.isDown = false;
      this.audio.currentTime = (this.musicLength / 100) * this.progress;
      this.audio.play();
      this.musicIcon = "mdi-pause";
    },

    endMusic() {
      this.musicList.push(this.clicked);
      this.clicked = this.musicList.shift();
      //console.log(this.clicked);
      this.prepareMusic(this.$ossPrefix + this.clicked[1]);
      this.audio.play();
      this.musicIcon = "mdi-pause";
    },
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

    timestamp() {
      if (this.isDown == false) {
        return (
          this.formatTime(this.currentTime) +
          "/" +
          this.formatTime(this.musicLength)
        );
      } else {
        return (
          this.formatTime((this.musicLength / 100) * this.progress) +
          "/" +
          this.formatTime(this.musicLength)
        );
      }
    },
  },

  destroyed: function () {
    this.musicIcon = "mdi-play";
    this.audio.pause();
  },
};
</script>