import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '?actionId=1001&key=login',
    method: 'get'
  })
}

export function getInfo(token) {
  return request({
    url: '?actionId=1001&key=getInfo',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '?actionId=1001&key=logou',
    method: 'get'
  })
}
