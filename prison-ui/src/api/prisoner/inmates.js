import request from '@/utils/request'

// 查询囚犯信息列表
export function listInmates(query) {
  return request({
    url: '/prisoner/inmates/list',
    method: 'get',
    params: query
  })
}

// 查询囚犯信息详细
export function getInmates(inmateId) {
  return request({
    url: '/prisoner/inmates/' + inmateId,
    method: 'get'
  })
}

// 新增囚犯信息
export function addInmates(data) {
  return request({
    url: '/prisoner/inmates',
    method: 'post',
    data: data
  })
}

// 修改囚犯信息
export function updateInmates(data) {
  return request({
    url: '/prisoner/inmates',
    method: 'put',
    data: data
  })
}

// 删除囚犯信息
export function delInmates(inmateId) {
  return request({
    url: '/prisoner/inmates/' + inmateId,
    method: 'delete'
  })
}
