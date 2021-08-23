package com.yt.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import yt.model.user.UserInfo;
import yt.vo.user.LoginVo;
import yt.vo.user.UserAuthVo;
import yt.vo.user.UserInfoQueryVo;


import java.util.Map;

/**
 * @author Zjd
 * @create 2021-08-21 18:51
 */

public interface UserInfoService extends IService<UserInfo> {

    //用户手机号登录
    Map<String, Object> loginUser(LoginVo loginVo);



    //用户认证接口
    void userAuth(Long userId, UserAuthVo userAuthVo);

    //用户列表（条件查询带分页）
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);

    //用户锁定功能
    void lock(long userId, Integer status);

    //用户详情功能
    Map<String, Object> show(long userId);

    //认证审批接口
    void approval(long userId, Integer authStatus);
}

