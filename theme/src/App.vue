<template>
  <v-app>
    <div>
      <notifications></notifications>
      <router-view :key="$route.fullPath"></router-view>
    </div>
  </v-app>
</template>

<script>
import api from "../src/services/api";
export default {
  methods: {
    disableRTL() {
      if (!this.$rtl.isRTL) {
        this.$rtl.disableRTL();
      }
    },
    toggleNavOpen() {
      let root = document.getElementsByTagName("html")[0];
      root.classList.toggle("nav-open");
    },
  },
  async mounted() {
    // Configure token for axios call
    const token = this.$session.get("jwt");
    api.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    // End

    this.$watch("$route", this.disableRTL, { immediate: true });
    this.$watch("$sidebar.showSidebar", this.toggleNavOpen);
  },
};
</script>

<style lang="scss"></style>
