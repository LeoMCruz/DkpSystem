<template>
  <v-container fluid>
    <v-form ref="form-class">
      <v-container>
        <v-row>
          <v-col cols="12" sm="3">
            <v-text-field
              v-model="nome"
              label="Name"
              filled
              dark
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3">
            <v-text-field
              v-model="level"
              label="Level"
              filled
              dark
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3">
            <v-select  v-model="select" 
             :items="role" filled label="Role" dark></v-select>
          </v-col>
          <br />
          <v-col cols="12" sm="3">
            <v-btn @click="enviar" block height="59px"> Send </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
    <v-row>
      <v-col cols="9">
        <v-data-table
          :headers="headers"
          :items="toons"
          :items-per-page="999"
          class="elevation-1"
          dark
          hide-default-footer
          :loading="lLista"
        >
          <template v-slot:[`item.level`]="props">
            <v-edit-dialog
              :return-value.sync="props.item.level"
              @close="close(props.item)"
              dark
            >
              {{ props.item.quantidade }}
              <template v-slot:input>
                <v-text-field
                  v-model="props.item.level"
                  
                  label="Edit"
                  single-line
                  dark
                ></v-text-field>
              </template>
            </v-edit-dialog>
          </template>
        </v-data-table>
        <v-snackbar v-model="snack" :timeout="3000" :color="snackColor">
          {{ snackText }}

          <template v-slot:action="{ attrs }">
            <v-btn v-bind="attrs" text @click="snack = false"> Close </v-btn>
          </template>
        </v-snackbar>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import api from "../services/api";

export default {
  components: {},

  data() {
    return {
      items: [],
      toons: [],
      itens: [],
      level: 0,
      nome: "",
      points: "",
      snack: false,
      snackColor: "",
      snackText: "",
      select:"",
      role: [
        "Adventurer",
        "ArcanaLord",
        "ArchMage",
        "Cardinal",
        "Death Knight",
        "Dominator",
        "Doombringer",
        "Doomcryer",
        "Dreadnought",
        "Duelist",
        "Elemental Master",
        "Eva's Saint",
        "Eva's Templar",
        "Fortune Seeker",
        "Ghost Hunter",
        "Ghost Sentinel",
        "Grand Khavatari",
        "Hell Knight",
        "Hierophant",
        "Maestro",
        "Moonlight Sentinel",
        "Mystic Muse",
        "Phoenix Knight",
        "Sagittarius",
        "Shillien Saint",
        "Shillien Templar",
        "Soul Hound",
        "SoulTaker",
        "Spectral Dancer",
        "Spectral Master",
        "Storm Blaster",
        "Storm Screamer",
        "Sword Muse",
        "Titan",
        "Trickster",
        "Wind Rider",
      ],
      headers: [
        {
          text: "Name",
          align: "start",
          value: "nome",
        },
        { text: "Level", value: "level" },
        { text: "Role", value: "role" },
        { text: "Total Dkp", value: "points" },
      ],
      lLista: false,
    };
  },
  //get
  mounted() {
    this.carregarTodos();
  },
  methods: {
    //post
    async enviar() {
      api
        .post("/toon", {
            nome:this.nome,
            level:this.level,
            role:this.select,
        })
        .then(() => {
          this.carregarTodos();
          this.nome = 0;
          this.level = "";
          this.select="";
        })
        .catch((error) => {
          console.log(error);
        });
    },
    carregarTodos() {
      this.lLista = true;
      api.get("/toon").then((resp) => {
        console.log(resp.data);
        this.toons = resp.data;
        this.lLista = false;
      });
    },

    close(value) {
      this.snack = true;
      this.snackColor = "success";
      this.snackText = "Successfully Changed";
      api.put(`/toon/${value.id}`, { level: value.level }).then(() => {});
    },
  },
};
</script>
<style scoped>
.v-progress-circular {
  margin: 1rem;
}
</style>
