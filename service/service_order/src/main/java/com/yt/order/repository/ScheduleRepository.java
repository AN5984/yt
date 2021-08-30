package com.yt.order.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import yt.model.hosp.Schedule;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {


    Schedule getScheduleByDepcodeAndWorkDate(String depcode, String workDate);

}
