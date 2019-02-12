import request from "@/utils/request";
// file
export function fileFindBySuffixOrderByLineCount(params) {
  return request({
    url: '?actionId=1002&suffix=java&maxLineCount=500',
    method: 'get'
  })
}
export function fileFindAll(rootKey) {
  return request({
    url: '?actionId=1001&rootKey=' + rootKey,
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
export function referenceFindAll(key) {
  return request({
    url: '?actionId=3001&key=' + key,
    method: 'get'
  })
}

// rule、ruleData
export function ruleDataFindAll(ruleType, ruleDataStatus) {
  return request({
    url: '?actionId=4001&ruleType=' + ruleType + '&ruleDataStatus=' + ruleDataStatus,
    method: 'get'
  })
}

// tag、tagData
export function tagFindByType(type) {
  return request({
    url: '?actionId=5001&type=' + type,
    method: 'get'
  })
}
export function tagDataFindByDataId(dataType, dataId) {
  return request({
    url: '?actionId=5002&dataType=' + dataType + '&dataId=' + dataId,
    method: 'get'
  })
}
export function tagDataUpdateTags(dataType, dataId, tags) {
  return request({
    url: '?actionId=5003&dataType=' + dataType + '&dataId=' + dataId + '&tags=' + tags,
    method: 'get'
  })
}
