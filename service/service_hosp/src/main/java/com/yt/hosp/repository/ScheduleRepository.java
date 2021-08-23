package com.yt.hosp.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import yt.model.hosp.Schedule;

import java.util.Date;
import java.util.List;


@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    //根据医院编号和排班编号进行查询
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

    //根据医院编号、科室编号和工作日期，查询排班详情信息
    List<Schedule> findScheduleByHoscodeAndDepcodeAndWorkDate(String hoscode, String depcode, Date toDate);
}
