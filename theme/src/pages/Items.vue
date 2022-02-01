<template>
  <v-container fluid>
    <v-form ref="form-class">
      <v-container>
        <v-row>
          <v-col cols="12" sm="3">
            <v-text-field
              v-model="item"
              label="Item"
              filled
              dark
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3">
            <v-text-field
              v-model="tipo"
              label="Tipo"
              filled
              dark
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3">
            <v-text-field
              v-model="physical"
              label="P Attack"
              filled
              dark
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3">
            <v-text-field
              v-model="magic"
              label="M Attack"
              filled
              dark
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3">
            <v-text-field
              v-model="armor"
              label="P Def"
              filled
              dark
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3">
            <v-text-field
              v-model="imagem"
              label="IMG URL"
              filled
              dark
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3">
            <v-text-field
              v-model="quantidade"
              label="Quantidade"
              filled
              dark
            ></v-text-field>
          </v-col>
          <br />
          <v-col cols="12" sm="3">
            <v-btn @click="enviar" block height="59px"> Enviar </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
    <v-row>
      <v-col cols="12">
        <v-card-title>
          <v-icon class="mr-4" @click="carregarTodos" dark>mdi-menu</v-icon>
          <v-icon class="mx-4" @click="carregarArmas" dark>mdi-sword</v-icon>
          <v-icon class="mx-4" @click="carregarArmaduras" dark>mdi-shield-sword</v-icon>
          <v-icon class="mx-4" @click="carregarJoias" dark>mdi-diamond-stone</v-icon>
          <v-icon class="mx-4" @click="carregarDiversos" dark>mdi-contain</v-icon>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
            dark
          ></v-text-field>
        </v-card-title>
        <v-data-table
          :headers="headers"
          :items="itens"
          :items-per-page="999"
          class="elevation-1"
          dark
          hide-default-footer
          :loading="lLista"
          :search="search"
          dense
        >
          <template v-slot:[`item.imagem`]="{ item }">
            <div>
              <v-img :src="item.imagem" height="48px" width="48px"></v-img>
            </div>
          </template>
          <template v-slot:[`item.quantidade`]="props">
            <v-edit-dialog
              :return-value.sync="props.item.quantidade"
              @close="close(props.item)"
              dark
            >
              {{ props.item.quantidade }}
              <template v-slot:input>
                <v-text-field
                  v-model="props.item.quantidade"
                  label="Edit"
                  single-line
                  dark
                ></v-text-field>
              </template>
            </v-edit-dialog>
          </template>
           <template v-slot:[`item.preco`]="props">
            <v-edit-dialog
              :return-value.sync="props.item.preco"
              @close="close2(props.item)"
              dark
            >
              {{ props.item.preco }}
              <template v-slot:input>
                <v-text-field
                  v-model="props.item.preco"
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
      snack: false,
      snackColor: "",
      snackText: "",
      search: "",
      imagem: "",
      nome: "",
      physical: "-",
      magic: "-",
      tipo: "",
      armor: "-",
      quantidade: "",
      itens: [],
      item: "",
      preco: "0",
      headers: [
        {
          text: "image",
          align: "start",
          value: "imagem",
        },
        { text: "Item", value: "nome" },
        { text: "P. Attack", value: "physical" },
        { text: "M. Attack", value: "magic" },
        { text: "P. Def", value: "armor" },
        { text: "Amount", value: "quantidade" },
        { text: "Points", value: "preco" },
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
        .post("/itens", {
          nome: this.item,
          imagem: this.imagem,
          armor: this.armor,
          physical: this.physical,
          magic: this.magic,
          tipo: this.tipo,
          quantidade: this.quantidade,
          preco: this.preco,
        })
        .then(() => {
          this.carregarTodos();
          this.item = "";
          this.physical = "";
          this.magic = "";
          this.armor = "";
          this.imagem = "";
          this.tipo = "";
          this.quantidade = "";
          this.preco ="0";
        })
        .catch((error) => {
          console.log(error);
        });
    },
    carregarTodos() {
      this.lLista = true;
      api.get("/itens").then((resp) => {
        console.log(resp);
        this.itens = resp.data;
        this.lLista = false;
      })
    },
    carregarArmas() {
      this.lLista = true;
      api.get("/itens/armas").then((resp) => {
        console.log(resp);
        this.itens = resp.data;
        this.lLista = false;
      });
    },
    carregarArmaduras() {
      this.lLista = true;
      api.get("/itens/armaduras").then((resp) => {
        console.log(resp);
        this.itens = resp.data;
        this.lLista = false;
      });
    },
    carregarJoias() {
      this.lLista = true;
      api.get("/itens/joias").then((resp) => {
        console.log(resp);
        this.itens = resp.data;
        this.lLista = false;
      });
    },
    carregarDiversos() {
      this.lLista = true;
      api.get("/itens/diversos").then((resp) => {
        console.log(resp);
        this.itens = resp.data;
        this.lLista = false;
      });
    },

    close(value) {
      this.snack = true;
      this.snackColor = "success";
      this.snackText = "Successfully Changed";
      api
        .put(`/itens/${value.id}`, { quantidade: value.quantidade, preco: value.preco})
        .then(() => {});
    },
    close2(value) {
      this.snack = true;
      this.snackColor = "success";
      this.snackText = "Successfully Changed";
      api
        .put(`/itens/${value.id}`, { preco: value.preco, quantidade: value.quantidade})
        .then(() => {});
    },
    
  },
};
</script>
<style scoped>
.v-progress-circular {
  margin: 1rem;
}
</style>
