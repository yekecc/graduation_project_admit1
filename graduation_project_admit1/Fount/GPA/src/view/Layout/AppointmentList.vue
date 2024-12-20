<template>
  <h2 style="margin-bottom: 16px;">预约列表</h2>

  <a-button @click="exportToExcel" type="primary" style="margin: 16px;">导出 Excel</a-button>
  <a-table :data-source="reservations" :columns="columns" :row-key="record => record.id" :pagination="{
    pageSize: 5,
    showSizeChanger: false,
    showQuickJumper: true,
    showTotal: total => `共 ${total} 条`
  }">

    <template #filterDropdown="{ setSelectedKeys, selectedKeys, confirm, clearFilters, column }">
      <div style="padding: 8px">
        <a-input ref="searchInput" :placeholder="`Search ${column.dataIndex}`" :value="selectedKeys[0]"
          style="width: 188px; margin-bottom: 8px; display: block"
          @change="e => setSelectedKeys(e.target.value ? [e.target.value] : [])"
          @pressEnter="handleSearch(selectedKeys, confirm, column.dataIndex)" />
        <a-button size="small" style="width: 90px; margin-right: 8px" type="primary"
          @click="handleSearch(selectedKeys, confirm, column.dataIndex)">
          <template #icon>
            <SearchOutlined />
          </template>
          Search
        </a-button>
        <a-button size="small" style="width: 90px" @click="handleReset(clearFilters)">
          Reset
        </a-button>
      </div>
    </template>
    <template #filterIcon="filtered">
      <search-outlined :style="{ color: filtered ? '#108ee9' : undefined }" />
    </template>
    <template #customRender="{ text, column }">
      <span v-if="searchText && searchedColumn === column.dataIndex">
        <template v-for="(fragment, i) in text
          .toString()
          .split(new RegExp(`(?<=${searchText})|(?=${searchText})`, 'i'))">
          <mark v-if="fragment.toLowerCase() === searchText.toLowerCase()" :key="i" class="highlight">
            {{ fragment }}
          </mark>
          <template v-else>{{ fragment }}</template>
        </template>
      </span>
      <template v-else>
        {{ text }}
      </template>
    </template>
    <template #action="{ record }">
      <a-button type="primary" @click="deleteReservation(record.id)">删除</a-button>
    </template>
  </a-table>

</template>

<script setup>
import { onMounted, ref } from 'vue';
import { saveAs } from 'file-saver';
import * as XLSX from 'xlsx';
import { getReservations } from '../../api/RoomData'; // 导入新的 API 方法
import { SearchOutlined } from '@ant-design/icons-vue';
import { delreservation } from "../../api/reservstion.js";
import { Modal } from 'ant-design-vue';

const searchText = ref('');
const searchedColumn = ref('');
const reservations = ref([]); // 使用 ref 创建响应式数据
const searchInput = ref();

// 获取预约数据
const fetchReservations = async () => {
  try {
    const response = await getReservations(); // 使用封装的 API 方法
    const result = response.data; // 根据实际返回结构调整
    if (result.code === 200) {
      reservations.value = result.data; // 存储获取到的预约数据
    } else {
      console.error('获取预约数据失败:', result.message);
    }
  } catch (error) {
    console.error('请求失败:', error);
  }
};

// 导出 Excel
const exportToExcel = () => {
  const ws = XLSX.utils.json_to_sheet(reservations.value);
  const wb = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(wb, ws, 'Reservations');
  const excelBuffer = XLSX.write(wb, { bookType: 'xlsx', type: 'array' });
  const data = new Blob([excelBuffer], { type: 'application/octet-stream' });
  saveAs(data, 'reservations.xlsx');
};

const deleteReservation = async (reservationId) => {
  try {
    Modal.confirm({
      title: '确认删除',
      content: '确定要删除这个预约记录吗？此操作不可恢复。',
      okText: '确认',
      cancelText: '取消',
      async onOk() {
        const response = await delreservation(reservationId);
        const result = response.data;
        if (result.code === 200) {
          await fetchReservations();
          console.log('删除成功:', result.message);
        } else {
          console.error('删除失败:', result.message);
        }
      },
      onCancel() {
        console.log('取消删除');
      },
    });
  } catch (error) {
    console.error('请求失败:', error);
  }
};

// 在组件挂载时调用接口
onMounted(() => {
  fetchReservations();
});

const columns = [
  {
    title: 'OpenID',
    dataIndex: 'openid',
    key: 'openid',
    slots: {
      headerCell: 'headerCell',
      bodyCell: 'bodyCell',
    },
    onFilter: (value, record) =>
      record.openid.toString().toLowerCase().includes(value.toLowerCase()),
    onFilterDropdownOpenChange: visible => {
      if (visible) {
        setTimeout(() => {
          searchInput.value.focus();
        }, 100);
      }
    },
  },
  {
    title: '预约教室',
    dataIndex: 'roomName',
    key: 'roomName',
    sorter: (a, b) => a.roomName.localeCompare(b.roomName),
  },
  {
    title: '预约日期',
    dataIndex: 'reservationDate',
    key: 'reservationDate',
    sorter: (a, b) => new Date(a.reservationDate) - new Date(b.reservationDate),
  },
  {
    title: '时间段',
    dataIndex: 'timeSlot',
    key: 'timeSlot',
    customRender: ({ text }) =>
      text == 1 ? '8:30-9:50' :
        text == 2 ? '10:10-11:30' :
          text == 3 ? '13:50-15:10' :
            text == 4 ? '15:20-16:40' :
              '未知时间段',
  },
  {
    title: '使用情况',
    dataIndex: 'status',
    key: 'status',
    customRender: ({ text }) =>
      text == 1 ? '未使用' : "已使用"
  },
  {
    title: '操作',
    key: 'operation',
    fixed: 'right',
    width: 100,
    slots: {
      customRender: 'action',
    },
  },
];
</script>
<style scoped>
.highlight {
  background-color: rgb(255, 192, 105);
  padding: 0px;
}
</style>