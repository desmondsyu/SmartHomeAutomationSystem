package com.smarthome.service;

import com.smarthome.model.Device;
import com.smarthome.model.DeviceStatus;
import com.smarthome.repository.impl.DeviceRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class DeviceServiceUnitTest {
    @Mock
    private DeviceRepositoryImpl deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    private Device device;

    @BeforeEach
    void setUp() {
        device = new Device();
        device.setDeviceId(1);
        device.setDeviceName("Smart Light");
        device.setStatus(DeviceStatus.Status.OFF);
        device.setType("test type");
        device.setUserId(1);
    }

    @Test
    void testTurnOnDevice() {
        when(deviceRepository.update(device)).thenReturn(device);

        Device updatedDevice = deviceService.turnOnDevice(device);

        verify(deviceRepository, times(1)).update(device);
        assertEquals(DeviceStatus.Status.ON, updatedDevice.getStatus());
    }

    @Test
    void testTurnOffDevice() {
        device.setStatus(DeviceStatus.Status.ON);

        when(deviceRepository.update(device)).thenReturn(device);

        Device updatedDevice = deviceService.turnOffDevice(device);

        verify(deviceRepository, times(1)).update(device);
        assertEquals(DeviceStatus.Status.OFF, updatedDevice.getStatus());
    }
}
