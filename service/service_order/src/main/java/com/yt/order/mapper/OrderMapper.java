package com.yt.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yt.model.order.OrderInfo;
import yt.vo.order.OrderCountQueryVo;
import yt.vo.order.OrderCountVo;

import java.util.List;

/**
 * @author Zjd
 * @create 2021-08-23 20:00
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderInfo>{
    //查询预约统计数据的方法
    List<OrderCountVo> selectOrderCount(@Param("vo") OrderCountQueryVo orderCountQueryVo);  //vo 别名
}
