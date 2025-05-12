package com.meditrack.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionRequest {

    private Long appointmentId;
    private String medicines;
    private String instructions;


}
