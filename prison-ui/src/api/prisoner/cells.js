import request from '@/utils/request'

// 查询牢房信息列表
export function listCells(query) {
  return request({
    url: '/prisoner/cells/list',
    method: 'get',
    params: query
  })
}

// 查询牢房信息详细
export function getCells(cellId) {
  return request({
    url: '/prisoner/cells/' + cellId,
    method: 'get'
  })
}

// 新增牢房信息
export function addCells(data) {
  return request({
    url: '/prisoner/cells',
    method: 'post',
    data: data
  })
}

// 修改牢房信息
export function updateCells(data) {
  return request({
    url: '/prisoner/cells',
    method: 'put',
    data: data
  })
}

// 删除牢房信息
export function delCells(cellId) {
  return request({
    url: '/prisoner/cells/' + cellId,
    method: 'delete'
  })
}
