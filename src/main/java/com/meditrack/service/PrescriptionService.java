package com.meditrack.service;

import com.meditrack.dto.PrescriptionRequest;
import com.meditrack.dto.PrescriptionResponse;

import java.util.List;

public interface PrescriptionService {

    PrescriptionResponse createPrescription(PrescriptionRequest request);

    List<PrescriptionResponse> getAllPrescription();

    PrescriptionResponse getPrescriptionById(Long id);

    String deletePrescription(Long id);

    PrescriptionResponse updatePrescription(Long id, PrescriptionRequest request);
}
