package com.meditrack.service;

import com.meditrack.dto.AppointmentRequest;
import com.meditrack.dto.AppointmentResponse;

import java.util.List;

public interface AppointmentService {

    AppointmentResponse createAppointment(AppointmentRequest request);

    List<AppointmentResponse> getAllAppointments();

    AppointmentResponse getAppointmentById(Long id);

    String deleteAppointment(Long id);

    AppointmentResponse updateAppointment(Long id, AppointmentRequest request);
}
