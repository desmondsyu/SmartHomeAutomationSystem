package com.smarthome.service;

import com.smarthome.model.Schedule;
import com.smarthome.repository.impl.ScheduleRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepositoryImpl scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepositoryImpl scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getSchedules(){
        return scheduleRepository.findAll();
    }

    public Schedule addSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public Schedule findById(Integer id){
        return scheduleRepository.findById(id);
    }

    public Schedule updateSchedule(Schedule schedule){
        return scheduleRepository.update(schedule);
    }
}
