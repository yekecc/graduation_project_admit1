import {ref} from "vue";
import {defineStore} from 'pinia'

const ClassData = defineStore('room', () => {
    const data = ref([])

    const updata = (val) => {
        data.value = val
    }

    return {
        data,
        updata
    }
}, {persist: true})

export default ClassData