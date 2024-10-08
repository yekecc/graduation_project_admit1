<template>
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="音乐室" name="first">
            <MusicClass />
        </el-tab-pane>
        <el-tab-pane label="美术室" name="second">
            <ArtClass />
        </el-tab-pane>
        <el-tab-pane label="教室" name="third">
            <ClassRoom />
        </el-tab-pane>
        <el-tab-pane label="会议室" name="fourth">
            <Meeting />
        </el-tab-pane>
    </el-tabs>
</template>
<script setup>
import { ref } from 'vue'
import MusicClass from './Class/MusicClass.vue';
import ArtClass from './Class/ArtClass.vue';
import ClassRoom from './Class/ClassRoom.vue';
import Meeting from './Class/Meeting.vue';
const activeName = ref('first')


</script>

<style>
/* .demo-tabs>.el-tabs__content {
    padding: 32px;
    color: #6b778c;
    font-size: 32px;
    font-weight: 600;
} */
</style>
