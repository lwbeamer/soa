<template>
  <Toast/>
  <div class="card bg-white border-primary-50 border-round-2xl h-auto w-12 ml-2 overflow-x-scroll">
    <div class="card w-12">
      <Toolbar class="mb-4">
        <template #start>
          <Button label="New" icon="pi pi-plus" severity="success" class="mr-2" @click="openNew"/>
          <Button label="Get By ID" severity="danger" @click="openGetStarshipById"/>
        </template>
      </Toolbar>
      <div class="overflow-x-scroll h-auto w-12">
        <DataTable ref="dt" lazy :value="starships" dataKey="id">
          <template #header>
            <div class="flex flex-wrap gap-2 align-items-center justify-content-between">
              <h4 class="m-0">Manage Starships</h4>
            </div>
          </template>
          <template #empty> No customers found.</template>
          <Column field="id" header="ID" class="w-3"/>
          <Column field="name" header="Name" class="w-3"/>
          <Column field="width" header="Width" class="w-2"/>
          <Column field="height" header="Height" class="w-2"/>
          <Column :exportable="false" class="w-2">
            <template #body="slotProps">
              <!--              <Button icon="pi pi-pencil" outlined rounded class="mr-2" @click="editStarship(slotProps.data)"/>-->
              <Button icon="pi pi-trash" outlined rounded severity="danger"
                      @click="confirmDeleteStarship(slotProps.data)"/>
            </template>
          </Column>
        </DataTable>
      </div>

    </div>

    <Dialog v-model:visible="starshipDialog" :style="{width: '450px'}" header="Starship Details" :modal="true"
            class="p-fluid">
      <div class="field">
        <label for="name">Name</label>
        <InputText id="name" v-model.trim="starship.name" required="true" autofocus
                   :class="{'p-invalid': submitted && !starship.name}"/>
        <small class="p-error" v-if="submitted && !starship.name">Name is required.</small>
      </div>

      <div class="field col">
        <label for="width">Width</label>
        <InputNumber id="width" v-model="starship.width" integeronly/>
      </div>
      <div class="field col">
        <label for="height">Coordinate Y</label>
        <InputNumber id="height" v-model="starship.height" integeronly/>
      </div>
      <template #footer>
        <Button label="Cancel" icon="pi pi-times" text @click="hideDialog"/>
        <Button label="Save" icon="pi pi-check" text @click="saveStarship"/>
      </template>
    </Dialog>

    <Dialog v-model:visible="deleteStarshipDialog" :style="{width: '450px'}" header="Confirm" :modal="true">
      <div class="confirmation-content">
        <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem"/>
        <span v-if="starship">Are you sure you want to delete <b>{{ starship.name }}</b>?</span>
      </div>
      <template #footer>
        <Button label="No" icon="pi pi-times" text @click="deleteStarshipDialog = false"/>
        <Button label="Yes" icon="pi pi-check" text @click="deleteStarship"/>
      </template>
    </Dialog>

    <Dialog v-model:visible="getStarshipByIdDialog" :style="{width: '450px'}" header="Get Starship By ID" :modal="true">
      <div class="p-fluid">
        <div class="field col">
          <label for="id">ID</label>
          <InputNumber id="id" v-model="getStarshipId" integeronly/>
        </div>
      </div>
      <div class="p-fluid" v-if="starship !== null">
        <div class="field flex flex-column col">
          <label for="id">ID</label>
          <span> {{ starship.id }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">Name</label>
          <span> {{ starship.name }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="width">Width</label>
          <span> {{ starship.width }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="height">Height</label>
          <span> {{ starship.height }}</span>
        </div>
      </div>
      <template #footer>
        <Button label="View" icon="pi pi-check" text @click="getStarshipById"/>
      </template>
    </Dialog>
  </div>


</template>

<script>
import StarshipService from '@/services/StarshipService';

export default {
  data() {
    return {
      starships: [],
      starshipDialog: false,
      deleteStarshipDialog: false,
      deleteStarshipsDialog: false,
      getStarshipByIdDialog: false,
      getStarshipId: null,
      starship: {
        "id": null,
        "name": null,
        "width": null,
        "height": null
      },
      selectedStarships: null,
      submitted: false,
    }
  },
  mounted() {
    StarshipService.getStarships().then(
        (resp) => {
          this.starships = resp.data
          this.$toast.add({severity: 'success', summary: 'Starship', detail: "Successfully got starship!", life: 3000});
        },
        err => {
          this.$toast.add({severity: 'error', summary: 'Starship', detail: err.response.data, life: 3000});
        }
    )
  },
  methods: {
    openNew() {
      this.starship = StarshipService.getDefaultStarship();
      // this.starships = StarshipService.getStarships();
      this.selectedStarship = null;
      this.submitted = false;
      this.starshipDialog = true;
    },
    openGetStarshipById() {
      this.starship = null;
      this.getStarshipByIdDialog = true;
    },
    getStarshipById() {
      StarshipService.getStarshipById(this.getStarshipId)
          .then((resp) => {
            this.starship = resp.data
            this.$toast.add({
              severity: 'success',
              summary: 'Starship',
              detail: "Successfully got starship!",
              life: 3000
            });

          }, (err) => {
            this.$toast.add({severity: 'error', summary: 'Starship', detail: err.response.data, life: 3000});
          })
    },
    hideDialog() {
      this.starshipDialog = false;
      this.submitted = false;
    },
    async saveStarship() {
      this.submitted = true;
      if (this.starship.name.trim()) {
        if (this.starship.id) {
          // this.starships[this.findIndexById(this.starship.id)] = this.starship;
          await StarshipService.updateStarship(this.starship)
              .then(() => {
                this.starships[this.findIndexById(this.starship.id)] = this.starship;
                this.$toast.add({severity: 'success', summary: 'Starship', detail: "Starship Updated!", life: 3000});
              }, (err) => {
                this.$toast.add({severity: 'error', summary: 'Starship', detail: err.response.data, life: 3000});
              })
        } else {
          // this.starships.push(this.starship);
          await StarshipService.createStarship(this.starship)
              .then(() => {
                console.log(this.starship)
                this.starships.push(this.starship);
                this.$toast.add({severity: 'success', summary: 'Starship', detail: "Starship Created!", life: 3000});
              }, (err) => {
                console.log(err)
                this.$toast.add({severity: 'error', summary: 'Starship', detail: err.response.data, life: 3000});
              })
        }

        this.starshipDialog = false;
        this.starship = {};
      }
    },
    editStarship(starship) {
      this.starship = {...starship};
      // this.starships = StarshipService.getStarships();
      // this.selectedStarship = this.starships.find((x) => x.id === this.starship.starshipId);
      this.starshipDialog = true;
    },
    confirmDeleteStarship(starship) {
      this.starship = starship;
      this.deleteStarshipDialog = true;
    },
    deleteStarship() {
      this.starships = this.starships.filter(val => val.id !== this.starship.id);
      this.deleteStarshipDialog = false;
      StarshipService.deleteStarship(this.starship.id)
          .then(() => {
                this.starships = this.starships.filter(val => val.id !== this.starship.id);
                this.$toast.add({severity: 'success', summary: 'Starship', detail: "Starship Deleted!", life: 3000});
              },
              (err) => {
                this.$toast.add({severity: 'error', summary: 'Starship', detail: err.response.data, life: 3000});
              })
      this.starship = {};
    },
    findIndexById(id) {
      let index = -1;
      for (let i = 0; i < this.starships.length; i++) {
        if (this.starships[i].id === id) {
          index = i;
          break;
        }
      }

      return index;
    },
    confirmDeleteSelected() {
      this.deleteStarshipsDialog = true;
    },
  },
}
</script>

<style scoped>

video {
  width: 50vw;
  height: 50vh;
}

.image-viewer {
  position: relative;
  text-align: center;
}

.map-general {
  display: contents;
}

img {
  object-fit: contain;
}

.dot {
  position: absolute;
  border-radius: 50%;
  background-color: #2e69ff; /* Change to your desired background color */
  transition: all 0.3s ease;
}

.map {
  width: 94%;
}
</style>
