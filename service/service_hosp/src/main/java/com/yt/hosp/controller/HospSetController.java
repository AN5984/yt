package com.yt.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yt.Result.Result;
import com.yt.hosp.service.HospSetService;
import com.yt.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import yt.model.hosp.HospitalSet;

import yt.vo.hosp.HospitalSetQueryVo;

import java.util.List;
import java.util.Random;

/**
 * @author Zjd
 * @create 2021-08-04 23:47
 */
@Api(tags="医院设置系统")
@RestController
@RequestMapping("/admin/hosp/hospSet")
//@CrossOrigin
public class HospSetController {
    @Autowired
    private HospSetService hospSetService;

    @ApiOperation(value = "获取全部医院设置信息")
    @GetMapping("findAll")
    public Result findAllHosp(){
        List<HospitalSet> hospitalSets = hospSetService.list();
        return Result.ok(hospitalSets);
    }
    @ApiOperation(value = "获取指定ID医院设置信息")
    @GetMapping("findbyID/{id}")
    public Result findByID(@PathVariable long id){
        HospitalSet hospSetServiceById = hospSetService.getById(id);
        return Result.ok(hospSetServiceById);
    }

    @ApiOperation(value = "按页获取医院设置信息")
    @PostMapping ("findPage/{current}/{limit}")
    public Result findPage(@PathVariable long current, @PathVariable long limit,@RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        Page<HospitalSet> page=new Page<HospitalSet>(current,limit);
        //构造查询条件
        String hosname = hospitalSetQueryVo.getHospname(); //医院名称
        String hoscode = hospitalSetQueryVo.getHospcode(); //医院编号
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(hosname)){
            wrapper.like("hosname",hosname);
        }
        if(!StringUtils.isEmpty(hoscode)){
            wrapper.eq("hoscode",hoscode);
        }
        //调用方法实现分页查询
        Page<HospitalSet> hospitalSetPage = hospSetService.page(page, wrapper);
        return Result.ok(hospitalSetPage);
    }

    @ApiOperation(value = "添加医院设置信息")
    @PostMapping ("addHosp")
    public Result findPage(@RequestBody HospitalSet hospitalSet){
        //状态 "1" 可以使用
        hospitalSet.setStatus(1);
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        boolean save = hospSetService.save(hospitalSet);
        if (save){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }



    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("delByID/{id}")
    public Result delByIDHosp(@PathVariable String id){

        boolean removeById = hospSetService.removeById(id);
        if (removeById){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation(value = "更新指定ID医院设置信息")
    @PostMapping ("update")
    public Result updateByID(@RequestBody HospitalSet hospitalSet){

        boolean updateById = hospSetService.updateById(hospitalSet);
        if (updateById){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "根据ID批量删除")
    @DeleteMapping("delByIDs")
    public Result delByIDsHosp(@RequestBody List<Long> ids ){
        boolean removeByIds = hospSetService.removeByIds(ids);
        if (removeByIds){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation(value = "锁定/解锁医院")
    @PutMapping("lockHospSet/{id}/{status}")
    public Result lockHospSet(@PathVariable long id,
                              @PathVariable Integer status){

        HospitalSet hospitalSet = hospSetService.getById(id);
        hospitalSet.setStatus(status);
        hospSetService.updateById(hospitalSet);
        return Result.ok();
    }


    @ApiOperation(value = "发送签名的秘钥KEY")
    @PutMapping("sendKey/{id}")
    public Result lockHospSet(@PathVariable long id){
        HospitalSet hospitalSet = hospSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //发送短信
        return Result.ok();
    }

}
