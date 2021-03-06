package com.yt.hosp.controller.api;

import com.yt.Result.Result;
import com.yt.hosp.service.DepartmentService;
import com.yt.hosp.service.HospSetService;
import com.yt.hosp.service.HospitalService;
import com.yt.hosp.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yt.model.hosp.Hospital;
import yt.model.hosp.Schedule;
import yt.vo.hosp.DepartmentVo;
import yt.vo.hosp.HospitalQueryVo;
import yt.vo.hosp.ScheduleOrderVo;
import yt.vo.order.SignInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @author Zjd
 * @create 2021-08-21 16:04
 */
@Api("医院管理接口")
@RestController
@RequestMapping("/api/hosp/hospital")
public class ApiHospController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private HospSetService hospitalSetService;

    @ApiOperation("查询医院列表")
    @GetMapping("findHospList/{page}/{limit}")
    public Result findHospList(@PathVariable("page") Integer page,
                               @PathVariable("limit") Integer limit,
                               HospitalQueryVo hospitalQueryVo){
        Page<Hospital> hospitals = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        List<Hospital> content = hospitals.getContent();  //供前端调用
        int totalPages = hospitals.getTotalPages();  //供前端调用
        return Result.ok(hospitals);
    }


    @ApiOperation("根据医院名称查询")
    @GetMapping("findByHosname/{hosname}")
    public Result findHospList(@PathVariable("hosname") String hosname){
        List<Hospital> list = hospitalService.findByHosname(hosname);  //模糊查询 可能返回多个，用list接收
        return Result.ok(list);
    }


    @ApiOperation("根据医院编号获取科室列表")
    @GetMapping("department/{hoscode}")
    public Result index(@PathVariable("hoscode") String hoscode){
        List<DepartmentVo> list = departmentService.findDepTree(hoscode);
        return Result.ok(list);
    }


    @ApiOperation(value = "根据医院编号获取预约挂号详情")
    @GetMapping("findHospDetail/{hoscode}")
    public Result item(@PathVariable("hoscode") String hoscode){
        Map<String,Object> map = hospitalService.item(hoscode);
        return Result.ok(map);
    }


    @ApiOperation(value = "获取可预约排班数据")
    @GetMapping("auth/getBookingScheduleRule/{page}/{limit}/{hoscode}/{depcode}")
    public Result getBookingSchedule(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode,
            @ApiParam(name = "depcode", value = "科室code", required = true)
            @PathVariable String depcode) {
        return Result.ok(scheduleService.getBookingScheduleRule(page, limit, hoscode, depcode));
    }

    //获取排班具体数据
    @ApiOperation(value = "获取排班具体数据")
    @GetMapping("auth/findScheduleList/{hoscode}/{depcode}/{workDate}")
    public Result findScheduleList(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode,
            @ApiParam(name = "depcode", value = "科室code", required = true)
            @PathVariable String depcode,
            @ApiParam(name = "workDate", value = "排班日期", required = true)
            @PathVariable String workDate) {
        return Result.ok(scheduleService.getDetailSchedule(hoscode, depcode, workDate));
    }

    //根据排班id获取排班数据
    @ApiOperation(value = "根据排班id获取排班数据")
    @GetMapping("getSchedule/{scheduleId}")
    public Result getSchedule(@PathVariable("scheduleId") String scheduleId){
        Schedule schedule = scheduleService.getScheduleId(scheduleId);
        return Result.ok(schedule);
    }

    @ApiOperation(value = "根据排班id获取预约下单数据")
    @GetMapping("inner/getScheduleOrderVo/{scheduleId}")
    public ScheduleOrderVo getScheduleOrderVo(@PathVariable("scheduleId") String scheduleId) {
        return scheduleService.getScheduleOrderVo(scheduleId);
    }

    @ApiOperation(value = "获取医院签名信息")
    @GetMapping("inner/getSignInfoVo/{hoscode}")
    public SignInfoVo getSignInfoVo(@PathVariable("hoscode") String hoscode) {
        return hospitalSetService.getSignInfoVo(hoscode);
    }

}
