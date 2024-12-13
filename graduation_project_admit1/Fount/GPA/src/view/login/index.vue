<script setup>
import { message } from 'ant-design-vue';
import { onMounted, ref } from 'vue';
import { login } from '../../api/login'
import AdmitData from '../../store/store';
import router from '../../router/router';
// const inputValue = ref({ account: '', password: '' });
const isFocused = ref({ account: false, password: false });

const { updata } = AdmitData()

const formData = ref({
  userNumber: '',
  password: ''
})


const handlogin = async () => {
  try {
    const response = await login(formData.value);
    console.log(response.data);

    if (response.data.code == 200) {
      updata(response.data.data);
      localStorage.setItem('token', 'true');
      router.push('/StatisticsReport');
      message.success('登录成功');
    } else {
      message.error('账号或密码错误');
    }
  } catch (error) {
    console.error('登录失败:', error);
    message.error('登录失败，请稍后重试');
  }
}



</script>

<template>
  <div class="login-container">
    <div class="login_box">
      <h2>管理员登录</h2>
      <form action="" @submit.prevent="handlogin">
        <div class="adm_box">
          <input id="" v-model="formData.userNumber" name="" type="text" @blur="isFocused.account = false"
            @focus="isFocused.account = true">
          <label :class="{ hidden: isFocused.account || formData.userNumber }" for="">账号</label>
        </div>
        <div class="adm_box">
          <input id="" v-model="formData.password" name="" type="password" @blur="isFocused.password = false"
            @focus="isFocused.password = true">
          <label :class="{ hidden: isFocused.password || formData.password }" for="">密码</label>
        </div>
        <!-- <div class="remember">
          <label for="">
            <input type="checkbox" name="" id="">记住密码
          </label>
        </div> -->
        <button type="submit">登录</button>
      </form>
    </div>
  </div>
</template>

<style scoped>
body {
  margin: 0;
  padding: 0;
  background-color: #b9c8da;
}

.hidden {
  display: none;
}

.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f0f2f5;
}

.login_box {
  width: 400px;
  background-color: #fff;
  padding: 40px;
  border-radius: 15px;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.15);
  margin: 100px auto;
  transition: transform 0.3s ease;
}

.login_box:hover {
  transform: translateY(-5px);
}

.login_box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #2c3e50;
  text-align: center;
  font-size: 28px;
  font-weight: 600;
}

.login_box .adm_box {
  position: relative;
  margin-bottom: 30px;
}

.login_box .adm_box input {
  width: 100%;
  padding: 12px 0;
  font-size: 16px;
  color: #2c3e50;
  border: none;
  border-bottom: 2px solid #e0e0e0;
  outline: none;
  background: transparent;
  transition: border-color 0.3s ease;
}

.login_box .adm_box input:focus {
  border-color: #e2ab75;
}

.login_box .adm_box label {
  position: relative;
  top: 0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #999;
  pointer-events: none;
  transition: 0.5s;
}

.login_box .adm_box input:focus~label,
.login_box .adm_box input:valid~label {
  top: -20px;
  left: 0;
  color: #777;
  font-size: 12px;
}

.login_box button {
  display: block;
  width: 100%;
  background-color: #e2ab75;
  color: #fff;
  border: none;
  outline: none;
  margin: 30px auto 0;
  padding: 15px;
  font-size: 18px;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
  font-weight: 500;
  letter-spacing: 0.5px;
}

.login_box button:hover {
  background-color: #d69555;
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(226, 171, 117, 0.4);
}

.login_box .adm_box label {
  color: #666;
  font-weight: 500;
}

.login_box .adm_box input:focus~label {
  color: #e2ab75;
}
</style>