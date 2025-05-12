package com.meditrack.controller;

import com.meditrack.dto.PrescriptionRequest;
import com.meditrack.dto.PrescriptionResponse;
import com.meditrack.service.impl.PrescriptionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionServiceImpl prescriptionService;

    @PostMapping
    public PrescriptionResponse createPrescription(@RequestBody PrescriptionRequest request) {
        return prescriptionService.createPrescription(request);
    }
    @GetMapping
    public List<PrescriptionResponse> getAllPrescription() {
        return prescriptionService.getAllPrescription();
    }
    @GetMapping("/{id}")
    public PrescriptionResponse getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }
    @DeleteMapping("/{id}")
    public String deletePrescription(@PathVariable Long id) {
        return prescriptionService.deletePrescription(id);
    }
    @PutMapping("/{id}")
    public PrescriptionResponse updatePrescription(@PathVariable Long id, @RequestBody PrescriptionRequest request) {
        return prescriptionService.updatePrescription(id, request);
    }


}
