package com.smarthome.repository;

import com.smarthome.model.Schedule;

import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findAll();

    Schedule save(Schedule schedule);

    Schedule findById(Integer id);

    Schedule update(Schedule schedule);
}
