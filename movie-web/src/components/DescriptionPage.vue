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
        <v-toolbar color="primary" dark>Edit Description</v-toolbar>
        <v-card>
          <v-card-text>
            <v-card-title>Edit</v-card-title>

            <v-card-text>
              <v-text-field
                label="title"
                solo
                v-model="editedTitle"
                :value="editedTitle"
              ></v-text-field>
              <v-card-text>content</v-card-text>
              <v-textarea
                name="editedContent"
                solo
                auto-grow
                v-model="editedContent"
                color="teal"
              ></v-textarea>
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
    <v-alert border="top" color="red accent-1" elevation="2" v-if="start">
      <h3>{{ title }}</h3>
      <div class="text-wrapper" v-html="content.replace(/\n/g, '<br/>')"></div>
    </v-alert>

    <v-alert
      border="top"
      color="red accent-1"
      elevation="2"
      v-if="noDescription"
    >
      <div v-html="content.replace(/\n/g, '<br/>')"></div>
    </v-alert>
  </div>
</template>


<script>
import { FileUploadRequest } from "@/proto/moviedb_pb.js";
export default {
  name: "DescriptionPage",

  mounted: function () {
    this.getDescription();
  },
  data: () => ({
    title: null,
    content: null,
    start: false,
    noDescription: false,
    showError: false,
    showEditing: false,
    editedTitle: null,
    editedContent: null,
  }),

  methods: {
    getDescription() {
      this.$axios
        .get(this.$ossPrefix + this.detailInfo[0] + "/description.json")
        .then((res) => {
          this.title = res.data.title;
          this.content = res.data.content;
          //console.log(res.status);
          //console.log(this.content);
          this.editedContent = this.content
          this.editedTitle = this.title;
          this.start = true;
          //console.log(res);
        })
        .catch((error) => {
          this.noDescription = true;
          this.content = "No Infomation";
          console.log(error);
        });
    },
    add() {
      this.showError = false;
      this.showEditing = true;
    },
    Edit() {
      var jsonString = this.convertToJson(this.editedTitle, this.editedContent);
      //console.log(typeof(JSON.stringify(jsonString)));

      var path = this.detailInfo[0] + "/description.json";
      this.$backend.uploadToOSS(
        new FileUploadRequest()
          .setObjectname(path)
          .setContent(JSON.stringify(jsonString)),
        {},
        (err, response) => {
          if (response.array[0] == 1) {
                window.location.reload();
            }else{
                this.showError = true;
            }
        }
      );
    },
    convertToJson(editedTitle, editedContent) {
      editedContent = editedContent
        .replace(/\r\n/g, "<br>")
        .replace(/\n/g, "<br>")
        .replace(/"/g, '\\"');
      //console.log(editedContent);
      var jsonString =
        "{" +
        '"title":"' +
        editedTitle +
        '",\n' +
        '"content":"' +
        editedContent +
        '"}';
      return JSON.parse(jsonString);
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

<style>
.text-wrapper {
  word-wrap: break-word;
}
</style>










