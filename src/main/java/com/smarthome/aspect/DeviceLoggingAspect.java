package com.smarthome.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DeviceLoggingAspect {
    @Pointcut("execution(* com.smarthome.controller.DeviceController.turnOn(..)) || execution(* com.smarthome.controller.DeviceController.turnOff(..))")
    public void deviceStatusChange() {}

    @After("deviceStatusChange()")
    public void logAfterDeviceChange() {
        System.out.println("Device status changed");
    }
}
