<template>
  <div class="wrapper">
    <v-dialog v-model="confirmSave" persistent max-width="290" dark>
      <v-card>
        <v-card-title class="text-h5"> Terms and Agreement </v-card-title>
        <v-card-text
          >When you click on "Agree" you are selling your soul to
          Nabbu.</v-card-text
        >
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="confirmSave = false">
            Disagree
          </v-btn>
          <v-btn color="green darken-1" text @click="salvarUsuario">
            Agree
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="janelaRegistro" persistent max-width="600px" dark>
      <v-card>
        <v-card-title>
          <span class="text-h5">User Profile</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  v-model="nome"
                  label="Character Name*"
                  hint="Put your main character name here"
                  required
                ></v-text-field>
              </v-col>
              <v-col sm="6" md="4">
                <v-text-field
                  v-model="level"
                  label="Level*"
                  required
                ></v-text-field>
              </v-col>

              <v-col sm="6" md="4">
                <v-text-field
                  v-model="role"
                  label="Class*"
                  required
                ></v-text-field>
              </v-col>

              <v-col sm="3" md="6">
                <v-text-field
                  v-model="email"
                  label="Email*"
                  required
                ></v-text-field>
              </v-col>
              <v-col>
                <v-text-field
                  v-model="senha"
                  label="Password*"
                  type="password"
                  required
                ></v-text-field>
              </v-col>
              <v-col sm="6">
                <v-select
                  v-model="select"
                  :items="clanList"
                  filled
                  label="Clan"
                  dark
                ></v-select>
              </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="janelaRegistro = false">
            Close
          </v-btn>
          <v-btn color="blue darken-1" text @click="save"> Save </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <div class="main-panel">
      <v-card class="centro" dark>
        <v-container>
          <div class="esquerda">
            <img src="img\anarchy(1).png" alt="" />
          </div>
        </v-container>
        <v-card-text dark>
          <v-row>
            <v-col>
              <v-text-field
                label="Login"
                v-model="username"
                filled
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
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
          <div class="text-center mt-4 mb-n3">
            <v-btn @click="register" text class="botaoRegistro"
              >New here? Register</v-btn
            >
          </div>
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
      role: "",
      nome: "",
      email: "",
      // emailRules: [
      //   v => !!v || 'E-mail is required',
      //   v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      // ],
      senha: "",
      level: "",
      select: "",
      password: "",
      username: "",
      invalid: false,
      janelaRegistro: false,
      confirmSave: false,
      clanList:["Anarchy", "Anarchy 2"],
    };
  },
  methods: {
    async salvarUsuario() {
      api
        .post("/register", {
          nome: this.nome,
          level: this.level,
          role: this.role,
          user: {
            email: this.email,
            senha: this.senha,
            clan: this.select,
            perfilAcesso: {
              id: 1,
            },
          },
        })

        .catch((error) => {
          console.log(error);
        });
    },
    register() {
      this.janelaRegistro = true;
      (this.nome = ""),
        (this.email = ""),
        (this.senha = ""),
        (this.level = ""),
        (this.clan = ""),
        (this.role = "");
    },
    save() {
      this.confirmSave = true;
    },
    async entrar() {
      api
        .post("/oauth/token", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          this.$session.start();
          this.$session.set("jwt", response.data.accessToken);
          console.log(response.data.accessToken);
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
.botaoRegistro {
  text-transform: unset !important;
  font-size: 11px !important;
}
</style>
