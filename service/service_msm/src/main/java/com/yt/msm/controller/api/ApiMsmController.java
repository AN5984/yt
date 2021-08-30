package com.yt.msm.controller.api;

import com.yt.Result.Result;
import com.yt.msm.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zjd
 * @create 2021-08-22 20:17
 */
@Api(tags="短信验证模拟")
@RestController
@RequestMapping("/api/msm")
public class ApiMsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation("短信验证模拟发送")
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone){
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //如果从redis获取不到，
        /**
         * 由于没有云服务提供个人项目的短信服务，无法实现短信验证
         * 可以直接将上述六位验证码存入redis以模拟短信发送成功,
         * code = RandomUtil.getSixBitRandom();
         * 为了方便模拟，现抛开随机数，使用123456进行模拟随机数
         */
        code="123456";
        redisTemplate.opsForValue().set(phone,code);
        return Result.ok();


//         //调用service方法，通过整合短信服务进行发送
//         boolean isSend = msmService.send(phone,code);
//         //生成验证码放到redis里面，设置有效时间
//         if(isSend) {
//         redisTemplate.opsForValue().set(phone,code,2, TimeUnit.MINUTES);
//         return Result.ok();
//         } else {
//         return Result.fail().message("发送短信失败");
//         }
    }
}
