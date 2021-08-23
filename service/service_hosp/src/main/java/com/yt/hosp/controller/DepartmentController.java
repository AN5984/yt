package com.yt.hosp.controller;

import com.yt.Result.Result;
import com.yt.hosp.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yt.vo.hosp.DepartmentVo;

import java.util.List;

/**
 * @author Zjd
 * @create 2021-08-18 21:01
 */
@Api(tags="医院科室信息")
@RestController
@RequestMapping("/admin/hosp/department")
//@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //根据医院编号，查询所有科室列表
    @ApiOperation("查询所有科室列表")
    @GetMapping("getDepList/{hoscode}")
    public Result getDepList(@PathVariable("hoscode") String hoscode){
        //大科室下面还有小科室，不止一个科室，所以将返回数据封装在DepartmentVo
        List<DepartmentVo> list = departmentService.findDepTree(hoscode);
        return Result.ok(list);
    }

}
