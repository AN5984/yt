package com.yt.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import yt.model.order.OrderInfo;

/**
 * @author Zjd
 * @create 2021-08-23 19:59
 */
public interface OrderService extends IService<OrderInfo> {

    void patientTips();
    Long saveOrder(String scheduleId, Long patientId);
}

