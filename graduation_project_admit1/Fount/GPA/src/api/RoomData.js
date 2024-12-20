import service from '../until/request'

export function getRoom() {
    return service({
        method: 'get',
        url: '/admit/getAllRoom',
    })
}
export function addroom(data) {
    return service({
        method: 'post',
        url: '/admit/addRoom',
        data
    })
}
export function delroom(id) {
    return service({
        method: 'delete',
        url: `/admit/delRoom?roomID=${id}`,
    })
}

export function getReservations() {
    return service({
        method: 'get',
        url: '/admit/getAllReservation',
    })
}
