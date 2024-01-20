<template>
  <Card class="nav-main-block">

    <template #content>
      <div class="nav-block">
        <div v-if="isClient" class="first-group">

<!--          <Button icon="pi " class="p-button-rounded p-button-info p-button-text" style="width:50px; height:50px" @click="$router.push('/main/profile')">-->
<!--            <font-awesome-icon class="nav-icon" icon="fa-solid fa-user" size="2x" />-->
<!--          </Button>-->
<!--          <span>Лич. каб.</span>-->
        </div>
        <div v-if="isClient" class="second-group">
          <Button icon="pi " class="p-button-rounded p-button-info p-button-text" style="width:50px; height:50px" @click="$router.push('/main/space-marine')">
            <font-awesome-icon class="nav-icon" icon="fa-solid fa-user-astronaut" size="2x" />
          </Button>

          <Button icon="pi " class="p-button-rounded p-button-info p-button-text" style="width:50px; height:50px" @click="$router.push('/main/starship')">
            <font-awesome-icon class="nav-icon" icon="fa-solid fa-shuttle-space" size="2x" />
          </Button>

        </div>
        <div class="third-group">
<!--          <Button icon="pi " class="p-button-rounded p-button-info p-button-text" style="width:50px; height:50px" @click="logout">-->
<!--            <font-awesome-icon class="nav-icon" icon="fa-solid fa-right-from-bracket" size="2x" />-->
<!--          </Button>-->
<!--          <span>Выход</span>-->

        </div>
      </div>
    </template>
  </Card>
</template>

<script>
import Card from "primevue/card";
import { mapGetters } from 'vuex';

export default {
  components: {
    Card
  },
  name: "Nav",
  data(){
    return{
      isClient: null,
    }
  },
  methods: {
    logout(){
      this.$store.dispatch('logout')
      this.$router.push('/login/signIn')
    }
  },
  computed:{

  },
  mounted() {

    // TODO пока оставлено так для реализации основного функционала (Расскоменти потом)
    // this.isClient = this.$store.state.user.role === "ROLE_CLIENT"
    // if (!this.isClient) {
    //   this.$router.push('/main/admin')
    // }

    this.isClient = true
  }
}
</script>

<style lang="scss" scoped >
.nav-icon{
  color: #183153;
}

span{
  font-size: 0.5rem;
  font-weight: 800;
}

.nav-main-block{
  width: 6%;
  height: 98vh;
  background: #FFFFFF;
  border-radius: 15px;
}

.nav-main-block::v-deep .p-card-content {
  display: flex;
  flex-direction: column;
  align-items: center;/* Rectangle 1 */
  height: 100%;
}

.nav-main-block::v-deep .p-card-body {
  height: 100%;
}

.nav-block{
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  text-align: center;
}

.second-group{
  display: flex;
  flex-direction: column;
  align-items: center;
}

.second-group button {
  margin-bottom: 15px;
}


</style>