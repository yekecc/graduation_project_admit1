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
      <template #action="{ record }">
        <a-button type="primary" @click="editUser(record.id)">修改</a-button>
        <a-button type="primary" @click="deleteUser(record.id)">删除</a-button>
      </template>
    </a-table>

    <a-modal v-model:visible="isEditModalVisible" title="修改用户" @ok="submitEdit">
      <a-form v-if="editForm.value" :model="editForm.value" :label-col="{ span: 4 }" :wrapper-col="{ span: 14 }">
        <a-form-item label="用户名">
          <a-input v-model:value="editForm.value.username" />
        </a-form-item>
        <a-form-item label="用户编号">
          <a-input v-model:value="editForm.value.userNumber" />
        </a-form-item>
        <a-form-item label="角色">
          <a-select v-model:value="editForm.value.role" placeholder="请选择角色">
            <a-select-option :value="1">管理员</a-select-option>
            <a-select-option :value="0">普通用户</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="电话">
          <a-input v-model:value="editForm.value.telephone" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { SearchOutlined } from '@ant-design/icons-vue';
import { Modal } from 'ant-design-vue';

import { getAllUsers, updateUser } from '../../api/UserData';
import * as XLSX from 'xlsx';
import { saveAs } from 'file-saver';
import { delUser } from '../../api/UserData';
import { message } from 'ant-design-vue';

const searchText = ref('');
const searchedColumn = ref('');
const data = ref([]);
const searchInput = ref();
const isEditModalVisible = ref(false);
const editForm = ref({
  value: {
    id: '',
    username: '',
    userNumber: '',
    telephone: '',
    role: 0,
  },
});

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
    title: '角色',
    dataIndex: 'role',
    key: 'role',
    customRender: ({ text }) => (text === 1 ? '管理员' : '普通用户'),
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

const handleReset = (clearFilters) => {
  clearFilters();
  searchText.value = '';
};

const fetchUsers = async () => {
  try {
    const response = await getAllUsers();
    if (response.data.code === 200) {
      data.value = response.data.data;
      console.log(data.value);
    } else {
      console.error('获取用户数据失败:', response.data.message);
    }
  } catch (error) {
    console.error('请求失败:', error);
  }
};

const deleteUser = async (userId) => {
  try {
    Modal.confirm({
      title: '确认删除',
      content: '确定要删除这个用户吗？此操作不可恢复。',
      okText: '确认',
      cancelText: '取消',
      async onOk() {
        const response = await delUser(userId);
        if (response.data.code === 200) {
          await fetchUsers();
        } else {
          console.error('删除用户失败:', response.data.message);
        }
      },
      onCancel() {
        console.log('取消删除');
      },
    });
  } catch (error) {
    console.error('删除请求失败:', error);
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

const editUser = async (userId) => {
  const foundUser = data.value.find((user) => user.id === userId);
  if (foundUser) {
    editForm.value = {
      value: {
        id: foundUser.id,
        username: foundUser.username || '',
        userNumber: foundUser.userNumber || '',
        telephone: foundUser.telephone || '',
        role: foundUser.role || 0,
      },
    };
    console.log('editForm:', editForm.value); // 打印 editForm 的值
    isEditModalVisible.value = true;
  } else {
    console.error('未找到用户:', userId);
    message.error('未找到用户');
  }
};

const submitEdit = async () => {
  const formdata = {
    id: 0,
    username: '',
    userNumber: '',
    telephone: '',
    role: 0

  }
  console.log(editForm.value.value)
  formdata.id = editForm.value.value.id
  formdata.role = editForm.value.value.role
  formdata.username = editForm.value.value.userNumber
  formdata.telephone = editForm.value.value.telephone
  formdata.userNumber = editForm.value.value.userNumber
  console.log('提交的 formData:', formdata); // 打印 formData 的值

  // 确保将 editForm.value 直接传递给 updateUser
  try {
    const response = await updateUser(formdata); // 直接传递 editForm.value
    if (response.data.code === 200) {
      message.success('修改成功');
      isEditModalVisible.value = false;
      await fetchUsers();
    } else {
      console.error('修改失败:', response.data.message);
      message.error(`修改失败: ${response.data.message}`);
    }
  } catch (error) {
    console.error('请求失败:', error);
    message.error('请求失败，请稍后重试');
  }
  fetchUsers();
};
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