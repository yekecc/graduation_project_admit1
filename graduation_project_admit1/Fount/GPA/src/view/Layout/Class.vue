<template>
    <a-table :columns="columns" :dataSource="data" :scroll="{ x: 1300, y: 1000 }">
        <template #bodyCell="{ column }">
            <template v-if="column.key === 'operation'">
                <a-button type="primary" style="margin-right:8px ;">修改</a-button>
                <a-button type="primary">删除</a-button>
            </template>
        </template>
    </a-table>
</template>
<script setup>
import { onMounted, ref, watch } from 'vue';
import ClassData from '../../store/roomdata';
const data = ref([]);
const ClassData1 = new ClassData();
// const props = defineProps({ rd: Object });
// watch(props.rd, (n, o) => {
//     console.log(n, o);
// });
// onMounted(() => {
//     console.log('111', ClassData1.data)
//     data.value = ClassData1.data
//     console.log('222', data.value)
// })
// const data = ref([])
onMounted(() => {
    // console.log('22222222222222222222', ClassData1.data)
    // ClassData1.updata(props.rd);
    data.value = ClassData1.data
    data.value = ClassData1.data.map(item => ({
        ...item,
        time1: '8:30-9:50',
        time2: '10:10-11:30',
        time3: '13:50-15:10',
        time4: '15:20-16:40',
        status01: 0 ? '已借出' : '未借出',
        status02: 0 ? '已借出' : '未借出',
        status03: 0 ? '已借出' : '未借出',
        status04: 0 ? '已借出' : '未借出'
    })
    )
})
const columns = [
    {
        title: '教室',
        width: 150,
        dataIndex: 'roomName',
        key: 'name',
        fixed: 'left',
    },
    {
        title: '时间段',
        dataIndex: 'time1',
        key: '1',
        fixed: 'true',
    },
    {
        title: '状态',
        dataIndex: 'status01',
        key: '2',
    },
    {
        title: '时间段',
        dataIndex: 'time2',
        key: '3',
        fixed: 'true',
    },
    {
        title: '状态',
        dataIndex: 'status02',
        key: '4',
    },
    {
        title: '时间段',
        dataIndex: 'time3',
        key: '5',
        fixed: 'true',
    },
    {
        title: '状态',
        dataIndex: 'status03',
        key: '6',
    },
    {
        title: '时间段',
        dataIndex: 'time4',
        key: '7',
        fixed: 'true',
    },
    {
        title: '状态',
        dataIndex: 'status04',
        key: '8',
    },
    {
        title: '编辑',
        key: 'operation',
        fixed: 'right',
        width: 170,
    },
];
// export default {
//     props: {
//         'lxy': {
//             type: Object,
//             default: []
//         }
//     }
// }

// export { };
</script>