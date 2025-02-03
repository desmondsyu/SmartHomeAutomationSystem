package com.smarthome.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthome.model.Device;
import com.smarthome.repository.DeviceRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository {
    private final String FILE_PATH = "src/main/resources/file/devices.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Device> findAll() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Device>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Device save(Device device) {
        List<Device> devices = findAll();
        devices.add(device);
        writeToFile(devices);
        return device;
    }

    @Override
    public Device findById(Integer id) {
        return findAll().stream().filter(device -> device.getDeviceId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Device update(Device updatedDevice) {
        List<Device> devices = findAll();
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getDeviceId().equals(updatedDevice.getDeviceId())) {
                devices.set(i, updatedDevice);
                writeToFile(devices);
                return updatedDevice;
            }
        }
        return null;
    }

    private void writeToFile(List<Device> devices) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), devices);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}