<template>
  <div>
    <h2 style="margin-bottom: 16px;">用户列表</h2>
    <a-button type="primary" @click="exportToExcel" style="margin-bottom: 16px;">导出 Excel</a-button>
    <a-table :columns="columns" :data-source="data">
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
      <template #action>
        <a-button type="primary" @click="deleteUser(record.id)">删除</a-button>
      </template>
    </a-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { SearchOutlined } from '@ant-design/icons-vue';
import { getAllUsers } from '../../api/UserData'; // 导入新的 API 方法
import * as XLSX from 'xlsx';
import { saveAs } from 'file-saver';
import {delUser} from "../../api/UserData";

const searchText = ref('');
const searchedColumn = ref('');
const data = ref([]); // 用于存储用户数据
const searchInput = ref();

const columns = [
  {
    title: 'OpenID',
    dataIndex: 'openid',
    key: 'openid',
    onFilter: (value, record) =>
      record.openid.toString().toLowerCase().includes(value.toLowerCase()),
  },
  {
    title: '用户名',
    dataIndex: 'username',
    key: 'username',
  },
  {
    title: '用户编号',
    dataIndex: 'userNumber',
    key: 'userNumber',
  },
  {
    title: '班级',
    dataIndex: 'userClass',
    key: 'userClass',
  },
  {
    title: '电话',
    dataIndex: 'telephone',
    key: 'telephone',
  },
  {
    title: '操作',
    key: 'operation',
    fixed: 'right',
    width: 200,
    slots: {
      customRender: 'action',
    },
  },
];

const handleSearch = (selectedKeys, confirm, dataIndex) => {
  confirm();
  searchText.value = selectedKeys[0];
  searchedColumn.value = dataIndex;
};

const handleReset = clearFilters => {
  clearFilters();
  searchText.value = '';
};

// 获取用户数据
const fetchUsers = async () => {
  try {
    const response = await getAllUsers(); // 调用封装的 API 方法
    console.log(response)
    if (response.data.code === 200) {
      data.value = response.data.data; // 存储获取到的用户数据
    } else {
      console.error('获取用户数据失败:', response.data.message);
    }
  } catch (error) {
    console.error('请求失败:', error);
  }
};

const deleteUser = async (userId) => {
  try {
    const response = await delUser(userId);
    const result = response.data;
    if (result.code === 200) {
      await fetchUsers(); // 重新获取用户数据
      console.log('删除成功:', result.message);
    } else {
      console.error('删除失败:', result.message);
    }
  } catch (error) {
    console.error('请求失败:', error);
  }
};
// 导出 Excel
const exportToExcel = () => {
  const ws = XLSX.utils.json_to_sheet(data.value); // 将数据转换为工作表
  const wb = XLSX.utils.book_new(); // 创建新的工作簿
  XLSX.utils.book_append_sheet(wb, ws, '用户数据'); // 将工作表添加到工作簿
  const excelBuffer = XLSX.write(wb, { bookType: 'xlsx', type: 'array' }); // 生成 Excel 文件
  const dataBlob = new Blob([excelBuffer], { type: 'application/octet-stream' }); // 创建 Blob 对象
  saveAs(dataBlob, '用户数据.xlsx'); // 使用 file-saver 保存文件
};

// 在组件挂载时调用接口
onMounted(() => {
  fetchUsers();
});
</script>

<style scoped>
.highlight {
  background-color: rgb(255, 192, 105);
  padding: 0px;
}
</style>