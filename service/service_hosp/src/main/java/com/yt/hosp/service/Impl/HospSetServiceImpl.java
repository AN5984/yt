package com.yt.hosp.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yt.Result.ResultCodeEnum;
import com.yt.exception.YtException;
import com.yt.hosp.mapper.HospSetMapper;
import com.yt.hosp.repository.HospitalRepository;
import com.yt.hosp.service.HospSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yt.model.hosp.HospitalSet;
import yt.vo.order.SignInfoVo;

/**
 * @author Zjd
 * @create 2021-08-04 23:42
 */
@Service
public class HospSetServiceImpl extends ServiceImpl<HospSetMapper, HospitalSet> implements HospSetService {

    @Autowired
    private HospitalRepository hospitalRepository;

    //根据传递过来的医院编码，查询数据库，查询签名
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(queryWrapper);

        return hospitalSet.getSignKey();
    }

    //获取医院签名信息
    @Override
    public SignInfoVo getSignInfoVo(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        if(null == hospitalSet) {
            throw new YtException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        SignInfoVo signInfoVo = new SignInfoVo();
        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
        signInfoVo.setSignKey(hospitalSet.getSignKey());
        return signInfoVo;
    }


}
