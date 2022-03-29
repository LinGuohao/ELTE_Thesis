<template>
  <div>
    <v-alert shaped dark color="info" class="pb-0 mb-0" dense v-if="isAdmin">
      <v-row justify="space-around" class="mb-0">
        <v-icon dense style="cursor: pointer" @click="add()"
          >mdi-plus-circle-outline</v-icon
        >
        <v-icon dense style="cursor: pointer" @click="del()"
          >mdi-delete-forever</v-icon
        >
      </v-row>
    </v-alert>

    <v-dialog
      max-width="600"
      v-model="showEditing"
      transition="dialog-bottom-transition"
    >
      <v-card>
        <v-toolbar color="primary" dark>Edit movie lines</v-toolbar>
        <v-card>
          <v-card-text>
            <v-card-title>Add new line</v-card-title>
            <v-card-text>Sentence</v-card-text>
            <v-card-text>
              <v-textarea
                name="sentence"
                solo
                auto-grow
                value=""
                v-model="sentence"
                color="teal"
              ></v-textarea>

              <v-text-field label="author" solo v-model="author"></v-text-field>
            </v-card-text>
          </v-card-text>

          <v-alert dense outlined type="error" v-model="showError">
            An unknown error has occurred
          </v-alert>
        </v-card>
        <v-card-actions>
          <v-btn text @click="showEditing = false">Close</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="addLine()"> Add </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog
      max-width="600"
      v-model="showDel"
      transition="dialog-bottom-transition"
    >
      <v-card>
        <v-toolbar color="primary" dark>Delete movie lines</v-toolbar>
        <v-card>
          <v-container fluid>
            <v-checkbox
              v-for="(line, index) in lines"
              :key="index"
              v-model="selected"
              :label="line[1]"
              :value="line[3]"
              dense
            ></v-checkbox>
          </v-container>
        </v-card>
        <v-card-actions>
          <v-btn text @click="showDel = false">Close</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="deleteLine()"> Delete </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-card color="pink darken-3" dark flat outlined shaped>
      <v-window v-model="onboarding">
        <v-window-item v-for="n in length" :key="`card-${n}`">
          <v-card color="transparent" height="200">
            <v-row class="fill-height" align="center" justify="center">
              <v-card-text
                class="text-center"
                style="font-family: Times New Roman"
              >
                {{ lines[n - 1][1] }}
                <v-card-text class="text-center font-italic font-weight-light">
                  {{ lines[n - 1][2] }}</v-card-text
                >
              </v-card-text>
            </v-row>
          </v-card>
        </v-window-item>
      </v-window>

      <v-card-actions class="justify-space-between">
        <v-btn text @click="prev">
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
        <v-item-group v-model="onboarding" class="text-center" mandatory>
          <v-item
            v-for="n in length"
            :key="`btn-${n}`"
            v-slot="{ active, toggle }"
          >
            <v-btn :input-value="active" icon @click="toggle">
              <v-icon>mdi-record</v-icon>
            </v-btn>
          </v-item>
        </v-item-group>
        <v-btn text @click="next">
          <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>

    <v-snackbar v-model="snackbar" :timeout="1000">
      {{ snackbartext }}
    </v-snackbar>
  </div>
</template>

<script>
import {
  InfoByIDRequest,
  LineList,
  deleteLineRequest,
} from "@/proto/moviedb_pb.js";
export default {
  name: "MovieLines",
  data: () => ({
    onboarding: 0,
    lines: [],
    showEditing: false,
    author: null,
    sentence: null,
    showError: false,
    showDel: false,
    selected: [],
    snackbar: false,
    snackbartext: null,
  }),
  mounted: function () {
    this.lines = this.$backend.getLines(
      new InfoByIDRequest().setId(this.detailInfo[0]),
      {},
      (err, response) => {
        this.lines = response.array[0];
        console.log(this.lines);
      }
    );
  },
  methods: {
    next() {
      this.onboarding =
        this.onboarding + 1 === this.length ? 0 : this.onboarding + 1;
    },
    prev() {
      this.onboarding =
        this.onboarding - 1 < 0 ? this.length - 1 : this.onboarding - 1;
    },
    add() {
      this.showError = false;
      this.showEditing = true;
    },
    addLine() {
      var addSentence = this.sentence.trim();
      if (addSentence != null) {
        this.$backend.insertLine(
          new LineList()
            .setId(this.detailInfo[0])
            .setSentence(addSentence)
            .setAuthor(this.author),
          {},
          (err, response) => {
            if (response.array[0] == 1) {
              this.showEditing = false;
              window.location.reload();
            } else {
              this.showError = true;
              console.log("Insert Line error");
            }
          }
        );
      }
    },
    deleteLine() {
      for (var line in this.selected) {
        this.$backend.deleteLine(
          new deleteLineRequest()
            .setLineid(this.selected[line])
            .setId(this.detailInfo[0]),
          {},
          (err, response) => {
            if (response.array[0] == -1) {
              console.log("An error occurred");
            }
          }
        );
      }
      this.snackbartext = "Success";
      window.location.reload();
      this.snackbar = true;
    },
    del() {
      this.showDel = true;
    },
  },
  computed: {
    detailInfo() {
      return this.$store.state.detailInfo;
    },
    length() {
      return this.lines.length;
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