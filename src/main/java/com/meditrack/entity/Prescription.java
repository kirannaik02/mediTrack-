package com.meditrack.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    private String doctorName;
    private LocalDate date;

    @Column(length = 1000)
    private String medicines;

    private String instructions;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

}
