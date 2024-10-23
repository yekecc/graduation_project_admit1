
import { createRouter, createWebHistory } from 'vue-router'

import Layout from '../view/Layout/page.vue'
const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('../view/login/index.vue')
    },
    {
        path: '/Layout',
        name: 'Layout',
        component: Layout,

        redirect: "/Layout",
        children: [{
            path: '/Layout',
            component: import('../view/Layout/AppointmentList.vue'),
            meta: {
                title: '预约列表'
            }
        }, {
            path: "/UserList",
            component: () => import('../view/Layout/UserList.vue'),
            meta: {
                title: '用户列表'
            }
        }, {
            path: '/ClassList1',
            component: () => import('../view/Layout/ClassList1.vue'),
            meta: {
                title: '课室列表'
            }
        }]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router