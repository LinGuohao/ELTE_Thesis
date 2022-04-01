<template>
  <div>
    <v-alert shaped dark color="info" class="pb-0 mb-0" dense v-if="isAdmin">
      <v-row justify="space-around" class="mb-0">
        <v-icon dense style="cursor: pointer" @click="add()"
          >mdi-account-edit-outline</v-icon
        >
      </v-row>
    </v-alert>
    <v-dialog
      max-width="600"
      v-model="showEditing"
      transition="dialog-bottom-transition"
    >
      <v-card>
        <v-toolbar color="primary" dark>Edit detail</v-toolbar>
        <v-card>
          <v-card-text>
            <v-card-title>Edit</v-card-title>
            <v-card-text>
              <v-text-field
                label="Director"
                v-model="editedDirector"
              ></v-text-field>
              <v-text-field
                label="Actors"
                v-model="editedActors"
              ></v-text-field>
              <v-select
                v-model="editedIMDb"
                :items="IMDblist"
                label="IMDb"
                persistent-hint
              ></v-select>
              <v-subheader class="pl-0 pb-10"> Rotten tomatoes </v-subheader>
              <v-slider
                v-model="editedTomatoes"
                thumb-label="always"
                color="red darken-4"
                track-color="orange darken-2"
              ></v-slider>
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
    <v-card
      class="mx-auto amber darken-1"
      max-width="400"
      elevation="24"
      outlined
      shaped
    >
      <v-list-item>
        <v-list-item-icon>
          <v-icon>mdi-human-male-board</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>Director</v-list-item-title>
          <v-list-item-subtitle>{{ infoJson.director }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-list-item two-line>
        <v-list-item-icon>
          <v-icon>mdi-account-group</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>Actors</v-list-item-title>
          <v-list-item-subtitle>{{ infoJson.actors }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-list-item three-line>
        <v-list-item-content>
          <v-list-item-title>Media rating</v-list-item-title>
          <v-card-actions class="pa-4">
            IMDb
            <v-spacer></v-spacer>
            <span class="grey--text text--lighten-2 text-caption mr-2">
              {{ detailInfo[2] }}
            </span>
            <v-rating
              background-color="white"
              color="red"
              dense
              half-increments
              size="18"
              readonly
              :value="detailInfo[2] / 2"
              large
            ></v-rating>
          </v-card-actions>

          <v-card-actions class="pa-4">
            Rotten tomatoes
            <v-spacer></v-spacer>
            <v-progress-circular
              :rotate="360"
              :size="100"
              :width="15"
              :value="detailInfo[3]"
              color="teal"
            >
              {{ detailInfo[3] }}
            </v-progress-circular>
          </v-card-actions>
        </v-list-item-content>
      </v-list-item>
    </v-card>
  </div>
</template>


<script>
import { TextUploadRequest, InfoRequest } from "@/proto/moviedb_pb.js";
export default {
  name: "DetailRight",
  data: () => ({
    infoJson: [],
    showEditing: false,
    showError: false,
    editedDirector: null,
    editedActors: null,
    editedIMDb: null,
    editedTomatoes: null,
    IMDblist: [],
  }),

  created: function () {
    this.init();
    this.getInfoJson();
  },

  methods: {
    init() {
      var index = 0;
      for (var i = 0.0; i < 10.0; i = i + 0.1) {
        this.IMDblist[index] = i.toFixed(1);
        index++;
      }
    },
    getInfoJson() {
      this.$axios
        .get(this.$ossPrefix + this.detailInfo[0] + "/info.json")
        .then((res) => {
          this.infoJson = res.data;
          this.editedDirector = this.infoJson.director;
          this.editedActors = this.infoJson.actors;
          this.editedTomatoes = this.detailInfo[3];
          this.editedIMDb = this.detailInfo[2];
        });
    },
    add() {
      this.showError = false;
      this.showEditing = true;
    },
    Edit() {
      var jsonString = this.convertToJson(
        this.editedDirector,
        this.editedActors
      );
      var path = this.detailInfo[0] + "/info.json";

      this.$backend.updateByID(
        new InfoRequest()
          .setId(this.detailInfo[0])
          .setName(this.detailInfo[1])
          .setImdb(this.editedIMDb)
          .setTomatoes(this.editedTomatoes),
        {}
      );

      this.$backend.uploadTextToOSS(
        new TextUploadRequest().setObjectname(path).setContent(jsonString),
        {},
        (err, response) => {
          if (response.array[0] == 1) {
            window.location.reload(true);
          } else {
            this.showError = true;
          }
        }
      );
    },
    convertToJson(directorInfo, actorsInfo) {
      var jsonString =
        "{" +
        '"director":"' +
        directorInfo +
        '",\n' +
        '"actors":"' +
        actorsInfo +
        '"}';
      return jsonString;
    },
  },
  computed: {
    detailInfo() {
      return this.$store.state.detailInfo;
    },
    isAdmin() {
      if (localStorage.getItem("roles") == "admin") {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>