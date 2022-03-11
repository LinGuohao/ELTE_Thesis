<template>
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
            {{ detailInfo[3]  }}
          </v-progress-circular>
        </v-card-actions>
      </v-list-item-content>
    </v-list-item>
  </v-card>
</template>


<script>
export default {
  name: "DetailRight",
  data: () => ({
    infoJson: [],
  }),

  created: function () {
    this.getInfoJson();
  },

  methods: {
    getInfoJson() {
      this.$axios
        .get(this.$ossPrefix + this.detailInfo[0] + "/info.json")
        .then((res) => (this.infoJson = res.data));
    },
  },
  computed: {
    detailInfo() {
      return this.$store.state.detailInfo;
    },
  },
};
</script>