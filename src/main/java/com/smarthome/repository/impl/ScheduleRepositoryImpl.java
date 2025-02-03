package com.smarthome.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthome.model.Schedule;
import com.smarthome.repository.ScheduleRepository;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final String FILE_PATH = "src/main/resources/file/schedules.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Schedule> findAll() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Schedule>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Schedule save(Schedule schedule) {
        List<Schedule> schedules = findAll();
        schedules.add(schedule);
        writeToFile(schedules);
        return schedule;
    }

    @Override
    public Schedule findById(Integer id) {
        return findAll().stream().filter(schedule -> schedule.getScheduleId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Schedule update(Schedule updatedSchedule) {
        List<Schedule> schedules = findAll();
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).getScheduleId().equals(updatedSchedule.getScheduleId())) {
                schedules.set(i, updatedSchedule);
                writeToFile(schedules);
                return updatedSchedule;
            }
        }
        return null;
    }

    private void writeToFile(List<Schedule> schedules) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), schedules);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
