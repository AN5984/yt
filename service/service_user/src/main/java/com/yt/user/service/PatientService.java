package com.yt.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yt.model.user.Patient;


import java.util.List;

/**
 * @author Zjd
 * @create 2021-08-23 16:01
 */
public interface PatientService extends IService<Patient> {
    //根据登录用户id获取就诊列表
    List<Patient> findAllUserId(Long userId);

    //根据就诊人id获取就诊人信息
    Patient getPatientById(Long id);
}
