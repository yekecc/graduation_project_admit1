import axios from "axios";
// import useTokenStore from '../store/token'

const service = axios.create({
    baseURL: 'http://dahle.natapp1.cc/'
})
export default service