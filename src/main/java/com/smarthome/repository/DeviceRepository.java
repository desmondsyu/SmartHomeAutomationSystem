package com.smarthome.repository;

import com.smarthome.model.Device;

import java.util.List;

public interface DeviceRepository {
    List<Device> findAll();

    Device save(Device device);

    Device findById(Integer id);

    Device update(Device device);
}
