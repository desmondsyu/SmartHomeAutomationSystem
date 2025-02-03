package com.smarthome.service;

import com.smarthome.model.Device;
import com.smarthome.model.DeviceStatus;
import com.smarthome.repository.impl.DeviceRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepositoryImpl deviceRepository;

    @Autowired
    public DeviceService(DeviceRepositoryImpl deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getDevices(){
        return deviceRepository.findAll();
    }

    public Device addDevice(Device device){
        return deviceRepository.save(device);
    }

    public Device findById(Integer id){
        return deviceRepository.findById(id);
    }

    public Device updateDevice(Device device){
        return deviceRepository.update(device);
    }

    public Device turnOnDevice(Device device){
        device.setStatus(DeviceStatus.Status.ON);
        return deviceRepository.update(device);
    }

    public Device turnOffDevice(Device device){
        device.setStatus(DeviceStatus.Status.OFF);
        return deviceRepository.update(device);
    }
}
