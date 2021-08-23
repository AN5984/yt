package com.yt.hosp.service;

import org.springframework.data.domain.Page;
import yt.model.hosp.Hospital;
import yt.vo.hosp.HospitalQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @author Zjd
 * @create 2021-08-17 20:42
 */

public interface HospitalService {

    void save(Map<String, Object> parampMap);

    String getHospName(String hoscode);


    Hospital getByHoscode(String hoscode);

    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    void updateStatus(String id, Integer status);

    Map<String, Object> getHospById(String id);

    List<Hospital> findByHosname(String hosname);

    Map<String, Object> item(String hoscode);
}
