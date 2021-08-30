import request from '@/utils/request'

export default{
    //医院设置列表
    getHospSetlist(current,limit,searchObj){
        return request({
            url: `/admin/hosp/hospSet/findPage/${current}/${limit}`,
            method: 'Post',
            data:searchObj  //使用json
          })
    },
    //删除医院设置
    deleteHospSet(id){
        return request({
            url: `/admin/hosp/hospSet/delByID/${id}`,
            method: 'delete'
          })
    },
    //批量删除
    batchRemoveHospSet(idList){
        return request({
            url: `/admin/hosp/hospSet/delByIDs`,
            method: 'delete',
            data:idList
          })
    },
    //锁定和取消锁定（是否可用）
    lockHospSet(id,status){
        return request({
            url: `/admin/hosp/hospSet/lockHospSet/${id}/${status}`,
            method: 'put'
          })
    },
    //添加医院设置
    saveHospSet(hospitalSet){
        return request({
            url: `/admin/hosp/hospSet/addHosp`,
            method: 'post',
            data:hospitalSet
          })
    },
    //医院设置id查询功能
    getHospSet(id){
        return request({
            url: `/admin/hosp/hospSet/findbyID/${id}`,
            method: 'get'
          })
    },
    //修改医院设置
    updateHospSet(hospitalSet){
        return request({
            url: `/admin/hosp/hospSet/update`,
            method: 'post',
            data:hospitalSet
          })
    },
}