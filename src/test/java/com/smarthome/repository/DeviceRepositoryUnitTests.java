package com.smarthome.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthome.model.Device;
import com.smarthome.model.DeviceStatus;
import com.smarthome.repository.impl.DeviceRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class DeviceRepositoryUnitTests {
    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private File file;

    @InjectMocks
    private DeviceRepositoryImpl deviceRepository;

    private Device device;

    @BeforeEach
    void setUp() {
        device = new Device();
        device.setDeviceId(1);
        device.setDeviceName("Smart Light");
        device.setType("test");
        device.setUserId(1);
        device.setStatus(DeviceStatus.Status.ON);
    }

    @Test
    void testSave() throws IOException {
        List<Device> devices = new ArrayList<>();
        when(objectMapper.readValue(any(File.class), eq(List.class))).thenReturn(devices);
        doNothing().when(objectMapper).writeValue(any(File.class), anyList());

        Device savedDevice = deviceRepository.save(device);

        assertNotNull(savedDevice);

        verify(objectMapper, times(1)).writeValue(eq(file), eq(devices));
    }
}
