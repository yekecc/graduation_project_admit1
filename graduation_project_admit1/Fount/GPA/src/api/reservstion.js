import service from '../until/request'

export function delreservation(id) {
    return service({
        method: 'delete',
        url: `/admit/delreservation?reservationId=${id}`,
    })
}