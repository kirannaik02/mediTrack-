package com.meditrack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private Long id;
    private String senderId;
    private String recipientId;
    private String content;
    private Long groupId;
    private LocalDateTime timestamp;
}
