<template>
    <a-button type="primary" @click="showModal = true" style="margin: 10px;">添加</a-button>
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
            <!-- <a-form-item label="数量" name="number1">
                <a-input-number v-model:value="formState.number1" />
            </a-form-item> -->
            <a-form-item>
                <a-button type="primary" html-type="submit">提交</a-button>
            </a-form-item>
        </a-form>
    </a-modal>

</template>
<script setup>
import { ref, defineComponent } from 'vue'
import MusicClass from './Class/MusicClass.vue';
import ArtClass from './Class/ArtClass.vue';
import ClassRoom from './Class/ClassRoom.vue';
import Meeting from './Class/Meeting.vue';
import { Form, Input, Button, Modal, InputNumber, Table } from 'ant-design-vue';

const activeName = ref('first')
const nameValue = ref([]);
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
]

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
]

defineComponent({
    components: {
        AButton: Button,
        ATable: Table,
        AForm: Form,
        AFormItem: Form.Item,
        AInput: Input,
        AInputNumber: InputNumber,
        AModal: Modal,
    },
    setup() {
        const showModal = ref(false);
        const formState = ref({
            name: '',
            time1: '',
            number1: 0,
        });

        const handleFinish = (values) => {
            data.push(values);
            showModal.value = false;
        };

        return {

            showModal,
            formState,
            handleFinish,
            value: ref([]),
            options,
        };
    },
});




</script>

<style>
/* .demo-tabs>.el-tabs__content {
    padding: 32px;
    color: #6b778c;
    font-size: 32px;
    font-weight: 600;
} */
</style>
