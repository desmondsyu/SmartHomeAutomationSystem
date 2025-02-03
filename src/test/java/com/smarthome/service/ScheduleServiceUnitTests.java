package com.smarthome.service;

import com.smarthome.model.DeviceStatus;
import com.smarthome.model.Schedule;
import com.smarthome.repository.impl.ScheduleRepositoryImpl;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceUnitTests {
    @Mock
    private ScheduleRepositoryImpl scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    private Schedule schedule;

    @BeforeEach
    void setUp() {
        schedule = new Schedule();
        schedule.setScheduleId(1);
        schedule.setAction(DeviceStatus.Status.OFF);
        schedule.setDeviceId(1);
        schedule.setTime("17:00");
    }

    @Test
    void testUpdateSchedule() {
        schedule.setTime("08:00 AM");

        when(scheduleRepository.update(schedule)).thenReturn(schedule);

        Schedule updatedSchedule = scheduleService.updateSchedule(schedule);

        verify(scheduleRepository, times(1)).update(schedule);
        assertEquals("08:00 AM", updatedSchedule.getTime());
    }
}
