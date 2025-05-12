package com.meditrack.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GroupUserResponse {
    private Long id;
    private Long userId;
    private Long groupId;
    private LocalDateTime joinedAt;
    private String role;
    private String status;
    private String notes;
}
