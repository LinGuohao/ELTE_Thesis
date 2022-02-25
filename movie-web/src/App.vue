<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title> Home </v-toolbar-title>
      <v-spacer></v-spacer>

      <v-btn>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </v-app-bar>
    <v-navigation-drawer app class="blue-grey darken-1">
      <v-divider></v-divider>
      <v-list nav>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="text-h6"> Log In </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item v-for="item in items" :key="item.title" link>
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <template v-slot:append>
        <div class="pa-2">
          <v-btn block> Logout </v-btn>
        </div>
      </template>
    </v-navigation-drawer>

    <v-main class="grey lighten-2">
      <v-container>
        <v-row>
          <v-col v-for="n in 24" :key="n" cols="4">
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
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",

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
  }),
};
</script>
