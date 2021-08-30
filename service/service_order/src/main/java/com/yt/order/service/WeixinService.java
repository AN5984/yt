package com.yt.order.service;

import java.util.Map;

/**
 * @author Zjd
 * @create 2021-08-26 22:15
 */
public interface WeixinService {
    //生成微信扫描支付二维码
    Map<String, Object> createNative(Long orderId);

    //调用微信接口实现支付状态查询
    Map<String, String> queryPayStatus(Long orderId);

    //微信退款方法
    Boolean refund(Long orderId);
}
