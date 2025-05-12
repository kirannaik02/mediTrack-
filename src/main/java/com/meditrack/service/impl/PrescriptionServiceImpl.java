package com.meditrack.service.impl;

import com.meditrack.dto.PrescriptionRequest;
import com.meditrack.dto.PrescriptionResponse;
import com.meditrack.entity.Appointment;
import com.meditrack.entity.Prescription;
import com.meditrack.repository.AppointmentRepo;
import com.meditrack.repository.PrescriptionRepo;
import com.meditrack.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepo prescriptionRepo;
    private final AppointmentRepo appointmentRepo;

    public PrescriptionResponse createPrescription(PrescriptionRequest request) {
        Appointment appointment = appointmentRepo.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + request.getAppointmentId()));

        Prescription prescription = Prescription.builder()
                .appointment(appointment)
                .medicines(request.getMedicines())
                .instructions(request.getInstructions())
                .build();

        Prescription saved = prescriptionRepo.save(prescription);

        return PrescriptionResponse.builder()
                .id(saved.getId())
                .appointmentId(saved.getAppointment().getId())
                .patientName(appointment.getPatientName())
                .doctorName(appointment.getDoctorName())
                .date(appointment.getDate())
                .medicines(saved.getMedicines())
                .instructions(saved.getInstructions())
                .build();
    }

    public List<PrescriptionResponse> getAllPrescription() {
        return prescriptionRepo.findAll()
                .stream()
                .map(prescription -> {
                    Appointment appointment = prescription.getAppointment();
                    return PrescriptionResponse.builder()
                            .id(prescription.getId())
                            .appointmentId(appointment != null ? appointment.getId() : null)
                            .patientName(appointment != null ? appointment.getPatientName() : null)
                            .doctorName(appointment != null ? appointment.getDoctorName() : null)
                            .date(appointment != null ? appointment.getDate() : null)
                            .medicines(prescription.getMedicines())
                            .instructions(prescription.getInstructions())
                            .build();
                })
                .toList();
    }

    public PrescriptionResponse getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));

        Appointment appointment = prescription.getAppointment();

        return PrescriptionResponse.builder()
                .id(prescription.getId())
                .appointmentId(appointment != null ? appointment.getId() : null)
                .patientName(appointment != null ? appointment.getPatientName() : null)
                .doctorName(appointment != null ? appointment.getDoctorName() : null)
                .date(appointment != null ? appointment.getDate() : null)
                .medicines(prescription.getMedicines())
                .instructions(prescription.getInstructions())
                .build();
    }

    public String deletePrescription(Long id) {
        Prescription prescription = prescriptionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));

        prescriptionRepo.delete(prescription);
        return "Prescription with ID " + id + " has been deleted successfully.";
    }

    public PrescriptionResponse updatePrescription(Long id, PrescriptionRequest request) {
        Prescription prescription = prescriptionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));

        Appointment appointment = appointmentRepo.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + request.getAppointmentId()));

        prescription.setAppointment(appointment);
        prescription.setMedicines(request.getMedicines());
        prescription.setInstructions(request.getInstructions());

        Prescription updated = prescriptionRepo.save(prescription);

        return PrescriptionResponse.builder()
                .id(updated.getId())
                .appointmentId(appointment.getId())
                .patientName(appointment.getPatientName())
                .doctorName(appointment.getDoctorName())
                .date(appointment.getDate())
                .medicines(updated.getMedicines())
                .instructions(updated.getInstructions())
                .build();
    }

}
