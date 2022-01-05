<template>
  <v-container fluid>
    <v-menu
      bottom
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
        >
          Click no pinto
        </v-btn>
      </template>

      <v-list>
        <v-list-item>
          <v-list-item-title>Pinto</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </v-container>
</template>

<script>
export default {
  data: () => ({
  }),
};
</script>
