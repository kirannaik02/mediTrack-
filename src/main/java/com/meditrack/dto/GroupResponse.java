package com.meditrack.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GroupResponse {
    private Long id;
    private String groupName;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
    private boolean isDeleted;
}
