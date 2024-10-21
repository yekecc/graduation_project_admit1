<script setup>
import { onMounted, ref, computed, watch } from 'vue';
import { getRoom, updateRoom } from '../../api/RoomData';
import Class from './Class.vue';
const RoomData = ref([]);
import ClassData from '../../store/roomdata';
import _ from 'lodash';
const { updata } = ClassData();

const roomType = ref([]);

const key = ref(0);
onMounted(async () => {
    try {
        const res = await getRoom();
        RoomData.value = res.data.data;
    } catch (error) {
        console.error(error);
    }
});
const activeName = ref('');
const groupedData = computed(() => {
    const r = _(RoomData.value).groupBy('roomType').value();
    if (Object.keys(r).length > 0) {
        activeName.value = Object.keys(r)[0];
    }
    return r;
});

watch(activeName, (newVal, oldVal) => {
    if (newVal !== '') {
        updata(groupedData.value[newVal]);
    }
});

watch(activeName, function (new_value, old_value) {
    if (new_value != '') {
        updata(groupedData.value[new_value]);
    }
});
const nameValue = ref('');
const timeValue = ref([]);
const typeValue = ref([]);
const addressValue = ref('');
const descriptionValue = ref('');
const showModal = ref(false);

// const options = [
//     {
//         value: '8:30-9:50',
//         label: '8:30-9:50',
//     }, {
//         value: '10:10-11:30',
//         label: '10:10-11:30',
//     }, {
//         value: '13:50-15:10',
//         label: '13:50-15:10',
//     }, {
//         value: '15:20-16:40',
//         label: '15:20-16:40',
//     }
// ];

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
    roomName: '',
    roomAddress: '',
    roomDescription: '',
    roomType: '',
});

const handleTabClick = (tab) => {
    console.log(tab);
};

const ClassDataComputed = computed(() => {
    return ssss.data.value;
});

const handleOk = async () => {
    formState.value.roomType = formState.value.roomType[formState.value.roomType.length - 1];
    console.log('11111111111', formState.value);
    const res = await updateRoom(formState.value);
    console.log('res', res);
    formState.value = {
        roomName: '',
        roomAddress: '',
        roomDescription: '',
        roomType: '',
    };

    showModal.value = false;
};



</script>

<template>
    <a-button type="primary" @click="showModal = true" style="margin: 10px;">添加</a-button>
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleTabClick">
        <el-tab-pane :label="type" :name="type" v-for="type in Object.keys(groupedData)" :key="type"></el-tab-pane>
        <Class :key="activeName" :dataSource="groupedData[activeName]" />
    </el-tabs>

    <a-modal v-model:visible="showModal" title="添加教室信息" @ok="handleOk">
        <a-form :model="formState" @finish="handleFinish">
            <a-form-item label="教室" name="name">
                <a-input v-model:value="formState.roomName" />
            </a-form-item>
            <a-form-item label="地址" name="Address">
                <a-input v-model:value="formState.roomAddress" />
            </a-form-item>
            <a-form-item label="详细信息" name="Description">
                <a-input v-model:value="formState.roomDescription" />
            </a-form-item>
            <a-form-item label="类型" name="type">
                <a-cascader v-model:value="formState.roomType" :options="typeOptions" placeholder="请选择类型" />
            </a-form-item>
            <!-- <a-form-item>
                <a-button type="primary" html-type="submit">提交</a-button>
            </a-form-item> -->
        </a-form>
    </a-modal>
</template>

<style scoped></style>