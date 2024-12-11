<template>
    <div>
        <h2>统计报表</h2>
        <div class="card-container">
            <!-- 用户数量卡片 -->
            <el-card class="card card-users" shadow="hover">
                <div class="card-content">
                    <el-icon>
                        <User />
                    </el-icon>
                    <h3>用户数量</h3>
                    <p class="amount">{{ userCount }}</p>
                </div>
            </el-card>

            <!-- 房间数量卡片 -->
            <el-card class="card card-rooms" shadow="hover">
                <div class="card-content">
                    <el-icon>
                        <House />
                    </el-icon>
                    <h3>房间数量</h3>
                    <p class="amount">{{ roomCount }}</p>
                </div>
            </el-card>

            <!-- 预约数量卡片 -->
            <el-card class="card card-reservations" shadow="hover">
                <div class="card-content">
                    <el-icon>
                        <Calendar />
                    </el-icon>
                    <h3>预约数量</h3>
                    <p class="amount">{{ reservationCount }}</p>
                </div>
            </el-card>
        </div>
        <div ref="lineChart" class="chart-container" style="height: 400px;"></div>
    </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue';
import * as echarts from 'echarts';
import { getAllUsers } from '../../api/UserData'; // 获取用户数据
import { getRoom } from '../../api/RoomData'; // 获取房间数据
import { getReservations } from '../../api/RoomData'; // 获取预约数据
import { ElCard, ElIcon } from 'element-plus';
import { User, House, Calendar } from '@element-plus/icons-vue';

const roomCount = ref(0);
const userCount = ref(0);
const reservationCount = ref(0);
const chartData = ref([]);

// 整合统计数据
const fetchStatisticsData = async () => {
    try {
        const [usersResponse, roomsResponse, reservationsResponse] = await Promise.all([
            getAllUsers(), // 获取用户数据
            getRoom(), // 获取房间数据
            getReservations() // 获取预约数据
        ]);

        if (usersResponse.data.code === 200) {
            userCount.value = usersResponse.data.data.length; // 假设返回的数据是用户数组
        } else {
            console.error('获取用户数据失败:', usersResponse.message);
        }

        if (roomsResponse.data.code === 200) {
            roomCount.value = roomsResponse.data.data.length; // 假设返回的数据是房间数组
        } else {
            console.error('获取房间数据失败:', roomsResponse.message);
        }

        if (reservationsResponse.data.code === 200) {
            reservationCount.value = reservationsResponse.data.data.length; // 预约数量
            processReservationData(reservationsResponse.data.data); // 处理预约数据
        } else {
            console.error('获取预约数据失败:', reservationsResponse.message);
        }
    } catch (error) {
        console.error('请求失败:', error);
    }
};

// 处理预约数据以生成折线图数据
const processReservationData = (reservations) => {
    const reservationCountByDate = {};

    reservations.forEach(reservation => {
        const date = reservation.reservationDate; // 获取预约日期
        if (!reservationCountByDate[date]) {
            reservationCountByDate[date] = 0;
        }
        reservationCountByDate[date] += 1; // 统计每个日期的预约数量
    });

    chartData.value = Object.entries(reservationCountByDate).map(([date, count]) => ({
        date,
        count
    }));

    nextTick(() => {
        drawLineChart(); // 确保在 DOM 更新后绘制折线图
    });
};

// 绘制折线图
const drawLineChart = () => {
    const lineChart = echarts.init(document.querySelector('.chart-container')); // 使用 ref 获取 DOM 元素
    const option = {
        title: {
            text: '每日预约数量',
        },
        tooltip: {
            trigger: 'axis',
        },
        xAxis: {
            type: 'category',
            data: chartData.value.map(item => item.date), // 日期
        },
        yAxis: {
            type: 'value',
        },
        series: [
            {
                name: '预约数量',
                type: 'line',
                data: chartData.value.map(item => item.count), // 预约数量
            },
        ],
    };
    lineChart.setOption(option);
};

onMounted(() => {
    fetchStatisticsData(); // 组件挂载时获取统计数据
});
</script>

<style scoped>
.card-container {
    display: flex;
    justify-content: space-around;
    margin-bottom: 20px;
}

.card {
    border-radius: 12px;
    padding: 20px;
    text-align: center;
    width: 30%;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s;
    position: relative;
}

.card:hover {
    transform: translateY(-5px);
}

/* 用户数量卡片 */
.card-users {
    background: linear-gradient(135deg, #64b5f6, #bbdefb);
}

/* 房间数量卡片 */
.card-rooms {
    background: linear-gradient(135deg, #80cbc4, #e0f2f1);
}

/* 预约数量卡片 */
.card-reservations {
    background: linear-gradient(135deg, #7c4dff, #d1c4e9);
}

.card i {
    font-size: 3em;
    color: white;
    margin-bottom: 10px;
}

.card-content {
    color: white;
}

.amount {
    font-size: 2em;
    margin: 10px 0;
}

h2 {
    color: #4a90e2;
    text-align: center;
    margin-bottom: 20px;
}

h3 {
    color: white;
    margin: 10px 0;
}

.chart-container {
    width: 100%;
}
</style>