package com.meditrack.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meditrack.controller.AppointmentController;
import com.meditrack.dto.AppointmentRequest;
import com.meditrack.dto.AppointmentResponse;
import com.meditrack.security.JwtService;
import com.meditrack.service.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AppointmentController.class)  // Only this is needed for controller testing
@AutoConfigureMockMvc
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtService jwtService; // Mock JwtService if needed for authorization

    @MockitoBean
    private AppointmentServiceImpl appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAppointment() throws Exception {
        // Arrange
        AppointmentRequest request = AppointmentRequest.builder()
                .patientName("John Doe")
                .doctorName("Dr. Smith")
                .appointmentDate(LocalDate.of(2025, 5, 1))
                .appointmentTime(LocalTime.of(10, 30))
                .reason("Checkup")
                .build();

        AppointmentResponse response = AppointmentResponse.builder()
                .id(1L)
                .patientName("John Doe")
                .doctorName("Dr. Smith")
                .appointmentDate(LocalDate.of(2025, 5, 1))
                .appointmentTime(LocalTime.of(10, 30))
                .reason("Checkup")
                .build();

        when(appointmentService.createAppointment(request)).thenReturn(response);

        // Act + Assert
        mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.patientName").value("John Doe"))
                .andExpect(jsonPath("$.doctorName").value("Dr. Smith"));
    }
}
