<template>
  <v-row justify="center" class="mt-5">
    <v-col cols="12" sm="10" md="8" lg="6">
      <v-card ref="form" color="amber accent-4" elevation="18">
        <v-card-text>
          <v-text-field
            ref="username"
            v-model="username"
            :rules="[
              () => !!username || 'This field is required',
              addressEmail() || 'Must be in Email format',
            ]"
            label="User name (Email)"
            placeholder="12345@test.com"
            required
            @blur="removeSpaces()"
          ></v-text-field>
          <v-text-field
            ref="password"
            v-model="password"
            :rules="[() => !!password || 'This field is required']"
            label="Password"
            required
            type="password"
          ></v-text-field>

          <v-alert dense outlined type="error" v-model="showErrorPassword">
            Incorrect username or password
          </v-alert>
        
          <v-alert dense outlined type="error" v-model="showErrorUsername">
            Username does not exist, please try again
          </v-alert>
          <v-alert dense type="info" outlined>
            If you don't have an account,
            <strong @click="goSignUp()" style="cursor: pointer">Sign Up</strong>
          </v-alert>
        </v-card-text>
        <v-divider class="mt-12"></v-divider>
        <v-card-actions>
          <v-btn text @click="goHome()"> Cancel </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="LogIn()"> Log In </v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { VerificationRequest } from "@/proto/moviedb_pb.js";
export default {
  data: () => ({
    username: null,
    password: null,
    showErrorPassword: false,
    showSuccess: false,
    showErrorUsername: false,
  }),
  computed: {},
  watch: {},
  methods: {
    addressEmail() {
      var reg = /^[0-9a-zA-Z_.-]+[@][0-9a-zA-Z_.-]+([.][a-zA-Z]+){1,2}$/;
      if (this.username) {
        if (reg.test(this.username)) {
          this.isUsername = true;
          return true;
        } else {
          this.isUsername = false;
          return false;
        }
      }
    },

    removeSpaces() {
      if (this.username) {
        this.username = this.username.trim();
      }
    },

    LogIn() {
        this.showErrorPassword = false;
        this.showErrorUsername = false;
      if (this.username && this.password) {
        this.$backend.authenticateUser(
          new VerificationRequest()
            .setUsername(this.username)
            .setPassword(this.password),
          {},
          (err, response) => {
            if(response.array[0]=="wrong"){
                this.showErrorPassword = true;
            }else if(response.array[0]=="null"){
                this.showErrorUsername = true;
            }else{
                localStorage.setItem('username',response.array[0]);
                localStorage.setItem('fullname',response.array[2]);
                localStorage.setItem('roles',response.array[3]);
                this.goHome();
                window.location.reload();
            }
          }
        );
      } else {
        if (!this.password) {
          this.$refs.password.focus();
          this.$refs.password.blur();
        }
        if (!this.username) {
          this.$refs.username.focus();
          this.$refs.username.blur();
        }
      }
    },

    goHome() {
      this.$router
        .push({
          path: "/",
        })
        .catch((err) => err);
    },

    goSignUp() {
      this.$router
        .push({
          path: "/signup",
        })
        .catch((err) => err);
    },
  },

  

 
};
</script>