<template>
      <v-container>
        <v-row>
          <v-col v-for="n in numberOfID" :key="n" cols="4">
            <v-card height="400" class="mx-auto" elevation="10">
              <v-img
                class="white--text align-end"
                height="200px"
                src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
              >
                <v-card-title>Movie title</v-card-title>
              </v-img>

              <v-card-subtitle class="pb-0"> Director and 演员: </v-card-subtitle>

              <v-card-text class="text--primary pb-0">
                <div>Director name</div>
                <div>演员名字</div>
              </v-card-text>

               <v-card-subtitle class="pb-0" > 评分: </v-card-subtitle>
               <v-card-text class="text--primary pb-0">
                <div>IMDb: 9.7 烂番茄: 100</div>
              </v-card-text>

              <v-card-actions>
                <v-btn color="orange" text> See more </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
</template>

<script>
export default {
  name: "HomePage",

  data: () => ({
    items: [{ title: "Home" }],
    cards: ["Today"],
    links: [
      ["mdi-inbox-arrow-down", "Inbox"],
      ["mdi-send", "Send"],
      ["mdi-delete", "Trash"],
      ["mdi-alert-octagon", "Spam"],
    ],
    drawer: null,
    numberOfID: 10,
    movies: [{}]
    
  }),
};
</script>
