<template>
  <Toast/>
  <div class="card bg-white border-primary-50 border-round-2xl h-auto w-auto ml-2 overflow-x-scroll">
    <div class="card">
      <Toolbar class="mb-4">
        <template #start>
          <Button label="New" icon="pi pi-plus" severity="success" class="mr-2" @click="openNew"/>
          <Button label="Get By ID" severity="danger" class="mr-2" @click="openGetMarineById"/>
          <Button label="Space Utils" severity="success" @click="openSpaceUtils"/>
        </template>
      </Toolbar>
      <div class="overflow-x-scroll h-auto w-auto">
        <DataTable v-model:filters="filters" ref="dt" lazy :value="marines" dataKey="id" sortMode="multiple"
                   :paginator="true" :page="0" :rows="10"
                   :totalRecords="totalRecords"
                   filterDisplay="menu"
                   :globalFilterFields="['id', 'achievements', 'name', 'coordinates.x', 'coordinates.y', 'health', 'category', 'weaponType', 'creationDate', 'starshipId', 'chapter.name', 'chapter.world']"
                   @page="onPage($event)" @sort="onSort($event)" @filter="onFilter($event)"
                   paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                   :rowsPerPageOptions="[1,5,10,25]"
                   scrollable
                   scroll-height="60vh"
                   currentPageReportTemplate="Showing {first} to {last} of {totalRecords} marines">
          <template #header>
            <div class="flex flex-wrap gap-2 align-items-center justify-content-between">
              <h4 class="m-0">Manage Marines</h4>
              <span class="p-input-icon-left">
                            <i class="pi pi-search"/>
                            <InputText v-model="filters['global'].value" placeholder="Search..."/>
                        </span>
            </div>
          </template>
          <template #empty> No customers found.</template>
          <template #loading> Loading customers data. Please wait.</template>
          <Column field="id" header="ID" sortable style="min-width:12rem" :showFilterMatchModes="false"
                  :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputNumber v-model="filterModel.value" placeholder="Search by ID"/>
            </template>
          </Column>
          <Column field="name" header="Name" filterField="name" sortable style="min-width:16rem"
                  :showFilterMatchModes="false" :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputText v-model="filterModel.value" type="text" class="p-column-filter" placeholder="Search by name"/>
            </template>
          </Column>
          <Column field="starshipId" header="Starship ID" sortable style="min-width:16rem"
                  :showFilterMatchModes="false" :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputText v-model="filterModel.value" type="text" class="p-column-filter"
                         placeholder="Search by starship id"/>
            </template>
          </Column>
          <Column field="achievements" header="Achievements" sortable style="min-width:12rem"
                  :showFilterMatchModes="false" :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputText v-model="filterModel.value" type="text" class="p-column-filter"
                         placeholder="Search by achievements"/>
            </template>
          </Column>
          <Column field="coordinates.x" header="Coordinate X" sortable style="min-width:16rem"
                  :showFilterMatchModes="false" :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputNumber v-model="filterModel.value" placeholder="Search by X" integeronly/>
            </template>
          </Column>
          <Column field="coordinates.y" header="Coordinate Y" sortable style="min-width:16rem"
                  :showFilterMatchModes="false" :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputNumber v-model="filterModel.value" placeholder="Search by Y" integeronly/>
            </template>
          </Column>
          <Column field="creationDate" header="Creation Date" sortable style="min-width:16rem"
                  :showFilterMatchModes="false" :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputText v-model="filterModel.value" type="text" class="p-column-filter"
                         placeholder="Search by creation date"/>
            </template>
          </Column>
          <Column field="health" header="Health" sortable style="min-width:16rem" :showFilterMatchModes="false"
                  :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputNumber v-model="filterModel.value" placeholder="Search by health" integeronly/>
            </template>
          </Column>
          <Column field="category" header="Category" sortable style="min-width:16rem" :showFilterMatchModes="false"
                  :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputText v-model="filterModel.value" type="text" class="p-column-filter"
                         placeholder="Search by category"/>
            </template>
          </Column>
          <Column field="weaponType" header="Weapon Type" sortable style="min-width:16rem" :showFilterMatchModes="false"
                  :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputText v-model="filterModel.value" type="text" class="p-column-filter"
                         placeholder="Search by weapon type"/>
            </template>
          </Column>
          <Column field="chapter.name" header="Chapter Name" sortable style="min-width:16rem"
                  :showFilterMatchModes="false" :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputText v-model="filterModel.value" type="text" class="p-column-filter"
                         placeholder="Search by chapter name"/>
            </template>
          </Column>
          <Column field="chapter.world" header="Chapter World" sortable style="min-width:16rem"
                  :showFilterMatchModes="false" :show-filter-operator="false">
            <template #filter="{ filterModel }">
              <InputText v-model="filterModel.value" type="text" class="p-column-filter" placeholder="Search by world"/>
            </template>
          </Column>

          <Column :exportable="false" style="min-width:8rem">
            <template #body="slotProps">
              <Button icon="pi pi-pencil" outlined rounded class="mr-2" @click="editMarine(slotProps.data)"/>
              <Button icon="pi pi-trash" outlined rounded severity="danger"
                      @click="confirmDeleteMarine(slotProps.data)"/>
            </template>
          </Column>
        </DataTable>
      </div>

    </div>

    <Dialog v-model:visible="marineDialog" :style="{width: '450px'}" header="Marine Details" :modal="true"
            class="p-fluid">
      <div class="field">
        <label for="name">Name</label>
        <InputText id="name" v-model.trim="marine.name" required="true" autofocus
                   :class="{'p-invalid': submitted && !marine.name}"/>
        <small class="p-error" v-if="submitted && !marine.name">Name is required.</small>
      </div>
      <div class="field">
        <label for="achievements">Achievements</label>
        <Textarea id="achievements" v-model="marine.achievements" required="true" rows="3" cols="20"/>
      </div>

      <div class="field col">
        <label for="coordinateX">Coordinate X</label>
        <InputNumber id="coordinateX" v-model="marine.coordinates.x" integeronly/>
      </div>
      <div class="field col">
        <label for="coordinateY">Coordinate Y</label>
        <InputNumber id="coordinateY" v-model="marine.coordinates.y" integeronly/>
      </div>
      <div class="field col">
        <label for="health">Health</label>
        <InputNumber id="health" v-model="marine.health" integeronly/>
      </div>
      <!--      <div class="field">-->
      <!--        <label class="mb-3">Starship</label>-->
      <!--        <Dropdown :options="starships" v-model="selectedStarship" optionLabel="name">-->
      <!--          <template #value="slotProps">-->
      <!--            <div class="" v-if="selectedStarship != null" style="width: 30vw">-->
      <!--              <div>{{ slotProps.value.name }}</div>-->
      <!--            </div>-->
      <!--          </template>-->
      <!--          <template #option="slotProps" style="width: 30vw">-->
      <!--            <div class="flex justify-content-between">-->
      <!--              <div>{{ slotProps.option.name }}</div>-->
      <!--            </div>-->
      <!--          </template>-->
      <!--        </Dropdown>-->
      <!--      </div>-->

      <div class="field">
        <label class="mb-3">Astartes Category</label>
        <div class="formgrid grid">
          <div class="field-radiobutton col-6">
            <RadioButton id="category1" name="category" value="SCOUT" v-model="marine.category"/>
            <label for="category1">SCOUT</label>
          </div>
          <div class="field-radiobutton col-6">
            <RadioButton id="category2" name="category" value="AGGRESSOR" v-model="marine.category"/>
            <label for="category2">AGGRESSOR</label>
          </div>
          <div class="field-radiobutton col-6">
            <RadioButton id="category3" name="category" value="TACTICAL" v-model="marine.category"/>
            <label for="category3">TACTICAL</label>
          </div>
          <div class="field-radiobutton col-6">
            <RadioButton id="category4" name="category" value="LIBRARIAN" v-model="marine.category"/>
            <label for="category4">LIBRARIAN</label>
          </div>
          <div class="field-radiobutton col-6">
            <RadioButton id="category5" name="category" value="APOTHECARY" v-model="marine.category"/>
            <label for="category5">APOTHECARY</label>
          </div>
        </div>
      </div>

      <div class="field">
        <label class="mb-3">Weapon type</label>
        <div class="formgrid grid">
          <div class="field-radiobutton col-6">
            <RadioButton id="category1" name="category" value="BOLTGUN" v-model="marine.weaponType"/>
            <label for="category1">BOLTGUN</label>
          </div>
          <div class="field-radiobutton col-6">
            <RadioButton id="category2" name="category" value="HEAVY_BOLTGUN" v-model="marine.weaponType"/>
            <label for="category2">HEAVY_BOLTGUN</label>
          </div>
          <div class="field-radiobutton col-6">
            <RadioButton id="category3" name="category" value="PLASMA_GUN" v-model="marine.weaponType"/>
            <label for="category3">PLASMA_GUN</label>
          </div>
          <div class="field-radiobutton col-6">
            <RadioButton id="category4" name="category" value="GRAV_GUN" v-model="marine.weaponType"/>
            <label for="category4">GRAV_GUN</label>
          </div>
        </div>
      </div>

      <div class="formgrid grid">
        <div class="field col">
          <label for="chapter-name">Chapter Name</label>
          <InputText id="chapter-name" v-model.trim="marine.chapter.name" required="true" autofocus
                     :class="{'p-invalid': submitted && !marine.chapter.name}"/>
        </div>
        <div class="field col">
          <label for="chapter-world">Chapter World</label>
          <InputText id="chapter-world" v-model.trim="marine.chapter.world" required="true" autofocus
                     :class="{'p-invalid': submitted && !marine.chapter.world}"/>
        </div>
      </div>
      <template #footer>
        <Button label="Cancel" icon="pi pi-times" text @click="hideDialog"/>
        <Button label="Save" icon="pi pi-check" text @click="saveMarine"/>
      </template>
    </Dialog>

    <Dialog v-model:visible="deleteMarineDialog" :style="{width: '450px'}" header="Confirm" :modal="true">
      <div class="confirmation-content">
        <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem"/>
        <span v-if="marine">Are you sure you want to delete <b>{{ marine.name }}</b>?</span>
      </div>
      <template #footer>
        <Button label="No" icon="pi pi-times" text @click="deleteMarineDialog = false"/>
        <Button label="Yes" icon="pi pi-check" text @click="deleteMarine"/>
      </template>
    </Dialog>
    <Dialog v-model:visible="spaceUtilsDialog" :style="{width: '450px'}" header="Space Utils" :modal="true">
      <div class="p-fluid">
        <div class="field">
          <label class="mb-3">Starship</label>
          <Dropdown :options="spaceUtilsOperations" v-model="selectedSpaceUtilOperation" optionLabel="label">
            <template #value="slotProps">
              <div class="" v-if="selectedSpaceMarineField != null" style="width: 30vw">
                <div>{{ slotProps.value.label }}</div>
              </div>
            </template>
            <template #option="slotProps" style="width: 30vw">
              <div class="flex justify-content-between">
                <div>{{ slotProps.option.label }}</div>
              </div>
            </template>
          </Dropdown>
        </div>
        <div class="field">
          <label class="mb-3">Starship</label>
          <Dropdown :options="spaceMarineFields" v-model="selectedSpaceMarineField" optionLabel="label">
            <template #value="slotProps">
              <div class="" v-if="selectedSpaceMarineField != null" style="width: 30vw">
                <div>{{ slotProps.value.value }}</div>
              </div>
            </template>
            <template #option="slotProps" style="width: 30vw">
              <div class="flex justify-content-between">
                <div>{{ slotProps.option.value }}</div>
              </div>
            </template>
          </Dropdown>
        </div>
      </div>
      <div class="p-fluid" v-if="submittedSpaceUtils && minMaxOperation && marine !== null">
        <div class="field flex flex-column col">
          <label for="id">ID</label>
          <span> {{ marine.id }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">name</label>
          <span> {{ marine.name }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">achievements</label>
          <span> {{ marine.achievements }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">creationDate</label>
          <span> {{ marine.creationDate }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">starshipId</label>
          <span> {{ marine.starshipId }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">health</label>
          <span> {{ marine.health }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">weaponType</label>
          <span> {{ marine.weaponType }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">category</label>
          <span> {{ marine.category }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">category</label>
          <span> {{ marine.category }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">chapterName</label>
          <span> {{ marine.chapter.name }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">chapterWorld</label>
          <span> {{ marine.chapter.world }}</span>
        </div>
      </div>
      <div class="p-fluid" v-if="submittedSpaceUtils && !minMaxOperation && sumField">
        {{ sumField }}
      </div>
      <template #footer>
        <Button label="View" icon="pi pi-check" text @click="submitSpaceUtil"/>
      </template>
    </Dialog>

    <Dialog v-model:visible="getMarineByIdDialog" :style="{width: '450px'}" header="Get Marine By ID" :modal="true">
      <div class="p-fluid">
        <div class="field col">
          <label for="id">ID</label>
          <InputNumber id="id" v-model="getMarineId" integeronly/>
        </div>
      </div>
      <div class="p-fluid" v-if="marine !== null">
        <div class="field flex flex-column col">
          <label for="id">ID</label>
          <span> {{ marine.id }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">name</label>
          <span> {{ marine.name }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">achievements</label>
          <span> {{ marine.achievements }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">creationDate</label>
          <span> {{ marine.creationDate }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">starshipId</label>
          <span> {{ marine.starshipId }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">health</label>
          <span> {{ marine.health }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">weaponType</label>
          <span> {{ marine.weaponType }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">category</label>
          <span> {{ marine.category }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">category</label>
          <span> {{ marine.category }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">chapterName</label>
          <span> {{ marine.chapter.name }}</span>
        </div>
        <div class="field flex flex-column col">
          <label for="id">chapterWorld</label>
          <span> {{ marine.chapter.world }}</span>
        </div>
      </div>
      <template #footer>
        <Button label="View" icon="pi pi-check" text @click="getMarineById"/>
      </template>
    </Dialog>
  </div>


</template>

<script>
import {FilterMatchMode, FilterOperator} from 'primevue/api';
import SpaceMarineService from '@/services/SpaceMarineService';

export default {
  data() {
    return {
      currentPage: 1,
      totalRecords: 0,
      marines: null,
      marineDialog: false,
      deleteMarineDialog: false,
      deleteMarinesDialog: false,
      getMarineByIdDialog: false,
      getMarineId: null,
      spaceUtilsDialog: false,
      spaceUtilsOperations: [
        {"label": "Минимум", "value": "min"},
        {"label": "Максимум", "value": "max"},
        {"label": "Сумма", "value": "sum"},
      ],
      submittedSpaceUtils: false,
      minMaxOperation: false,
      spaceMarineFields: [
        {"value": "name"},
        {"value": "coordinatesX"},
        {"value": "coordinatesY"},
        {"value": "starshipId"},
        {"value": "creationDate"},
        {"value": "health"},
        {"value": "achievements"},
        {"value": "category"},
        {"value": "weaponType"},
        {"value": "chapterName"},
        {"value": "chapterWorld"},
      ],
      selectedSpaceMarineField: null,
      selectedSpaceUtilOperation: null,
      marine: {
        "name": "",
        "coordinates": {
          "x": 1,
          "y": 1
        },
        "starshipId": 2,
        "creationDate": "2023-10-11T17:25:59.248Z",
        "health": null,
        "achievements": "string",
        "category": "SCOUT",
        "weaponType": "BOLTGUN",
        "chapter": {
          "name": "New",
          "world": "Finland"
        },
      },
      selectedMarines: null,
      filters: {},
      submitted: false,
      statuses: [
        {label: 'INSTOCK', value: 'instock'},
        {label: 'LOWSTOCK', value: 'lowstock'},
        {label: 'OUTOFSTOCK', value: 'outofstock'}
      ],
      lazyParams: {},
      sumField: null
    }
  },
  created() {
    this.initFilters();
  },
  mounted() {
    this.totalRecords = 10;
    this.lazyParams = {
      // rows: this.$refs.dt.rows,
      rows: 10,
      multiSortMeta: null,
      filters: this.filters
    };
    this.loadLazyData()
  },
  methods: {
    convertFieldName(fieldName) {
      return fieldName.replace(/\.(.)/g, function (match, group1) {
        return group1.toUpperCase();
      })
    },
    submitSpaceUtil() {
      let data = {
        "operation": this.selectedSpaceUtilOperation.value,
        "field": this.selectedSpaceMarineField.value
      }
      SpaceMarineService.getSpaceUtils(data)
          .then((data) => {
                this.submittedSpaceUtils = true;
                this.minMaxOperation = !('sumHealth' in data.data);
                if (this.minMaxOperation)
                  this.marine = data.data
                else
                  this.sumField = data.data.sumHealth
                this.$toast.add({
                  severity: 'success',
                  summary: 'SpaceMarineUtils',
                  detail: "Successfully get marines!",
                  life: 3000
                });

              },
              (err) => {
                this.submittedSpaceUtils = false;
                this.$toast.add({
                  severity: 'error',
                  summary: 'SpaceMarineUtils',
                  detail: err.response.data,
                  life: 3000
                });
              })
    },
    openSpaceUtils() {
      this.marine = null
      this.spaceUtilsDialog = true
    },

    buildRequestParams(data) {
      const queryParams = [];
      // Params begin with '?' char
      queryParams.push(`?pageSize=${this.lazyParams.rows}`);
      queryParams.push(`page=${this.currentPage}`);

      // Process filters
      for (const field in data.filters) {
        const filter = data.filters[field];
        if (filter.operator === "and" && filter.constraints) {
          filter.constraints.forEach((constraint) => {
            if (constraint.value !== null) {
              queryParams.push(`${field}=${constraint.value}`);
            }
          });
        }
      }

      // Process multiSortMeta
      if (data.multiSortMeta && Array.isArray(data.multiSortMeta)) {
        data.multiSortMeta.forEach((sortMeta) => {
          const sortField = this.convertFieldName(sortMeta.field).replace('.', '');
          const sortOrder = sortMeta.order === -1 ? 'DESC' : 'ASC';
          queryParams.push(`sort=${sortField}${sortOrder}`);
        });
      }
      return queryParams.join('&');
    },
    loadLazyData() {
      console.log(this.lazyParams);
      const reqParams = this.buildRequestParams(this.lazyParams)
      SpaceMarineService.getMarines(reqParams)
          .then((data) => {
                // console.log(data.data.)
                this.marines = data.data.objects
                this.$toast.add({
                  severity: 'success',
                  summary: 'SpaceMarine',
                  detail: "Successfully get marines!",
                  life: 3000
                });

              },
              (err) => {
                this.$toast.add({
                  severity: 'error',
                  summary: 'SpaceMarine',
                  detail: "Error when fetch data!",
                  life: 3000
                });
              })
      console.log(reqParams);
    },
    onPage(event) {
      this.lazyParams = event;
      this.currentPage = event.page + 1;
      this.loadLazyData();
    },
    onSort(event) {
      this.lazyParams = event;
      this.loadLazyData();
    },
    onFilter() {
      this.lazyParams.filters = this.filters;
      this.loadLazyData();
    },

    openNew() {
      this.marine = SpaceMarineService.getDefaultMarine();
      // this.starships = SpaceMarineService.getStarships();
      this.selectedStarship = null;
      this.submitted = false;
      this.marineDialog = true;
    },
    openGetMarineById() {
      this.marine = null;
      this.getMarineByIdDialog = true;
    },
    getMarineById() {
      SpaceMarineService.getMarineById(this.getMarineId)
          .then((resp) => {
            console.log(resp.data)
            this.marine = resp.data
            this.$toast.add({
              severity: 'success',
              summary: 'SpaceMarine',
              detail: "Successfully got marine!",
              life: 3000
            });

          })
          .catch((err) => {
            this.$toast.add({severity: 'error', summary: 'SpaceMarine', detail: err.response.data, life: 3000});
          })
    },
    hideDialog() {
      this.marineDialog = false;
      this.submitted = false;
    },
    async saveMarine() {
      console.log(this.marine)

      this.submitted = true;
      if (this.marine.name.trim()) {
        if (this.marine.id) {
          // this.marines[this.findIndexById(this.marine.id)] = this.marine;
          await SpaceMarineService.updateMarine(this.marine)
              .then(() => {
                    this.marines[this.findIndexById(this.marine.id)] = this.marine;
                    this.$toast.add({severity: 'success', summary: 'SpaceMarine', detail: "Marine Updated!", life: 3000});
                  },
                  (err) => {
                    this.$toast.add({severity: 'error', summary: 'SpaceMarine', detail: err.response.data, life: 3000});
                  })
        } else {
          // this.marines.push(this.marine);
          await SpaceMarineService.createMarine(this.marine)
              .then(() => {
                    this.marines.push(this.marine);

                    this.$toast.add({severity: 'success', summary: 'SpaceMarine', detail: "Marine Created!", life: 3000});
                  },
                  (err) => {
                    this.$toast.add({severity: 'error', summary: 'SpaceMarine', detail: err.response.data, life: 3000});
                  })
        }

        this.marineDialog = false;
        this.marine = {};
      }
    },
    editMarine(marine) {
      this.marine = {...marine};
      // this.starships = SpaceMarineService.getStarships();
      // this.selectedStarship = this.starships.find((x) => x.id === this.marine.starshipId);
      this.marineDialog = true;
    },
    confirmDeleteMarine(marine) {
      this.marine = marine;
      this.deleteMarineDialog = true;
    },
    deleteMarine() {
      // this.marines = this.marines.filter(val => val.id !== this.marine.id);
      // this.deleteMarineDialog = false;
      // this.marine = {};
      SpaceMarineService.deleteMarine(this.marine.id)
          .then(() => {
            this.marines = this.marines.filter(val => val.id !== this.marine.id);
            this.deleteMarineDialog = false;
            this.marine = {};
            this.$toast.add({severity: 'success', summary: 'SpaceMarine', detail: "Marine Deleted!", life: 3000});
          })
          .catch((err) => {
            this.$toast.add({severity: 'error', summary: 'SpaceMarine', detail: err.response.data, life: 3000});
          })
    },
    findIndexById(id) {
      let index = -1;
      for (let i = 0; i < this.marines.length; i++) {
        if (this.marines[i].id === id) {
          index = i;
          break;
        }
      }

      return index;
    },
    confirmDeleteSelected() {
      this.deleteMarinesDialog = true;
    },
    initFilters() {
      this.filters = {
        'global': {value: null, matchMode: FilterMatchMode.STARTS_WITH},
        id: {operator: FilterOperator.AND, constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]},
        name: {operator: FilterOperator.AND, constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]},
        achievements: {operator: FilterOperator.AND, constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]},
        category: {operator: FilterOperator.AND, constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]},
        weaponType: {operator: FilterOperator.AND, constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]},
        'coordinates.x': {
          operator: FilterOperator.AND,
          constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]
        },
        'coordinates.y': {
          operator: FilterOperator.AND,
          constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]
        },
        health: {operator: FilterOperator.AND, constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]},
        creationDate: {operator: FilterOperator.AND, constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]},
        starshipId: {operator: FilterOperator.AND, constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]},
        'chapter.name': {operator: FilterOperator.AND, constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]},
        'chapter.world': {
          operator: FilterOperator.AND,
          constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]
        },
      }
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
