import service from '../until/request'
export function getUser() {
    return service({
        method: 'get',
        url: '/admit/getAllUser',
    })
}   