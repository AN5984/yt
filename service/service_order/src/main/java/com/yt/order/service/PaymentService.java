package com.yt.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yt.model.order.OrderInfo;
import yt.model.order.PaymentInfo;

import java.util.Map;

/**
 * @author Zjd
 * @create 2021-08-26 22:14
 */
public interface PaymentService extends IService<PaymentInfo> {
    //向支付记录表里面添加信息
    void savePaymentInfo(OrderInfo order, Integer paymentType);

    //更改订单状态
    void paySuccess(String out_trade_no, Integer status, Map<String, String> resultMap);

    //获取支付记录
    PaymentInfo getPaymentInfo(Long orderId, Integer paymentType);
}