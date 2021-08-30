package com.yt.order.controller;

import com.yt.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yt.vo.order.OrderCountQueryVo;

import java.util.Map;

/**
 * @author Zjd
 * @create 2021-08-28 17:56
 */
@Api(tags = "订单显示接口")
@RestController
@RequestMapping("/admin/order/orderInfo")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "获取订单统计数据")
    @PostMapping("inner/getCountMap")
    public Map<String, Object> getCountMap(@RequestBody OrderCountQueryVo orderCountQueryVo) {
        return orderService.getCountMap(orderCountQueryVo);
    }
}
