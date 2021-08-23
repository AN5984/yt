package com.yt.user.controller.api;

import com.yt.Result.Result;
import com.yt.Utils.AuthContextHolder;

import com.yt.user.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yt.model.user.Patient;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zjd
 * @create 2021-08-23 16:03
 */
@Api(tags="就诊人管理")
@RestController
@RequestMapping("/api/user/patient")
public class ApiPatientController {
    @Autowired
    private PatientService patientService;
    @ApiOperation("获取就诊人列表接口")
    @GetMapping("auth/findAll")
    public Result findAll(HttpServletRequest request){
        //获取到当前登录用户的id
        Long userId = AuthContextHolder.getUserId(request);
        //根据登录用户id获取就诊列表
        List<Patient> list = patientService.findAllUserId(userId);
        return Result.ok(list);
    }

    @ApiOperation("添加就诊人")
    @PostMapping("auth/save")
    public Result savePatient(@RequestBody Patient patient,HttpServletRequest request){
        //获取到当前登录用户的id
        Long userId = AuthContextHolder.getUserId(request);
        patient.setUserId(userId);
        patientService.save(patient);
        return Result.ok();
    }

    @ApiOperation("根据就诊人id获取就诊人信息")
    @GetMapping("auth/get/{id}")
    public Result getPatient(@PathVariable("id") Long id){
        Patient patient = patientService.getPatientById(id);
        return Result.ok(patient);
    }

    @ApiOperation("修改就诊人")
    @PostMapping("auth/update")
    public Result updatePatient(@RequestBody Patient patient){
        patientService.updateById(patient);
        return Result.ok();
    }

    @ApiOperation("删除就诊人")
    @DeleteMapping("auth/remove/{id}")
    public Result removePatient(@PathVariable("id") Long id){
        patientService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("根据就诊人id获取就诊人信息")
    @GetMapping("inner/get/{id}")
    public Patient getPatientOrder(@PathVariable("id") Long id){
        Patient patient = patientService.getPatientById(id);
        return patient;
    }

}
