package com.yt.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yt.model.hosp.Hospital;
import yt.model.hosp.HospitalSet;
import yt.vo.order.SignInfoVo;

/**
 * @author Zjd
 * @create 2021-08-04 23:41
 */
public interface HospSetService extends IService<HospitalSet> {

    String getSignKey(String hoscode);

    SignInfoVo getSignInfoVo(String hoscode);
    //实现根据医院编号查询
}
