import service from '../until/request';

/**
 * 获取所有用户信息
 *
 * @return 用户列表
 */
export function getAllUsers() {
    return service({
        method: 'get',
        url: '/admit/getAllUser',
    });
}
export function delUser() {
    return service({
        method: 'delete',
        url: '/admit/delUser',
    });
} 
