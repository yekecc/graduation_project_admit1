import service from '../until/request'

export function getRoom() {
    return service({
        method: 'get',
        url: '/room',
    })
}

export function updateRoom(data) {
    return service({
        method: 'post',
        url: '/room',
        data
    })
}
