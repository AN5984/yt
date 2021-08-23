package com.yt.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import yt.model.user.Patient;

/**
 * @author Zjd
 * @create 2021-08-23 15:59
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}
