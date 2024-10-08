<template>
    <a-table :columns="columns" :data-source="data" :scroll="{ x: 1300 }">
        <template #action>
            <a-button type="primary" style="margin-right:8px ;">修改</a-button>
            <a-button type="primary">删除</a-button>
        </template>
    </a-table>
</template>
<script>
import { defineComponent } from 'vue';
const columns = [
    {
        title: '教室',
        width: 150,
        dataIndex: 'name',
        key: 'name',
        fixed: 'left',
    },
    {
        title: '可预约总数量',
        width: 80,
        dataIndex: 'total_quantity',
        key: 'total_quantity',
        fixed: 'left',
    },
    {
        title: '时间段',
        dataIndex: 'time1',
        key: '1',
    },
    {
        title: '数量',
        dataIndex: 'number1',
        key: '2',
    },
    {
        title: '时间段',
        dataIndex: 'time2',
        key: '3',
    },
    {
        title: '数量',
        dataIndex: 'number2',
        key: '4',
    },
    {
        title: '时间段',
        dataIndex: 'time3',
        key: '5',
    },
    {
        title: '数量',
        dataIndex: 'number3',
        key: '6',
    },
    {
        title: '时间段',
        dataIndex: 'time4',
        key: '7',
    },
    {
        title: '数量',
        dataIndex: 'number4',
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
        name: '教学楼601',
        total_quantity: 20,
        time1: '8:30-9:50',
        number1: 2,
        time2: '10:10-11:30',
        number2: 3,
        time3: '13:50-15:10',
        number3: 4,
        time4: '15:20-16:40',
        number4: 5,
    },
    {
        key: '2',
        name: '教学楼602',
        total_quantity: 20,
        time1: '8:30-9:50',
        number1: 5,
        time2: '10:10-11:30',
        number2: 4,
        time3: '13:50-15:10',
        number3: 3,
        time4: '15:20-16:40',
        number4: 2,
    },
];
export default defineComponent({
    data() {
        return {
            data,
            columns,
        };
    },
});
</script>