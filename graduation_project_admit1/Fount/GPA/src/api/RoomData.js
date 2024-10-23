import service from '../until/request'
// export function RoomDto(data) {
//     return service({
//         method: 'post',
//         url: '/room',
//         data
//     })
// }
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

export function deleteRoom(data) {
    return service({
        method: 'delete',
        url: `/room?roomId=${data}`,

    })
}
