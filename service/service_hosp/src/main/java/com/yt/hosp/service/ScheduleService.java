package com.yt.hosp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import yt.model.hosp.Schedule;

import yt.vo.hosp.ScheduleOrderVo;
import yt.vo.hosp.ScheduleQueryVo;

import java.util.List;
import java.util.Map;

public interface ScheduleService extends IService<Schedule> {
    //上传排班
    void save(Map<String, Object> parampMap);

    //查询科室
    Page<Schedule> finPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    //删除排班
    void remove(String hoscode, String hosScheduleId);

    //根据医院编号和科室编号，查询排班规则数据
    Map<String, Object> getReleSchedule(Long page, Long limit, String hoscode, String depcode);

    //根据医院编号、科室编号和工作日期，查询排班详情信息
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);


    //根据排班id获取排班数据
    Schedule getScheduleId(String scheduleId);


    //更新排班，用于mq
    void update(Schedule schedule);

    Object getBookingScheduleRule(Integer page, Integer limit, String hoscode, String depcode);

    ScheduleOrderVo getScheduleOrderVo(String scheduleId);
}
