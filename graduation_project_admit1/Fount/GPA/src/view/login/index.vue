<script setup>
import {message} from 'ant-design-vue';
import {ref} from 'vue';
import {login} from '../../api/login'
import AdmitData from '../../store/store';
import router from '../../router/router';
// const inputValue = ref({ account: '', password: '' });
const isFocused = ref({account: false, password: false});

const {updata} = AdmitData()

const formData = ref({
  userNumber: '',
  password: ''
})


const handlogin = async () => {
  const response = await login(formData.value);
  console.log(response.data)

  updata(response.data)


  if (response.data != null) {
    console.log("okk")
    router.push({
      name: 'Layout'
    })
  } else {
    message.value = '账号或密码错误'
  }
  console.log(1111111111111111111111)
}
</script>

<template>
  <div class="login_box">
    <h2>管理员登录</h2>
    <form action="" @submit.prevent="handlogin">
      <div class="adm_box">
        <input
            id=""
            v-model="formData.userNumber"
            name=""

            type="text"
            @blur="isFocused.account = false"
            @focus="isFocused.account = true"
        >
        <label :class="{ hidden: isFocused.account || formData.userNumber }" for="">账号</label>
      </div>
      <div class="adm_box">
        <input
            id=""
            v-model="formData.password"
            name=""
            type="password"
            @blur="isFocused.password = false"
            @focus="isFocused.password = true"
        >
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
</template>

<style scoped>
body {
  margin: 0;
  padding: 0;
  background-color: #8ca5c0;
}

.hidden {
  display: none;
}

.login_box {
  width: 400px;
  background-color: #fff;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  margin: 100px auto;
}

.login_box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #333;
  text-align: center;
}

.login_box .adm_box {
  position: relative;
  margin-bottom: 30px;
}

.login_box .adm_box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #333;
  border: none;
  border-bottom: 1px solid #999;
  outline: none;
  background: transparent;
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

.login_box .adm_box input:focus ~ label,
.login_box .adm_box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #777;
  font-size: 12px;
}

.login_box button {
  display: block;
  width: 80%;
  background-color: #e2ab75;
  color: #fff;
  border: none;
  outline: none;
  margin: auto;
  margin-top: 20px;
  padding: 15px;
  font-size: 18px;
  border-radius: 5px;
  cursor: pointer;
  transition: 0.5s;
}


</style>