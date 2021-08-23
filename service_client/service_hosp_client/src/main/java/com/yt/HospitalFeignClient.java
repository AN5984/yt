package com.yt;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yt.vo.hosp.ScheduleOrderVo;
import yt.vo.order.SignInfoVo;

/**
 * @author Zjd
 * @create 2021-08-23 20:19
 */
@FeignClient(value = "serviceHosp")
@Repository
public interface HospitalFeignClient {
    //根据排班id获取预约下单数据
    @GetMapping("/api/hosp/hospital/inner/getScheduleOrderVo/{scheduleId}")
    public ScheduleOrderVo getScheduleOrderVo(@PathVariable("scheduleId") String scheduleId);

    //获取医院签名信息
    @GetMapping("/api/hosp/hospital/inner/getSignInfoVo/{hoscode}")
    public SignInfoVo getSignInfoVo(@PathVariable("hoscode") String hoscode);
}
