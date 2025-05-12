package com.meditrack.controller;

import com.meditrack.dto.MessageRequest;
import com.meditrack.dto.MessageResponse;
import com.meditrack.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // Private message endpoint
    @MessageMapping("/private-message")
    public void handlePrivateMessage(MessageRequest messageRequest) {
        messageService.sendPrivateMessage(messageRequest);
    }

    // Group message endpoint
    @MessageMapping("/group-message")
    public void handleGroupMessage(MessageRequest messageRequest) {
        messageService.sendGroupMessage(messageRequest);
    }
}
