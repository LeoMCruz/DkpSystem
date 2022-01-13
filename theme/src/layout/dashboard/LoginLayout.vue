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
              <v-text-field label="Login" filled></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-text-field label="Password" filled></v-text-field>
            </v-col>
          </v-row>
          <div class="text-center">
            <v-btn rounded color="primary" dark> Enter</v-btn>
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
export default {
  components: {
    ContentFooter,
    DashboardContent,
  },
  methods: {},
};
</script>
<style scoped>
.centro {
  background-color: white;
  opacity: 0.70;
  border-color: transparent ;
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
