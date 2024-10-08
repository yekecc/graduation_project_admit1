<script setup>
import index from './view/login/index.vue'
</script>

<template>
   <a-config-provider :theme="{
        token: {
            colorPrimary: '#8ca5c0',
  }
        // f8d8df   938ba0   ebc6ce  d4dadf
    }"></a-config-provider>
<router-view></router-view>
</template>

<style scoped></style>

