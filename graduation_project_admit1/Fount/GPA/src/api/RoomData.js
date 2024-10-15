import service from '../until/request'
export function RoomDto(data) {
    return service({
        method: 'post',
        url: '/room',
        data
    })
}
export function getRoom() {
    return service({
        method: 'get',
        url: '/room',
    })
}