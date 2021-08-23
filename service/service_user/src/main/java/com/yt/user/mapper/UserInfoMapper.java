package com.yt.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import yt.model.user.UserInfo;

/**
 * @author Zjd
 * @create 2021-08-21 18:52
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
