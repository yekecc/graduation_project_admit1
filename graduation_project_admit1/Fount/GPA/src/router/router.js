import { createRouter, createWebHistory } from 'vue-router'

import Layout from '../view/Layout/page.vue'

const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('../view/login/index.vue'),
    }, {
        path: '/',
        name: 'Layout',
        component: Layout,
        redirect: "/login",
        children: [{
            path: '/StatisticsReport',
            component: () => import('../view/Layout/StatisticsReport.vue'),
            meta: {
                title: '统计报表'
            }
        }, {
            path: '/Layout',
            component: () => import('../view/Layout/AppointmentList.vue'),
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
        },]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

// 添加全局前置守卫
router.beforeEach((to, from, next) => {
    if (to.path === '/') {
        next('/login')
    } else {
        next()
    }
})

export default router