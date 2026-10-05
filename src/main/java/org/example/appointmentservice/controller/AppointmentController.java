package org.example.appointmentservice.controller;

import org.example.appointmentservice.service.DoctorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final DoctorService doctorService;

    public AppointmentController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctor/{doctorId}/schedule")
    public String getDoctorSchedule(
            @PathVariable Long doctorId
    ) {

        return doctorService.getDoctorSchedule(doctorId);
    }
}
