<template>
  <a-card title="科室列表">
    <div style="margin-bottom: 16px;">
      <a-button type="primary" @click="showAddRoomModal">添加教室</a-button>
      <a-button style="margin-left: 8px;" type="default" @click="exportExcel">导出 Excel</a-button>
    </div>
    <a-table :dataSource="rooms" :rowKey="room => room.id">
      <a-table-column dataIndex="roomName" title="科室名称" />
      <a-table-column dataIndex="roomAddress" title="科室地址" />
      <a-table-column dataIndex="roomType" title="科室类型" />
      <a-table-column dataIndex="capacity" title="容量" />
      <a-table-column dataIndex="equipment" title="设备" />
      <a-table-column dataIndex="status" title="状态">
        <template #default="{ record }">
          {{ record.status ? '可用' : '不可用' }}
        </template>
      </a-table-column>
      <a-table-column title="操作">
        <template #default="{ record }">
          <a-button type="primary" @click="deleteRoom(record.id)">删除</a-button>
        </template>
      </a-table-column>
    </a-table>

    <!-- 添加教室模态框 -->
    <a-modal v-model:visible="isAddRoomModalVisible" title="添加教室" @cancel="isAddRoomModalVisible = false" @ok="addr">
      <a-form ref="addRoomForm" :model="newRoom" :rules="formRules" autocomplete="off">
        <a-form-item label="科室名称" name="roomName">
          <a-input v-model:value="newRoom.roomName" />
        </a-form-item>
        <a-form-item label="科室地址" name="roomAddress">
          <a-input v-model:value="newRoom.roomAddress" />
        </a-form-item>
        <a-form-item label="科室类型" name="roomType">
          <a-input v-model:value="newRoom.roomType" />
        </a-form-item>
        <a-form-item label="容量" name="capacity">
          <a-input v-model:value="newRoom.capacity" type="number" />
        </a-form-item>
        <a-form-item label="设备" name="equipment">
          <a-input v-model:value="newRoom.equipment" />
        </a-form-item>
      </a-form>
    </a-modal>
  </a-card>
</template>

<script setup>
import { onMounted, ref, reactive } from 'vue';
import { addroom, getRoom, delroom } from '../../api/RoomData'; // 引入获取科室信息的 API
import * as XLSX from 'xlsx'; // 引入 xlsx 库
import { Modal } from 'ant-design-vue';

const rooms = ref([]); // 用于存储科室信息
const isAddRoomModalVisible = ref(false); // 控制模态框的显示
const newRoom = reactive({ // 新教室信息
  roomName: '',
  roomAddress: '',
  roomType: '',
  capacity: 1, // 初始值设置为 1
  equipment: '',
  status: true // 默认状态为可用
});
const addRoomForm = ref(null); // 确保正确定义表单引用

const formRules = {
  roomName: [
    { required: true, message: '请输入科室名称', trigger: 'blur' },
    { min: 2, max: 20, message: '科室名称长度必须在2-20个字符之间', trigger: 'blur' }
  ],
  roomAddress: [
    { required: true, message: '请输入科室地址', trigger: 'blur' },
    { min: 5, max: 50, message: '科室地址长度必须在5-50个字符之间', trigger: 'blur' }
  ],
  roomType: [
    { required: true, message: '请输入科室类型', trigger: 'blur' },
    { min: 2, max: 20, message: '科室类型长度必须在2-20个字符之间', trigger: 'blur' }
  ],
  capacity: [
    { required: true, message: '请输入容量', trigger: 'blur' }
  ],
  equipment: [
    { required: true, message: '请输入设备信息', trigger: 'blur' },
    { min: 2, max: 100, message: '设备信息长度必须在2-100个字符之间', trigger: 'blur' }
  ]
};

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

const addr = async () => {
  try {
    console.log('提交前的 capacity:', newRoom.capacity); // 调试信息

    // 强制将 capacity 转换为数字
    newRoom.capacity = Number(newRoom.capacity);

    console.log('转换后的 capacity:', newRoom.capacity); // 调试信息

    // 验证表单
    await addRoomForm.value.validate();

    // 提交数据
    await addroom(newRoom);
    rooms.value.push({ ...newRoom, id: rooms.value.length + 1 });
    isAddRoomModalVisible.value = false;

    // 重置表单
    addRoomForm.value.resetFields();
  } catch (error) {
    console.error('表单验证失败:', error);
  }
};

const exportExcel = () => {
  const worksheet = XLSX.utils.json_to_sheet(rooms.value); // 将数据转换为工作表
  const workbook = XLSX.utils.book_new(); // 创建新工作簿
  XLSX.utils.book_append_sheet(workbook, worksheet, '科室信息'); // 将工作表添加到工作簿
  XLSX.writeFile(workbook, '科室信息.xlsx'); // 导出 Excel 文件
};

const deleteRoom = async (roomId) => {
  try {
    // 使用 Modal.confirm 的正确方式，通过 Promise 处理用户响应
    Modal.confirm({
      title: '确认删除',
      content: '确定要删除这个科室吗？此操作不可恢复。',
      okText: '确认',
      cancelText: '取消',
      async onOk() {
        // 用户点击确认后执行删除操作
        const response = await delroom(roomId);
        if (response.data.code === 200) {
          await fetchRooms();
        } else {
          console.error('删除科室失败:', response.data.message);
        }
      },
      onCancel() {
        // 用户点击取消时不执行任何操作
        console.log('取消删除');
      },
    });
  } catch (error) {
    console.error('删除请求失败:', error);
  }
};

onMounted(() => {
  fetchRooms(); // 组件挂载时调用获取科室信息的方法
});
</script>

<style>
/* 可以根据需要添加样式 */
</style>