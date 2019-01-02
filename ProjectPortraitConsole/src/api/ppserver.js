import request from '@/utils/request'
// file
export function fileFindBySuffixOrderByLineCount(params) {
  return request({
    url: '?actionId=1002&suffix=java&maxLineCount=500',
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
// targetData
