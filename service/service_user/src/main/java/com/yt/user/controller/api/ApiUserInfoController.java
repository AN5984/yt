package com.yt.user.controller.api;

import com.yt.Result.Result;
import com.yt.Utils.AuthContextHolder;
import com.yt.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yt.model.user.UserInfo;
import yt.vo.user.LoginVo;
import yt.vo.user.UserAuthVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Zjd
 * @create 2021-08-21 18:53
 */
@Api(tags="用户登录")
@RestController
@RequestMapping("/api/user")
public class ApiUserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @ApiOperation("用户手机号登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){
        Map<String,Object> map = userInfoService.loginUser(loginVo);
        return Result.ok(map);
    }
    @ApiOperation("用户认证接口")
    @PostMapping("auth/userAuth")
    public Result userAuth(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request){
        //传递两个参数 1、用户id  2、认证数据的vo对象
        userInfoService.userAuth(AuthContextHolder.getUserId(request),userAuthVo);
        return Result.ok();
    }

    @ApiOperation("获取用户id信息接口")
    @GetMapping("auth/getUserInfo")
    public Result getUserInfo(HttpServletRequest request){
        Long userId = AuthContextHolder.getUserId(request);
        UserInfo userInfo = userInfoService.getById(userId);
        return Result.ok(userInfo);
    }
}
