package com.meditrack.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionResponse {

    private Long id;
    private Long appointmentId;
    private String patientName;
    private String doctorName;
    private LocalDate date;
    private String medicines;
    private String instructions;

}
