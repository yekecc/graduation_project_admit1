import { createRouter, createWebHistory } from 'vue-router'


const routes = [
    {
        path:'/login',
        name:'login',
        component:()=>import('../view/login/index.vue')
    },{
        path:'/Layout',
        name:'Layout',
        redirect: "/UserDashboard",
        children:[{
            path:'',
            component:()=>import('../view/Layout/page.vue')
        }]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
  })

export default router