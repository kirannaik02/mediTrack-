package com.meditrack.dto;

import lombok.Data;

@Data
public class GroupRequest {
    private String groupName;
    private String description;
    private String createdBy;
}
