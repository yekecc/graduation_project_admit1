<script setup>
import {computed} from 'vue';
import {useRouter} from 'vue-router'

const router = useRouter()
const routes = computed(() => {
  return router.options.routes[1].children
})
console.log(router.options.routes[1].children)
</script>
<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="collapsed" collapsible>
      <div class="logo">Low 狗</div>
      <a-menu v-model:selectedKeys="selectedKeys" mode="inline" theme="dark">
        <a-menu-item v-for="(r, i) in routes" :key="i">
          <router-link :to="r.path">
            <span>{{ r.meta.title }}</span>
          </router-link>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0"/>
      <a-layout-content style="margin: 0 16px">
        <a-breadcrumb style="margin: 16px 0">

        </a-breadcrumb>
        <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
          <router-view></router-view>
        </div>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<style>
#components-layout-demo-side .logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout .site-layout-background {
  background: #fff;
}

[data-theme='dark'] .site-layout .site-layout-background {
  background: #141414;
}

.logo {
  font-size: 15px;
  background-color: rgba(255, 255, 255, 0.5);
  padding: 10px;
  margin: 18px;
  text-align: center;
  border-radius: 5px;
}
</style>
