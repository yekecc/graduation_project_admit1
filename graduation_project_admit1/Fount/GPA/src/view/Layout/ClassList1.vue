<template>
  <a-card title="科室列表">
    <div style="margin-bottom: 16px;">
      <a-button type="primary" @click="showAddRoomModal">添加教室</a-button>
      <a-button type="default" @click="exportExcel" style="margin-left: 8px;">导出 Excel</a-button>
    </div>
    <a-table :dataSource="rooms" :rowKey="room => room.id">
      <a-table-column title="科室名称" dataIndex="roomName" />
      <a-table-column title="科室地址" dataIndex="roomAddress" />
      <a-table-column title="科室类型" dataIndex="roomType" />
      <a-table-column title="容量" dataIndex="capacity" />
      <a-table-column title="设备" dataIndex="equipment" />
      <a-table-column title="状态">
        <template #default="{ record }">
          {{ record.status ? '可用' : '不可用' }}
        </template>
      </a-table-column>
    </a-table>

    <!-- 添加教室模态框 -->
    <a-modal v-model:visible="isAddRoomModalVisible" title="添加教室" @ok="addRoom" @cancel="isAddRoomModalVisible = false">
      <a-form>
        <a-form-item label="科室名称">
          <a-input v-model="newRoom.roomName" />
        </a-form-item>
        <a-form-item label="科室地址">
          <a-input v-model="newRoom.roomAddress" />
        </a-form-item>
        <a-form-item label="科室类型">
          <a-input v-model="newRoom.roomType" />
        </a-form-item>
        <a-form-item label="容量">
          <a-input type="number" v-model="newRoom.capacity" />
        </a-form-item>
        <a-form-item label="设备">
          <a-input v-model="newRoom.equipment" />
        </a-form-item>
      </a-form>
    </a-modal>
  </a-card>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { getRoom } from '../../api/RoomData'; // 引入获取科室信息的 API
import * as XLSX from 'xlsx'; // 引入 xlsx 库

const rooms = ref([]); // 用于存储科室信息
const isAddRoomModalVisible = ref(false); // 控制模态框的显示
const newRoom = ref({ // 新教室信息
  roomName: '',
  roomAddress: '',
  roomType: '',
  capacity: null,
  equipment: '',
  status: true // 默认状态为可用
});

const fetchRooms = async () => {
  try {
    const response = await getRoom(); // 调用 API 获取科室信息
    if (response.data.code === 200) {
      rooms.value = response.data.data; // 更新科室信息
    } else {
      console.error(response.data.message); // 处理失败情况
    }
  } catch (error) {
    console.error("获取科室信息失败:", error); // 处理请求错误
  }
};

const showAddRoomModal = () => {
  isAddRoomModalVisible.value = true; // 显示添加教室模态框
};

const addRoom = () => {
  // 这里可以添加逻辑将新教室信息发送到后端
  rooms.value.push({ ...newRoom.value, id: rooms.value.length + 1 }); // 模拟添加教室
  isAddRoomModalVisible.value = false; // 关闭模态框
  newRoom.value = { roomName: '', roomAddress: '', roomType: '', capacity: null, equipment: '', status: true }; // 重置表单
};

const exportExcel = () => {
  const worksheet = XLSX.utils.json_to_sheet(rooms.value); // 将数据转换为工作表
  const workbook = XLSX.utils.book_new(); // 创建新工作簿
  XLSX.utils.book_append_sheet(workbook, worksheet, '科室信息'); // 将工作表添加到工作簿
  XLSX.writeFile(workbook, '科室信息.xlsx'); // 导出 Excel 文件
};

onMounted(() => {
  fetchRooms(); // 组件挂载时调用获取科室信息的方法
});
</script>

<style>
/* 可以根据需要添加样式 */
</style>