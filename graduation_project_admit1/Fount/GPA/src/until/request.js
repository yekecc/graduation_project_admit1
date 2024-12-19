import axios from "axios";
// import useTokenStore from '../store/token'

const service = axios.create({
    baseURL: 'http://47.99.60.90:8080'
})
export default service