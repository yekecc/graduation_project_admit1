<script setup>
import { ref, defineComponent, onMounted, computed } from 'vue';
import axios from 'axios';
import Class from './Class.vue';
import { Form, Input, Button, Modal, InputNumber, Table } from 'ant-design-vue';
import ClassData from '../../store/roomdata';
import { getRoom } from '../../api/RoomData';
const fff = () => {
    console.log('1111111111111111111111111')
}
const ssss = new ClassData();
const classData = ref([]);

const RoomData = ref([]);
const roomType = ref([]);

const { updata } = ClassData();
const handleClick = (e) => {
    console.log(e.props.label);
};

onMounted(async () => {
    try {
        const res = await getRoom();
        console.log("11111111111111111111111111111", res);
        console.log(res.data);
        RoomData.value = res.data.data;
        updata(RoomData.value);
        console.log(RoomData.value.length);

        roomType.value = [...new Set(RoomData.value.map(item => item.roomType))];
        console.log(roomType.value);
    } catch (error) {
        console.error(error);
    }
});

const activeName = ref('first');
const nameValue = ref('');
const timeValue = ref([]);
const typeValue = ref([]);
const showModal = ref(false);

const options = [
    {
        value: '8:30-9:50',
        label: '8:30-9:50',
    }, {
        value: '10:10-11:30',
        label: '10:10-11:30',
    }, {
        value: '13:50-15:10',
        label: '13:50-15:10',
    }, {
        value: '15:20-16:40',
        label: '15:20-16:40',
    }
];

const typeOptions = [
    {
        value: '音乐室',
        label: '音乐室',
    }, {
        value: '美术室',
        label: '美术室',
    }, {
        value: '教室',
        label: '教室',
    }, {
        value: '会议室',
        label: '会议室',
    }
];

const formState = ref({
    name: '',
    time1: '',
    number1: 0,
});

const handleFinish = (values) => {
    classData.value.push(values);
    showModal.value = false;
};

const ClassDataComputed = computed(() => {
    return ssss.data.value;
});
</script>


<template>
    <a-button type="primary" @click="showModal = true" style="margin: 10px;" v-on="fff">添加</a-button>
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label='音乐室' name="first">
            <Class />
        </el-tab-pane>
        <el-tab-pane label="美术室" name="second">
            <Class />
        </el-tab-pane>
        <el-tab-pane label="教室" name="third">
            <Class />
        </el-tab-pane>
        <el-tab-pane label="会议室" name="fourth">
            <Class />
        </el-tab-pane>
    </el-tabs>

    <a-modal v-model:visible="showModal" title="添加教室信息">
        <a-form :model="formState" @finish="handleFinish">
            <a-form-item label="教室" name="name">
                <a-input v-model:value="nameValue" />
            </a-form-item>
            <a-form-item label="时间" name="time1">
                <a-cascader v-model:value="timeValue" :options="options" placeholder="请选择时间段" />
            </a-form-item>
            <a-form-item label="类型" name="type">
                <a-cascader v-model:value="typeValue" :options="typeOptions" placeholder="请选择类型" />
            </a-form-item>
            <a-form-item>
                <a-button type="primary" html-type="submit">提交</a-button>
            </a-form-item>
        </a-form>
    </a-modal>
</template>



<style></style>