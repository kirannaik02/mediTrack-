package com.meditrack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    private String senderId;
    private String recipientId;
    private String content;
    private Long groupId;

}
