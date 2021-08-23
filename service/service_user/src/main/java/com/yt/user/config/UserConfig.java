package com.yt.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zjd
 * @create 2021-08-21 18:53
 */
@Configuration
@MapperScan("com.yt.user.mapper")
public class UserConfig {
}
