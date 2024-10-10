<template>
    <!-- <a-button type="primary" @click="showModal = true" style="margin: 10px;">添加</a-button> -->
    <a-table :columns="columns" :data-source="data" :scroll="{ x: 1300 }">
        <template #action>
            <a-button type="primary" style="margin-right:8px ;">修改</a-button>
            <a-button type="primary">删除</a-button>
        </template>
    </a-table>
    <a-modal v-model:visible="showModal" title="添加教室信息">
        <a-form :model="formState" @finish="handleFinish">
            <a-form-item label="教室" name="name">
                <a-input v-model:value="formState.name" />
            </a-form-item>
            <a-form-item label="时间" name="time1">
                <a-cascader v-model:value="value" :options="options" placeholder="请选择时间段" />
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
<script>
import { defineComponent } from 'vue';
import { Form, Input, Button, Modal, InputNumber, Table } from 'ant-design-vue';
import { ref } from 'vue';
const columns = [
    {
        title: '教室',
        width: 150,
        dataIndex: 'name',
        key: 'name',
        fixed: 'left',
    },
    // {
    //     title: '可预约总数量',
    //     width: 80,
    //     dataIndex: 'total_quantity',
    //     key: 'total_quantity',
    //     fixed: 'left',
    // },
    {
        title: '时间段',
        dataIndex: 'time1',
        key: '1',
    },
    {
        title: '状态',
        dataIndex: 'status1',
        key: '2',
    },
    {
        title: '时间段',
        dataIndex: 'time2',
        key: '3',
    },
    {
        title: '状态',
        dataIndex: 'status2',
        key: '4',
    },
    {
        title: '时间段',
        dataIndex: 'time3',
        key: '5',
    },
    {
        title: '状态',
        dataIndex: 'status3',
        key: '6',
    },
    {
        title: '时间段',
        dataIndex: 'time4',
        key: '7',
    },
    {
        title: '状态',
        dataIndex: 'status4',
        key: '8',
    },
    {
        title: '编辑',
        key: 'operation',
        fixed: 'right',
        width: 200,
        slots: {
            customRender: 'action',
        },
    },
];
const data = [
    {
        key: '1',
        name: '科技楼201',
        // total_quantity: 20,
        time1: '8:30-9:50',
        status1: '可预约',
        time2: '10:10-11:30',
        status2: '可预约',
        time3: '13:50-15:10',
        status3: '不可预约',
        time4: '15:20-16:40',
        status4: '可预约',
    },
    {
        key: '2',
        name: '科技楼202',
        // total_quantity: 20,
        time1: '8:30-9:50',
        status1: '可预约',
        time2: '10:10-11:30',
        status2: '可预约',
        time3: '13:50-15:10',
        status3: '不可预约',
        time4: '15:20-16:40',
        status4: '可预约',
    },
];
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
export default defineComponent({
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
            data,
            columns,
            showModal,
            formState,
            handleFinish,
            value: ref([]),
            options,
        };
    },
});
</script>