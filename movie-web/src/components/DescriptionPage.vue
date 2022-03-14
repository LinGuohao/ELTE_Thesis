<template>
  <div>
    <v-alert border="top" color="red accent-1" elevation="2" v-if="start">
      <h3>{{ title }}</h3>
      <div v-html="content.replace(/\n/g, '<br/>')"></div>
    </v-alert>
  </div>
</template>


<script>
export default {
  name: "DescriptionPage",

  mounted: function () {
    this.getDescription();
  },
  data: () => ({
    title: null,
    content: null,
    start: false,
  }),

  methods: {
    getDescription() {
      this.$axios
        .get(this.$ossPrefix + this.detailInfo[0] + "/description.json")
        .then((res) => {
          this.title = res.data.title;
          this.content = res.data.content;
          //console.log(res.status);
          this.start = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  computed: {
    detailInfo() {
        
      return this.$store.state.detailInfo;
    },
  },
};
</script>










