package com.meditrack.service.impl;

import com.meditrack.dto.AppointmentRequest;
import com.meditrack.dto.AppointmentResponse;
import com.meditrack.entity.Appointment;
import com.meditrack.repository.AppointmentRepo;
import com.meditrack.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepo appointmentRepo;

    public AppointmentResponse createAppointment(AppointmentRequest request) {
        Appointment appointment = Appointment.builder()
                .patientName(request.getPatientName())
                .doctorName(request.getDoctorName())
                .appointmentDate(request.getAppointmentDate())
                .appointmentTime(request.getAppointmentTime())
                .reason(request.getReason())
                .build();

        Appointment saved = appointmentRepo.save(appointment);

        return AppointmentResponse.builder()
                .id(saved.getId())
                .patientName(saved.getPatientName())
                .doctorName(saved.getDoctorName())
                .appointmentDate(saved.getAppointmentDate())
                .appointmentTime(saved.getAppointmentTime())
                .reason(saved.getReason())
                .build();
    }

    public List<AppointmentResponse> getAllAppointments() {
        return appointmentRepo.findAll()
                .stream()
                .map(appointment -> AppointmentResponse.builder()
                        .id(appointment.getId())
                        .patientName(appointment.getPatientName())
                        .doctorName(appointment.getDoctorName())
                        .appointmentDate(appointment.getAppointmentDate())
                        .appointmentTime(appointment.getAppointmentTime())
                        .reason(appointment.getReason())
                        .build())
                .toList();
    }

    public AppointmentResponse getAppointmentById(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        return AppointmentResponse.builder()
                .id(appointment.getId())
                .patientName(appointment.getPatientName())
                .doctorName(appointment.getDoctorName())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getAppointmentTime())
                .reason(appointment.getReason())
                .build();
    }

    public String deleteAppointment(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        appointmentRepo.delete(appointment);
        return "Appointment with ID " + id + " has been deleted successfully.";
    }

    public AppointmentResponse updateAppointment(Long id, AppointmentRequest request) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        appointment.setPatientName(request.getPatientName());
        appointment.setDoctorName(request.getDoctorName());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setReason(request.getReason());

        Appointment updated = appointmentRepo.save(appointment);

        return AppointmentResponse.builder()
                .id(updated.getId())
                .patientName(updated.getPatientName())
                .doctorName(updated.getDoctorName())
                .appointmentDate(updated.getAppointmentDate())
                .appointmentTime(updated.getAppointmentTime())
                .reason(updated.getReason())
                .build();
    }

}
