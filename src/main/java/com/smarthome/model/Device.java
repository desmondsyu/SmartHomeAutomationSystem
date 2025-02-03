package com.smarthome.model;

import org.springframework.stereotype.Component;

@Component
public class Device {
    private Integer deviceId;
    private String deviceName;
    private String type;
    private DeviceStatus.Status status;
    private Integer userId;

    public Device() {
    }

    public Device(Integer deviceId, String deviceName, String type, DeviceStatus.Status status, Integer userId) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.type = type;
        this.status = status;
        this.userId = userId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DeviceStatus.Status getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus.Status status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
