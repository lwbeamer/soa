<template>
  <Toast/>
<div class="bg-white border-primary-50 border-round-2xl w-12 p-5" style="height: 98vh">
  <div class="flex flex-column align-items-center ">
        <TabMenu :model="items" v-model:activeIndex="active"/>
        <router-view/>
  </div>
</div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      items: [
        {
          label: 'Вход',
          icon: 'pi pi-fw pi-user',
          to: '/login/signIn'
        },
        {
          label: 'Регистрация',
          icon: 'pi pi-fw pi-plus',
          to: '/login/signUp'
        }
      ],
    }
  },
  created() {
    if (this.$store.state.status.loggedIn) {
      this.$router.push('/main/profile');
    }
  }
}
</script>

<style scoped>
</style>