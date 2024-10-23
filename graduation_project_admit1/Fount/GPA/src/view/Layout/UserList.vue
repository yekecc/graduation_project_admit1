<template>
    <a-table :data-source="userData" :columns="columns">
        <template #filterDropdown="{ setSelectedKeys, selectedKeys, confirm, clearFilters, column }">
            <div style="padding: 8px">
                <a-input ref="searchInput" :placeholder="`Search ${column.dataIndex}`" :value="selectedKeys[0]"
                    style="width: 188px; margin-bottom: 8px; display: block"
                    @change="e => setSelectedKeys(e.target.value ? [e.target.value] : [])"
                    @pressEnter="handleSearch(selectedKeys, confirm, column.dataIndex)" />
                <a-button type="primary" size="small" style="width: 90px; margin-right: 8px"
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
<script setup>
import { SearchOutlined } from '@ant-design/icons-vue';
import { reactive, ref, onMounted } from 'vue';
import { getUser } from '../../api/User';

const state = reactive({
    searchText: '',
    searchedColumn: '',
})

const searchInput = ref(null)

const userData = ref([])

onMounted(async () => {
    try {
        const res = await getUser()
        userData.value = res.data.data
        console.log(userData.value)
    } catch (error) {
        console.error('获取用户数据失败:', error)
    }
})

const data = [
    {
        key: '1',
        username: '傻逼',
        userNumber: 2206030000,
        userClass: '2022级计算机应用技术E班',
    }
];

const columns = [
    {
        title: '姓名',
        dataIndex: 'username',
        key: 'username',
        slots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
        },
        onFilter: (value, record) =>
            record.username.toString().toLowerCase().includes(value.toLowerCase()),
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
        dataIndex: 'userNumber',
        key: 'userNumber',
        slots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
        },
        onFilter: (value, record) =>
            record.userNumber.toString().toLowerCase().includes(value.toLowerCase()),
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
        dataIndex: 'userClass',
        key: 'userClass',
        slots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
        },
        onFilter: (value, record) =>
            record.userClass.toString().toLowerCase().includes(value.toLowerCase()),
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
</script>
<style scoped>
.highlight {
    background-color: rgb(255, 192, 105);
    padding: 0px;
}
</style>