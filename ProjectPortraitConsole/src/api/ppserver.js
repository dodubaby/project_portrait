import request from '@/utils/request'
// file
export function fileFindBySuffixOrderByLineCount(params) {
  return request({
    url: '?actionId=1002&suffix=java&maxLineCount=500',
    method: 'get'
  })
}
export function fileFindAll(params) {
  return request({
    url: '?actionId=1001',
    method: 'get'
  })
}
// resource
export function resourceFindStatisticsByCount(params) {
  return request({
    url: '?actionId=2002',
    method: 'get'
  })
}
// reference
export function referenceFindAll(params) {
  return request({
    url: '?actionId=3001',
    method: 'get'
  })
}
// targetData
