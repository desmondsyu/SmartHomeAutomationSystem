package com.smarthome.controller;

import com.smarthome.model.Device;
import com.smarthome.model.DeviceStatus;
import com.smarthome.model.Schedule;
import com.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public String getAllDevices(Model model) throws IOException {
        model.addAttribute("devices", deviceService.getDevices());
        return "devices";
    }

    @GetMapping("{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("device", deviceService.findById(id));
        return "device-control";
    }

    @PutMapping("/on/{id}")
    public String turnOn(@PathVariable Integer id, @RequestParam DeviceStatus.Status status, RedirectAttributes redirectAttributes) {
        Device device = deviceService.findById(id);
        device.setStatus(status);
        deviceService.turnOnDevice(device);
        redirectAttributes.addFlashAttribute("message", "device " + id + " has been turned ON");
        return "redirect:/device";
    }

    @PutMapping("/off/{id}")
    public String turnOff(@PathVariable Integer id, @RequestParam DeviceStatus.Status status, RedirectAttributes redirectAttributes) {
        Device device = deviceService.findById(id);
        device.setStatus(status);
        deviceService.turnOffDevice(device);
        redirectAttributes.addFlashAttribute("message", "device " + id + " has been turned OFF");
        return "redirect:/device";
    }

}
