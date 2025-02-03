package com.smarthome.model;

import org.springframework.stereotype.Component;

@Component
public class DeviceStatus {
    public enum Status{
        ON, OFF
    }
}
