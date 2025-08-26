import request from '@/utils/request'

// 查询囚犯照片列表
export function listPhotos(query) {
  return request({
    url: '/prisoner/photos/list',
    method: 'get',
    params: query
  })
}

// 查询囚犯照片详细
export function getPhotos(firstName) {
  return request({
    url: '/prisoner/photos/' + firstName,
    method: 'get'
  })
}

// 新增囚犯照片
export function addPhotos(data) {
  return request({
    url: '/prisoner/photos',
    method: 'post',
    data: data
  })
}

// 修改囚犯照片
export function updatePhotos(data) {
  return request({
    url: '/prisoner/photos',
    method: 'put',
    data: data
  })
}

// 删除囚犯照片
export function delPhotos(firstName) {
  return request({
    url: '/prisoner/photos/' + firstName,
    method: 'delete'
  })
}
