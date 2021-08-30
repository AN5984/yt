package com.yt.msm.service;

import yt.vo.msm.MsmVo;

/**
 * @author Zjd
 * @create 2021-08-22 20:17
 */
public interface MsmService {
    //通过整合短信服务进行发送
    boolean send(String phone, String code);

    //使用mq发送短信
    boolean send(MsmVo msmVo);
}
