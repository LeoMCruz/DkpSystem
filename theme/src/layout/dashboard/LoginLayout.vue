<template>
  <div class="wrapper">
    <div class="main-panel">
      <v-card class="centro" dark>
        <v-container>
          <div class="esquerda">
            <img src="img\anarchy(1).png" alt="" />
          </div>
        </v-container>
        <v-card-text dark>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="Login"
                v-model="username"
                filled
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="Password"
                v-model="password"
                type="password"
                filled
              ></v-text-field>
            </v-col>
          </v-row>

          <div class="text-center">
            <v-btn rounded color="primary" @click="entrar" dark> Enter</v-btn>
          </div>
          <a href="http://meuvo.com">New here? Register</a>
        </v-card-text>
      </v-card>
    </div>
  </div>
</template>
<style lang="scss"></style>
<script>
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import api from "../../services/api";
export default {
  components: {
    ContentFooter,
    DashboardContent,
  },
  data() {
    return {
      password: "",
      username: "",
      invalid: false,
    };
  },
  methods: {
    async entrar() {
      api
        .post("/oauth/token", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          this.$session.start();
          this.$session.set("jwt", response.data.accessToken);
          //TODO: Redirect to another page
        })
        .catch((error) => {
          console.log(error);
          this.invalid = true;
          //TODO: tokern not valid
        });
    },
  },
};
</script>
<style scoped>
.centro {
  background-color: white;
  opacity: 0.7;
  border-color: transparent;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.esquerda {
  position: fixed;
  top: 50%;
  right: -200%;
  left: 2%;
  transform: translate(-50%, -50%);
}
</style>
