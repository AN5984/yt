package com.yt.hosp.controller;

import com.yt.DictFeignClient;
import com.yt.Result.Result;
import com.yt.hosp.repository.HospitalRepository;
import com.yt.hosp.service.HospitalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import yt.model.hosp.Hospital;
import yt.vo.hosp.HospitalQueryVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zjd
 * @create 2021-08-18 15:58
 */
@Api(tags="医院详细信息")
@RestController
@RequestMapping("/admin/hosp/hospital")
//@CrossOrigin
public class HospController {
    @Autowired
    private HospitalService hospitalService;

    @ApiOperation(value = "医院列表(条件查询分页)")
    @GetMapping("list/{page}/{limit}")
    public Result listHosp(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo hospitalQueryVo){  //将查询的条件参数封装到了HospitalSetQueryVo中
        //此时医院的数据存在于mongodb中
        Page<Hospital> pageModel = hospitalService.selectHospPage(page,limit,hospitalQueryVo);
        List<Hospital> content = pageModel.getContent();
        long totalElements = pageModel.getTotalElements();
        return Result.ok(pageModel);
    }

    @ApiOperation("更新医院的上线状态")
    @GetMapping("updateHospStatus/{id}/{status}")
    public Result updateHospStatus(@PathVariable("id") String id,
                                   @PathVariable("status") Integer status){
        hospitalService.updateStatus(id,status);
        return Result.ok();
    }

    @ApiOperation("查询医院详情")
    @GetMapping("showHospDetail/{id}")
    public Result showHospDetail(@PathVariable("id") String id){
        //为了方便后续取值方便，此处查询不用Hospital接受，而是放在集合里面
        Map<String, Object> map = hospitalService.getHospById(id);
        return Result.ok(map);
    }
}
