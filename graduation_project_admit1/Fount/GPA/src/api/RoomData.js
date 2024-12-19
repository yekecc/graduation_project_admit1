import service from '../until/request'

export function getRoom() {
    return service({
        method: 'get',
        url: '/admit/getAllRoom',
    })
}
export function addroom(){
    return service({
        method: 'post',
        url: '/admit/addRoom',
    })
}
export function updateRoom(data) {
    return service({
        method: 'post',
        url: '/room',
        data
    })
}

export function getReservations() {
    return service({
        method: 'get',
        url: '/admit/getAllReservation',
    })
}
