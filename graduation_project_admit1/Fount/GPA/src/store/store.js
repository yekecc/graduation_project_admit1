import {ref} from "vue";
import {defineStore} from 'pinia'

const AdmitData = defineStore('user', () => {
    const data = ref({
        office: '',
        password: '',
        telephone: '',
        userClass: '',
        userID: 0,
        userNumber: '',
        username: ''

    })


    const updata = (val) => {
        data.value = val
    }

    return {
        data,
        updata
    }

}, {persist: true})


export default AdmitData