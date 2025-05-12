package com.meditrack.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.meditrack.dto.AppointmentRequest;
import com.meditrack.dto.AppointmentResponse;
import com.meditrack.entity.Appointment;
import com.meditrack.repository.AppointmentRepo;
import com.meditrack.service.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepo appointmentRepo;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAppointment() {
        // Arrange
        AppointmentRequest request = AppointmentRequest.builder()
                .patientName("John Doe")
                .doctorName("Dr. Smith")
                .appointmentDate(LocalDate.of(2025, 5, 1))
                .appointmentTime(LocalTime.of(10, 30))
                .reason("Regular Checkup")
                .build();

        Appointment savedAppointment = Appointment.builder()
                .id(1L)
                .patientName(request.getPatientName())
                .doctorName(request.getDoctorName())
                .appointmentDate(request.getAppointmentDate())
                .appointmentTime(request.getAppointmentTime())
                .reason(request.getReason())
                .build();

        // Mocking the behavior of appointmentRepo.save()
        when(appointmentRepo.save(any(Appointment.class))).thenReturn(savedAppointment);

        // Act
        AppointmentResponse response = appointmentService.createAppointment(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("John Doe", response.getPatientName());
        assertEquals("Dr. Smith", response.getDoctorName());
        assertEquals(LocalDate.of(2025, 5, 1), response.getAppointmentDate());
        assertEquals(LocalTime.of(10, 30), response.getAppointmentTime());
        assertEquals("Regular Checkup", response.getReason());

        // Verify that appointmentRepo.save() was called exactly once
        verify(appointmentRepo, times(1)).save(any(Appointment.class));
    }

    @Test
    void testGetAllAppointments() {
        // Arrange
        when(appointmentRepo.findAll()).thenReturn(List.of(
                Appointment.builder().id(1L).patientName("John").doctorName("Dr. A").build(),
                Appointment.builder().id(2L).patientName("Jane").doctorName("Dr. B").build()
        ));

        // Act
        List<AppointmentResponse> responses = appointmentService.getAllAppointments();

        // Assert
        assertEquals(2, responses.size());
        assertEquals("John", responses.get(0).getPatientName());
        assertEquals("Jane", responses.get(1).getPatientName());

        verify(appointmentRepo, times(1)).findAll();
    }


}
