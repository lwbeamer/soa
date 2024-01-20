import { createApp } from 'vue'
import { library } from '@fortawesome/fontawesome-svg-core'
import App from './App.vue'
import PrimeVue from 'primevue/config';
import router from "@/router";

import './assets/styles/style.css'
import 'primevue/resources/themes/soho-light/theme.css';
import 'primevue/resources/primevue.min.css';
import 'primeflex/themes/primeone-light.css';
import 'primeflex/primeflex.css';
import 'primeicons/primeicons.css';

import {
    faArrowDown,
    faCirclePlus,
    faCircleQuestion,
    faClockRotateLeft,
    faDollarSign,
    faRightFromBracket,
    faRotate,
    faStore,
    faUser,
    faUsers,
    faUserSecret,
    faBedPulse, faClipboardQuestion,
    faMap,
    faFileWaveform,
    faUserAstronaut,
    faShuttleSpace
} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import Button from "primevue/button";
import Carousel from "primevue/carousel";
import Card from "primevue/card";
import Listbox from "primevue/listbox";
import InputNumber from "primevue/inputnumber";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Dialog from "primevue/dialog";
import InputText from "primevue/inputtext";
import Tooltip from "primevue/tooltip";
import Dropdown from "primevue/dropdown";
import Slider from "primevue/slider";
import TabMenu from "primevue/tabmenu";
import ScrollPanel from "primevue/scrollpanel";
import InputMask from "primevue/inputmask";
import Calendar from "primevue/calendar";
import Badge from "primevue/badge";
import Password from "primevue/password";
import ToggleButton from "primevue/togglebutton";
import ToastService from 'primevue/toastservice';
import Toast from 'primevue/toast';
import FileUpload from 'primevue/fileupload';
import RadioButton from 'primevue/radiobutton';
import Textarea from "primevue/textarea";

import store from "@/store";
import {Mixin} from "@/mixin";
import Toolbar from "primevue/toolbar";

const app = createApp(App)

library.add(faUserSecret, faUser, faUsers, faRotate, faStore, faCircleQuestion, faRightFromBracket, faDollarSign, faArrowDown, faCirclePlus, faClockRotateLeft, faBedPulse, faClipboardQuestion, faMap, faFileWaveform, faUserAstronaut, faShuttleSpace)
app.use(PrimeVue).use(router).use(store).use(ToastService);

app.component('Carousel', Carousel)
app.component('Card', Card)
app.component('Button', Button)
app.component('Listbox', Listbox)
app.component('InputNumber', InputNumber)
app.component('InputText', InputText)
app.component('DataTable', DataTable)
app.component('Column', Column)
app.component('Dialog', Dialog)
app.component('Dropdown', Dropdown)
app.component('Slider', Slider)
app.component('TabMenu', TabMenu)
app.component('ScrollPanel', ScrollPanel)
app.component('InputMask', InputMask)
app.component('Badge', Badge)
app.component('Password', Password)
app.component('ToggleButton', ToggleButton)
app.component('font-awesome-icon', FontAwesomeIcon)
app.component('Toast', Toast)
app.component('Calendar', Calendar)
app.component('Toolbar', Toolbar)
app.component('FileUpload', FileUpload)
app.component('RadioButton', RadioButton)
app.component('Textarea', Textarea)

app.directive('tooltip', Tooltip)
app.mixin(Mixin)
app.mount('#app')

