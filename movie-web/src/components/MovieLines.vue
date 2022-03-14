<template>
  <v-card color="pink darken-3" dark flat outlined shaped>
    <v-window v-model="onboarding">
      <v-window-item v-for="n in length" :key="`card-${n}`">
        <v-card color="transparent" height="200">
          <v-row class="fill-height" align="center" justify="center">
            <v-card-text
              class="text-center"
              style="font-family: Times New Roman"
            >
              {{lines[n-1][1]}}
              <v-card-text class="text-center font-italic font-weight-light"
                > {{lines[n-1][2]}}</v-card-text
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
</template>

<script>
import { InfoByIDRequest } from "@/proto/moviedb_pb.js";
export default {
  name: "MovieLines",
  data: () => ({
    onboarding: 0,
    lines: [],
  }),
  mounted: function () {
    this.lines = this.$backend.getLines(
      new InfoByIDRequest().setId(this.detailInfo[0]),
      {},
      (err, response) => {this.lines = response.array[0];console.log(this.lines); }
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
  },
  computed: {
    detailInfo() {
      return this.$store.state.detailInfo;
    },
    length(){
      return this.lines.length
    }
  },
};
</script>