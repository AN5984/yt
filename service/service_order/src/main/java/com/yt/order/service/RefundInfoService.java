package com.yt.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yt.model.order.PaymentInfo;
import yt.model.order.RefundInfo;

/**
 * @author Zjd
 * @create 2021-08-26 22:17
 */
public interface RefundInfoService extends IService<RefundInfo> {
    //保存退款记录
    RefundInfo saveRefundInfo(PaymentInfo paymentInfo);
}

