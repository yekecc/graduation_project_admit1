import service from '../until/request'
export function login(data) {
    return service({
        method: 'post',
        url: '/admit/login',
        data
    })

}
