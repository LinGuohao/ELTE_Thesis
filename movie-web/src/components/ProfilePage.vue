<template>
  <v-container>
    <div class="pl-50 pr-50">
      <h1>{{ userName }}</h1>
      <h3>
        <font color="gray">{{ userInfo }}</font>
      </h3>
    </div>
    <v-divider dark></v-divider>
   
    <v-row>
      <v-col cols="6">
       <h3>My favorite movies</h3>
      </v-col>
      <v-col cols="6"> 
        <h3>My comments</h3>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "ProfilePage",
  computed: {
    userInfo() {
      if (localStorage.getItem("username") == null) {
        return null;
      } else {
        return localStorage.getItem("username");
      }
    },

    userName() {
      if (localStorage.getItem("username") == null) {
        return "Guest";
      } else {
        return localStorage.getItem("fullname");
      }
    },
  },
};
</script>
