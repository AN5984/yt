package com.yt.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import yt.model.order.PaymentInfo;

/**
 * @author Zjd
 * @create 2021-08-26 22:13
 */
@Mapper
public interface PaymentMapper extends BaseMapper<PaymentInfo> {
}
