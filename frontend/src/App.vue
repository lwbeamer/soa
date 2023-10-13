<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
import Nav from "@/components/Nav";
export default {
  name: "App",
  components: {Nav},
  created() {
    this.$router.push('/main/space-marine');
  }
}
</script>

<style scoped>
body{
  margin: 0;
  font-family: Roboto, "Helvetica Neue", sans-serif;
  background-color: #E5E5E5;
}
</style>