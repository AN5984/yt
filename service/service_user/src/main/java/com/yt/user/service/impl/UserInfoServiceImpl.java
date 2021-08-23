package com.yt.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yt.Result.ResultCodeEnum;
import com.yt.Utils.JwtHelper;

import com.yt.exception.YtException;
import com.yt.user.mapper.UserInfoMapper;
import com.yt.user.service.PatientService;
import com.yt.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import yt.enums.AuthStatusEnum;
import yt.model.user.Patient;
import yt.model.user.UserInfo;
import yt.vo.user.LoginVo;
import yt.vo.user.UserAuthVo;
import yt.vo.user.UserInfoQueryVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zjd
 * @create 2021-08-21 18:52
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private PatientService patientService;

    //用户手机号登录
    @Override
    public Map<String, Object> loginUser(LoginVo loginVo) {
        //从loginVo获取输入的手机号和验证码，并判断是否为空
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)){
            throw new YtException(ResultCodeEnum.PARAM_ERROR);
        }
        //判断手机验证码和输入验证码是否一致
        String redisCode = redisTemplate.opsForValue().get(phone);
        if(!code.equals(redisCode)){
            throw new YtException(ResultCodeEnum.CODE_ERROR);
        }
//        //绑定手机号码
        UserInfo userInfo = null;
//        if(!StringUtils.isEmpty(loginVo.getOpenid())) {
//            userInfo = this.selectWxInfoOpenId(loginVo.getOpenid());
//            if(null != userInfo) {
//                userInfo.setPhone(loginVo.getPhone());
//                this.updateById(userInfo);
//            } else {
//                throw new YtException(ResultCodeEnum.DATA_ERROR);
//            }
//        }

        //如果userInfo为空，进行正常手机登录
        if(userInfo == null){
            //判断是否是第一次登录：根据手机号查询数据库，如果不存在相同的，则表示第一次登录，进行注册
            QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("phone",phone);
            userInfo = baseMapper.selectOne(wrapper);
            if(userInfo == null){
                //添加到数据库
                userInfo = new UserInfo();
                userInfo.setName("");
                userInfo.setPhone(phone);
                userInfo.setStatus(1);
                baseMapper.insert(userInfo);
            }
        }
        //不是第一次登录，直接进行登录
        //校验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new YtException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }
        //返回登录用户名
        Map<String, Object> map = new HashMap<>();
        String name = userInfo.getName();
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getNickName();
        }
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getPhone();
        }
        map.put("name", name);
        //返回token信息
        //jwt生成token字符串
        String token = JwtHelper.createToken(userInfo.getId(), name);
        map.put("token", token);
        return map;
    }

    //用户认证接口
    @Override
    public void userAuth(Long userId, UserAuthVo userAuthVo) {
        //1.根据用户id查询用户信息
        UserInfo userInfo = baseMapper.selectById(userId);
        //2.设置认证信息
        //认证人姓名
        userInfo.setName(userAuthVo.getName());
        //其他认证信息
        userInfo.setCertificatesType(userAuthVo.getCertificatesType());
        userInfo.setCertificatesNo(userAuthVo.getCertificatesNo());
        userInfo.setCertificatesUrl(userAuthVo.getCertificatesUrl());
        userInfo.setAuthStatus(AuthStatusEnum.AUTH_RUN.getStatus());
        //3.进行信息更新
        baseMapper.updateById(userInfo);
    }

    //用户列表（条件查询带分页）
    @Override
    public IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo) {
        //UserInfoQueryVo获取条件值
        String name = userInfoQueryVo.getKeyword();  //用户名称
        Integer status = userInfoQueryVo.getStatus();  //用户状态
        Integer authStatus = userInfoQueryVo.getAuthStatus();  //认证状态
        String createTimeBegin = userInfoQueryVo.getCreateTimeBegin();  //开始时间
        String createTimeEnd = userInfoQueryVo.getCreateTimeEnd();  //结束时间
        //对条件值进行非空判断
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }
        if(!StringUtils.isEmpty(authStatus)) {
            wrapper.eq("auth_status",authStatus);
        }
        if(!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time",createTimeBegin);
        }
        if(!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le("create_time",createTimeEnd);
        }
        Page<UserInfo> pages = baseMapper.selectPage(pageParam, wrapper);
        //把编号(认证状态和用户状态)变成对应的值,封装进UserInfo
        pages.getRecords().stream().forEach(item -> {
            this.packageUserInfo(item);
        });
        return pages;
    }

    //用户锁定功能
    @Override
    public void lock(long userId, Integer status) {
        if(status.intValue() == 0 || status.intValue() == 1){
            UserInfo userInfo = baseMapper.selectById(userId);
            userInfo.setStatus(status);
            baseMapper.updateById(userInfo);
        }
    }

    //用户详情功能
    @Override
    public Map<String, Object> show(long userId) {
        Map<String, Object> map = new HashMap<>();
        //根据userid查询用户信息
        UserInfo userInfo = baseMapper.selectById(userId);
        UserInfo userInfo1 = this.packageUserInfo(userInfo);
        map.put("userInfo",userInfo1);
        //根据userid查询就诊人信息
        List<Patient> patientList = patientService.findAllUserId(userId);
        map.put("patientList",patientList);
        return map;
    }

    //认证审批接口
    @Override
    public void approval(long userId, Integer authStatus) {
        if(authStatus.intValue() == 2 || authStatus.intValue() == -1){ //2代表审核通过，-1代表审核不通过
            UserInfo userInfo = baseMapper.selectById(userId);
            userInfo.setAuthStatus(authStatus);
            baseMapper.updateById(userInfo);
        }
    }

    //把编号变成对应的值
    private UserInfo packageUserInfo(UserInfo userInfo) {
        //处理认证状态
        userInfo.getParam().put("authStatusString",AuthStatusEnum.getStatusNameByStatus(userInfo.getAuthStatus()));
        //处理用户状态
        String statusString = userInfo.getStatus().intValue() == 0 ? "锁定" : "正常";
        userInfo.getParam().put("statusString",statusString);
        return userInfo;
    }
}
