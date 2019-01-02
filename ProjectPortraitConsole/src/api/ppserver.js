import request from '@/utils/request'

export function resourceFindStatisticsByCount(params) {
  return request({
    url: '?actionId=2002',
    method: 'get',
    params
  })
}
