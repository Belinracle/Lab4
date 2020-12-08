import Vue from 'vue'
import Router from 'vue-router'
import Start from "./views/Start.vue";
import Main from "./views/Main.vue";

Vue.use(Router)
export default new Router({
    mode: 'hash',
    routes:[
        {
            path: '/',
            component: Start
        },
        {
            path:'/main',
            component: Main
        }
    ]
    }
)