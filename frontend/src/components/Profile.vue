<template>
  <div class=" bg-white border-primary-50 border-round-2xl h-auto w-12 ml-2 p-5">
    <div class="main-info-block m-auto w-6 align-self-center">
        <span class="header">
          Личная информация
        </span>
      <Toast/>
      <div class="main-info-img">
        <div class="main-info">
          <div class="main-info-login">
              <span class="info-title">
                Логин
              </span>
            <span class="info-content">
                {{ profile.userLogin }}
              </span>
          </div>
          <div class="main-info-name">
              <span class="info-title">
                Имя и фамилия
              </span>
            <span class="info-content">
                {{ profile.name }} {{ profile.surname }}
              </span>
          </div>
        </div>
        <div class="img">
          <font-awesome-icon icon="fa-solid fa-user" size="10x" style="color: #183153"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>


export default {
  name: "Profile",
  data() {
    return {
      profile: Object,
      bankCardDialogVisible: false,
      replenishForm: {
        amount: null
      },
      responsiveOptions: [
        {
          breakpoint: '1024px',
          numVisible: 3,
          numScroll: 3
        },
        {
          breakpoint: '600px',
          numVisible: 2,
          numScroll: 2
        },
        {
          breakpoint: '480px',
          numVisible: 1,
          numScroll: 1
        }
      ],
      hasCards: false,
    }
  },
  methods: {
    getProfileInfo() {
      this.$store.dispatch('checkIsLoggedIn').then(
          (resp) => {
            this.profile = resp
          },
          () => {
            this.$toast.add({severity: 'error', summary: 'Личный кабинет', detail: err.response.data, life: 3000});
            this.$router.push('/login/signIn')
          }
      )
    },

  },
  mounted() {

    // this.getProfileInfo()
    // this.getBankCards()
  }
}
</script>

<style scoped>
.bank-card-carousel::v-deep .p-carousel-items-container {
  width: 30vw;
}


.replenish-form {
  margin-top: 1rem;
}

.replenish-form .field {
  display: flex;
  flex-direction: column;
  align-items: center;
}


.replenish-form > div {
  margin-bottom: 1.5rem;
  text-align: center;
}

.profile {
  display: flex;
  width: 94%;
}

.main-info-block {
  width: 55%;
}

.bank-card-block {
  width: 45%;
}

.field label {
  margin-bottom: 8px;
}

.bank-card, .main-card {
  border-radius: 15px;
}

.card-fields > * {
  margin-bottom: 1rem;
}

.info-title {
  color: grey;
  font-weight: 500;
  margin-bottom: 0.75rem
}

.info-content {
  border-bottom: solid;
  font-weight: bold;
  padding-bottom: 0.5rem;
  inline-size: fit-content;
}

.main-info > div {
  display: flex;
  flex-direction: column;
  margin-bottom: 1.25rem;
}

.header {
  font-size: larger;
  font-weight: bolder;
  margin-top: 30px;
}

.main-info-img {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.main-card::v-deep .p-card-body {
  width: 100%;
}

.main-card::v-deep .p-card-content {
  padding: 1rem;
  width: 100%
}

.main-card::v-deep .p-card-header {
  margin-top: 1rem;
}

.bank-card::v-deep .p-card-header {
  margin-top: 1rem;
}

.img {
  margin-right: 2rem;
}

.card-fields {
  height: 50%;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: space-around;
}

.card-fields .field {
  display: flex;
  flex-direction: column;
}

.bank-card::v-deep .p-card-body {
  height: 100%;
}

.bank-card::v-deep .p-card-content {
  height: 100%
}

.add-button {
  text-align: center;
  margin-top: 0;
}

.card {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 1rem;
}

.card h4 {
  margin: 0 2rem 0 0;
}

.card > * {
  margin-bottom: 1.5rem;
}

.available-resources > div {
  margin-bottom: 1rem;
  display: flex;
}


.bank-card-dialog-header {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: space-between;
}

.header-block {
  display: flex;
  align-items: center;
}

.header-block h3 {
  margin: 0;
}

</style>