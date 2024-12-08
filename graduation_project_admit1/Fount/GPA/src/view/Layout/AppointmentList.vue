<template>
  <a-table :data-source="data" :columns="columns">
    <template #filterDropdown="{ setSelectedKeys, selectedKeys, confirm, clearFilters, column }">
      <div style="padding: 8px">
        <a-input ref="searchInput" :placeholder="`Search ${column.dataIndex}`" :value="selectedKeys[0]"
                 style="width: 188px; margin-bottom: 8px; display: block"
                 @change="e => setSelectedKeys(e.target.value ? [e.target.value] : [])"
                 @pressEnter="handleSearch(selectedKeys, confirm, column.dataIndex)"/>
        <a-button type="primary" size="small" style="width: 90px; margin-right: 8px"
                  @click="handleSearch(selectedKeys, confirm, column.dataIndex)">
          <template #icon>
            <SearchOutlined/>
          </template>
          Search
        </a-button>
        <a-button size="small" style="width: 90px" @click="handleReset(clearFilters)">
          Reset
        </a-button>
      </div>
    </template>
    <template #filterIcon="filtered">
      <search-outlined :style="{ color: filtered ? '#108ee9' : undefined }"/>
    </template>
    <template #customRender="{ text, column }">
            <span v-if="searchText && searchedColumn === column.dataIndex">
                <template v-for="(fragment, i) in text
                    .toString()
                    .split(new RegExp(`(?<=${searchText})|(?=${searchText})`, 'i'))">
                    <mark v-if="fragment.toLowerCase() === searchText.toLowerCase()" class="highlight" :key="i">
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
      <a-button type="primary" style="margin-right:8px ;">修改</a-button>
      <a-button type="primary">删除</a-button>
    </template>
  </a-table>
</template>
<script>
import {SearchOutlined} from '@ant-design/icons-vue';
import {defineComponent, reactive, ref, toRefs} from 'vue';

const data = [
  {
    key: '1',
    name: '傻逼',
    Student_Number: 2206030000,
    Class_and_Grade: '2022级计算机应用技术E班',
    Class: '教学楼201',
    Time: '8:30-9:50'
  },
  {
    key: '2',
    name: '傻逼',
    Student_Number: 2206030000,
    Class_and_Grade: '2022级计算机应用技术E班',
    Class: '教学楼201',
    Time: '8:30-9:50'
  },
  {
    key: '3',
    name: '傻逼',
    Student_Number: 2206030000,
    Class_and_Grade: '2022级计算机应用技术E班',
    Class: '教学楼201',
    Time: '8:30-9:50'
  },
  {
    key: '4',
    name: '傻逼',
    Student_Number: 2206030000,
    Class_and_Grade: '2022级计算机应用技术E班',
    Class: '教学楼201',
    Time: '8:30-9:50'
  },
];
export default defineComponent({
  components: {
    SearchOutlined,
  },
  setup() {
    const state = reactive({
      searchText: '',
      searchedColumn: '',
    });
    const searchInput = ref();
    const columns = [
      {
        title: '姓名',
        dataIndex: 'name',
        key: 'name',
        slots: {
          filterDropdown: 'filterDropdown',
          filterIcon: 'filterIcon',
          customRender: 'customRender',
        },
        onFilter: (value, record) =>
            record.name.toString().toLowerCase().includes(value.toLowerCase()),
        onFilterDropdownVisibleChange: visible => {
          if (visible) {
            setTimeout(() => {
              console.log(searchInput.value);
              searchInput.value.focus();
            }, 100);
          }
        },
      },
      {
        title: '学号',
        dataIndex: 'Student_Number',
        key: 'Student_Number',
        slots: {
          filterDropdown: 'filterDropdown',
          filterIcon: 'filterIcon',
          customRender: 'customRender',
        },
        onFilter: (value, record) =>
            record.age.toString().toLowerCase().includes(value.toLowerCase()),
        onFilterDropdownVisibleChange: visible => {
          if (visible) {
            setTimeout(() => {
              searchInput.value.focus();
            }, 100);
          }
        },
      },
      {
        title: '班级',
        dataIndex: 'Class_and_Grade',
        key: 'Class_and_Grade',
        slots: {
          filterDropdown: 'filterDropdown',
          filterIcon: 'filterIcon',
          customRender: 'customRender',
        },
        onFilter: (value, record) =>
            record.address.toString().toLowerCase().includes(value.toLowerCase()),
        onFilterDropdownVisibleChange: visible => {
          if (visible) {
            setTimeout(() => {
              searchInput.value.focus();
            }, 100);
          }
        },
      },
      {
        title: '预约教室',
        dataIndex: 'Class',
        key: 'Class',
        slots: {
          filterDropdown: 'filterDropdown',
          filterIcon: 'filterIcon',
          customRender: 'customRender',
        },
        onFilter: (value, record) =>
            record.address.toString().toLowerCase().includes(value.toLowerCase()),
        onFilterDropdownVisibleChange: visible => {
          if (visible) {
            setTimeout(() => {
              searchInput.value.focus();
            }, 100);
          }
        },
      },
      {
        title: '时间段',
        dataIndex: 'Time',
        key: 'Time',
        slots: {
          filterDropdown: 'filterDropdown',
          filterIcon: 'filterIcon',
          customRender: 'customRender',
        },
        onFilter: (value, record) =>
            record.address.toString().toLowerCase().includes(value.toLowerCase()),
        onFilterDropdownVisibleChange: visible => {
          if (visible) {
            setTimeout(() => {
              searchInput.value.focus();
            }, 100);
          }
        },
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
    const handleSearch = (selectedKeys, confirm, dataIndex) => {
      confirm();
      state.searchText = selectedKeys[0];
      state.searchedColumn = dataIndex;
    };
    const handleReset = clearFilters => {
      clearFilters();
      state.searchText = '';
    };
    return {
      data,
      columns,
      handleSearch,
      handleReset,
      searchInput,
      ...toRefs(state),
    };
  },
});
</script>
<style scoped>
.highlight {
  background-color: rgb(255, 192, 105);
  padding: 0px;
}
</style>