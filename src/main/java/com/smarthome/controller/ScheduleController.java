package com.smarthome.controller;

import com.smarthome.model.Schedule;
import com.smarthome.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public String getAllSchedules(Model model) throws IOException {
        model.addAttribute("schedules", scheduleService.getSchedules());
        return "schedule";
    }

//    @PostMapping
//    public Schedule createSchedule(@RequestBody Schedule schedule) {
//        return scheduleService.addSchedule(schedule);
//    }

//    @GetMapping("{id}")
//    public Schedule getById(@PathVariable Integer id) {
//        return scheduleService.findById(id);
//    }

    @PutMapping("{id}")
    public String updateSchedule(@PathVariable Integer id, @RequestParam String time, RedirectAttributes redirectAttributes) {
        Schedule schedule = scheduleService.findById(id);
        schedule.setTime(time);
        scheduleService.updateSchedule(schedule);
        redirectAttributes.addFlashAttribute("message", "schedule " + id + " has been updated");
        return "redirect:/schedule";
    }
}
