package com.yt;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yt.model.user.Patient;

/**
 * @author Zjd
 * @create 2021-08-23 20:12
 */
@FeignClient(value = "serviceUser")
@Repository
public interface PatientFeignClient {
    //根据id获取就诊人信息
    @GetMapping("/api/user/patient/inner/get/{id}")
    public Patient getPatientOrder(@PathVariable("id") Long id);

}
