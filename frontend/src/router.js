import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/components/Login";
import SignIn from "@/components/SignIn";
import SignUp from "@/components/SignUp";
import store from "@/store";
import NotFound from "@/components/NotFound";

const router = createRouter({
    history: createWebHistory(),

    routes: [
        { path: '/main', component: ()=> import("@/components/Main"),
            children: [
                {
                    path: 'space-marine',
                    component:  ()=> import("@/components/SpaceMarine.vue"),
                },
                {
                    path: 'profile',
                    component: ()=> import("@/components/Profile"),
                },
                {
                    path: 'starship',
                    component:  ()=> import("@/components/StarShip.vue"),
                },

                {
                    path: '/notfound',
                    component: ()=> import("@/components/NotFound")
                }
            ],
        },
        { path: '/login', component: Login, children:
            [
                {
                    path: 'signIn',
                    component: SignIn,
                },
                {
                    path: 'signUp',
                    component: SignUp,
                }
            ]
        },
        { path: '/notfound', component: NotFound}
    ]
})

// router.beforeEach((to, from, next) => {
//     const publicPages = ['/login/signIn', '/login/signUp'];
//     const authRequired = !publicPages.includes(to.path);
//     const loggedIn = store.state.status.loggedIn;
//
//     // trying to access a restricted page + not logged in
//     // redirect to login page
//
//     // TODO пока оставлено так для реализации основного функционала (расскоменти потом)
//
//     // if (authRequired && !loggedIn) {
//     //     localStorage.removeItem('user')
//     //     next('/login/signIn');
//     // } else {
//         next();
//     // }
//
// });

export default router