<template>
    <div class="table-container">
        <div class="table">
            <div class="table-header">
                <div style="width: 150px;">教室</div>
                <template v-for="(time, index) in times" :key="index">
                    <div>时间段</div>
                    <div>状态</div>
                </template>
                <div>编辑</div>
            </div>
            <div class="table-row" v-for="(room, roomIndex) in processedData" :key="roomIndex">
                <div>{{ room.roomName }}</div>
                <template v-for="(time, pindex) in times" :key="index">
                    <div>{{ time }}</div>
                    <div>{{ room[`status${pindex + 1}`] }}</div>
                </template>
                <div>
                    <div class="edit-buttons">
                        <!-- <button @click="editRoom(room.roomID)">修改</button> -->
                        <button @click="handleDeleteRoom(room.roomID)">删除</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import ClassData from '../../store/roomdata';
import { deleteRoom } from '../../api/RoomData';

const data = ref([]);
const ClassData1 = new ClassData();

const times = ['8:30-9:50', '10:10-11:30', '13:50-15:10', '15:20-16:40']

const handleDeleteRoom = async (roomID) => {
    try {

        const res = await deleteRoom(roomID)
        console.log('删除成功', res);
        data.value = data.value.filter(item => item.roomID !== roomID)

    } catch (error) {
        console.log('555', error);
    }
}

// const editRoom = (roomID) => {
//     console.log('666', roomID);
// }
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
// onMounted(() => {
//     console.log('22222222222222222222', ClassData1.data)
//     // ClassData1.updata(props.rd);
//     data.value = ClassData1.data
//     data.value = ClassData1.data.map(item => ({
//         ...item,
//         time1: '8:30-9:50',
//         time2: '10:10-11:30',
//         time3: '13:50-15:10',
//         time4: '15:20-16:40',
//         status01: 0 ? '已借出' : '未借出',
//         status02: 0 ? '已借出' : '未借出',
//         status03: 0 ? '已借出' : '未借出',
//         status04: 0 ? '已借出' : '未借出'
//     })
//     )
// })

const processedData = computed(() => {
    // console.log('222', data.value)
    if (data.value.length === 0) {
        return [];
    }

    const processedData = data.value.map(item => ({
        ...item,
        status1: item.status1 === 1 ? '已借出' : '未借出',
        status2: item.status2 === 1 ? '已借出' : '未借出',
        status3: item.status3 === 1 ? '已借出' : '未借出',
        status4: item.status4 === 1 ? '已借出' : '未借出'
    }));
    console.log('333', processedData);
    return processedData;
})

onMounted(() => {
    data.value = ClassData1.data
})

// const columns = [
//     {
//         title: '教室',
//         width: 150,
//         dataIndex: 'roomName',
//         key: 'name',
//         fixed: 'left',
//     },
//     {
//         title: '时间段',
//         dataIndex: 'time1',
//         key: '1',
//         fixed: 'true',
//     },
//     {
//         title: '状态',
//         dataIndex: 'status01',
//         key: '2',
//     },
//     {
//         title: '时间段',
//         dataIndex: 'time2',
//         key: '3',
//         fixed: 'true',
//     },
//     {
//         title: '状态',
//         dataIndex: 'status02',
//         key: '4',
//     },
//     {
//         title: '时间段',
//         dataIndex: 'time3',
//         key: '5',
//         fixed: 'true',
//     },
//     {
//         title: '状态',
//         dataIndex: 'status03',
//         key: '6',
//     },
//     {
//         title: '时间段',
//         dataIndex: 'time4',
//         key: '7',
//         fixed: 'true',
//     },
//     {
//         title: '状态',
//         dataIndex: 'status04',
//         key: '8',
//     },
//     {
//         title: '编辑',
//         key: 'operation',
//         fixed: 'right',
//         width: 170,
//     },
// ];

</script>

<style>
.table-container {
    overflow-x: auto;
    width: 100%;
}

.table {
    display: grid;
    grid-template-columns: repeat(10, 1fr);
    grid-auto-rows: minmax(50px, auto);
    gap: 1px;
    background-color: #ccc;
}

.table-header,
.table-row {
    display: contents;
}

.table-header div {
    background-color: #ccc;
}

.table-header>div {
    background-color: #fff;
    border-bottom: 1px solid #ccc;
    text-align: center;
    line-height: 50px;
}

.table-row>div {
    padding: 8px;
    background-color: #fff;
    /* border-bottom: 1px solid #ccc; */
    text-align: center;
    line-height: 25px;
}

.table-header>div:first-child,
.table-row>div:first-child,
.table-header>div:last-child,
.table-row>div:last-child {
    position: sticky;
    background-color: #f9f9f9;
    z-index: 1;
}

.table-header>div:first-child,
.table-row>div:first-child {
    left: 0;
}

.table-header>div:last-child,
.table-row>div:last-child {
    right: 0;
}

.table-header>div:nth-child(2) {
    position: sticky;
    top: 0;
    z-index: 2;
}

.edit-buttons {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    width: 80px;
}

.edit-buttons button {
    /* margin-left: 4px;*/
    padding: 4px 8px;
    align-items: center;
    border: none;
    background-color: #f9f9f9;
    /* cursor: pointer; */
}

.edit-buttons button:first-child {
    margin-left: 0;
}

.edit-buttons button:hover {
    background-color: #e9e9e9;
}
</style>