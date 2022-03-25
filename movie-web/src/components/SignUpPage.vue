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
            ref="fullname"
            v-model="fullname"
            :rules="[() => !!fullname || 'This field is required']"
            label="Full Name"
            placeholder="John Doe"
            required
            value=" "
            @blur="removeSpaces()"
          ></v-text-field>
          <v-text-field
            ref="password"
            v-model="password"
            :rules="[
              () => !!password || 'This field is required',
              checkPassword() ||
                'Password must contain numbers and letters and be 6-16 characters long',
            ]"
            label="Password"
            required
            type="password"
          ></v-text-field>

          <v-text-field
            ref="repassword"
            v-model="repassword"
            :rules="[
              () => !!repassword || 'This field is required',
              checkRepassword() || 'Password does not match',
            ]"
            label="Repeat password"
            required
            type="password"
          ></v-text-field>

           <v-alert dense type="info" outlined v-model="showInfo">
           If you already have an account, <strong @click="goLogin()" style="cursor: pointer">Log In</strong> 
          </v-alert>

          <v-alert dense text type="success" v-model="showSuccess">
            Account <strong>{{ this.username }}</strong> created successfully
            <strong style="cursor: pointer" @click="goLogin()">Log in</strong>
          </v-alert>

          <v-alert dense outlined type="error" v-model="showRepeat">
            Account <strong>{{ this.username }}</strong> already exists, Please
            change and try again
          </v-alert>
        </v-card-text>
        <v-divider class="mt-12"></v-divider>
        <v-card-actions>
          <v-btn text @click="goHome()"> Cancel </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="submit"> Sign Up </v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { UserInfo } from "@/proto/moviedb_pb.js";
export default {
  data: () => ({
    username: null,
    fullname: null,
    password: null,
    repassword: null,
    formHasErrors: false,
    isPassword: false,
    isUsername: false,
    showSuccess: false,
    showRepeat: false,
    showInfo: true
  }),
  computed: {
    form() {
      return {
        username: this.name,
        fullname: this.address,
        password: this.city,
      };
    },
  },
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
    checkPassword() {
      var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
      if (this.password) {
        if (reg.test(this.password)) {
          this.isPassword = true;
          return true;
        } else {
          this.isPassword = false;
          return false;
        }
      }
    },
    checkRepassword() {
      if (this.repassword == this.password) {
        return true;
      } else {
        return false;
      }
    },
    submit() {
      this.showSuccess = false;
      this.showRepeat = false;
      if (
        this.username != null &&
        this.fullname != null &&
        this.password != null &&
        this.isUsername &&
        this.isPassword &&
        this.checkRepassword()
      ) {
        this.$backend.insertUser(
          new UserInfo()
            .setUsername(this.username)
            .setPassword(this.password)
            .setFullname(this.fullname)
            .setRoles("user"),
          {},
          (err, response) => {
            if (response.array[0] == -1) {
              this.showRepeat = true;
            } else {
              this.showSuccess = true;
            }
          }
        );
      } else {
        if (!this.repassword) {
          this.$refs.repassword.focus();
          this.$refs.repassword.blur();
        }
        if (!this.password) {
          this.$refs.password.focus();
          this.$refs.password.blur();
        }
        if (!this.fullname) {
          this.$refs.fullname.focus();
          this.$refs.fullname.blur();
        }

        if (!this.username) {
          this.$refs.username.focus();
          this.$refs.username.blur();
        }
      }
    },

    removeSpaces() {
      if (this.username) {
        this.username = this.username.trim();
      }
      if (this.fullname) {
        this.fullname = this.fullname.trim();
      }
    },

    goHome() {
      this.$router
        .push({
          path: "/",
        })
        .catch((err) => err);
    },

    goLogin() {
      this.$router.push({ path: "/login" }).catch((err) => err);
    },
  },
};
</script>