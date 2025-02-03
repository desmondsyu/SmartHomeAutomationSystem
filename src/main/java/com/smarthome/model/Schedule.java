package com.smarthome.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Schedule {
    private Integer scheduleId;
    private DeviceStatus.Status action;
    private String time;
    private Integer deviceId;

    public Schedule() {}

    public Schedule(Integer scheduleId, DeviceStatus.Status action, String time, Integer deviceId) {
        this.scheduleId = scheduleId;
        this.action = action;
        this.time = time;
        this.deviceId = deviceId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public DeviceStatus.Status getAction() {
        return action;
    }

    public void setAction(DeviceStatus.Status action) {
        this.action = action;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer device) {
        this.deviceId = device;
    }
}
