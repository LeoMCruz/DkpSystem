<template>
<v-container fluid>
  <v-row>
    <v-col cols="12">
      <v-data-table
        :headers="headers"
        :items="role"
        :items-per-page="999"
        class="elevation-1"
        dark
        hide-default-footer
        :loading="lLista"
       ></v-data-table>
    </v-col>
  </v-row>
  </v-container>
</template>
<script>
import api from "../services/api";

export default {
  components: {
  },
  
  data () {
    return {
      first: '',
      last: '',
      role:[],
      headers: [
          {
            text: 'Classes.',
            align: 'start',
            value: 'nome',
          },
          { text: 'Abrev', value: 'abreviacao' }
        ],
      lLista: false
      
    }
  },
  //get
  mounted(){
    this.carregarLista()
    
  },
  methods:{
      //post
       async enviar() {
          console.log(this.first),
          console.log(this.last)
          api
            .post("/role", {nome: this.first, abreviacao: this.last})
            .then(() => {
              this.carregarLista()
              this.first=""
              this.last=""
            })
            .catch((error) => {
              console.log(error);
            })
        },

      carregarLista(){
          this.lLista = true
          api
         .get("/role").then(resp => {
          console.log(resp)
          this.role = resp.data
          this.lLista = false
        })
      }
  }
}
</script>
<style>
</style>
