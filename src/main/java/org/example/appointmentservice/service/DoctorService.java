package org.example.appointmentservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class DoctorService {

    private final RestClient restClient;

    public DoctorService() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }

    @CircuitBreaker(
            name = "doctorServiceCB",
            fallbackMethod = "doctorServiceFallback"
    )
    public String getDoctorSchedule(Long doctorId) {

        return restClient.get()
                .uri("/api/v1/doctors/{id}/schedule", doctorId)
                .retrieve()
                .body(String.class);
    }

    public String doctorServiceFallback(
            Long doctorId,
            Throwable throwable
    ) {

        return "Doctor-Service đang không khả dụng. Vui lòng thử lại sau.";
    }
}
