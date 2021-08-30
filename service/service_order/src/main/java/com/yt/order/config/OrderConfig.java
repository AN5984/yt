package com.yt.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zjd
 * @create 2021-08-24 15:42
 */
@Configuration
@MapperScan("com.yt.order.mapper")
public class OrderConfig {
}
