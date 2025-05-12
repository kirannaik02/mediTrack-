package com.meditrack.dto;

import lombok.Data;

@Data
public class GroupUserRequest {
    private Long userId;
    private Long groupId;
    private String role;
    private String status;
    private String notes;
}
