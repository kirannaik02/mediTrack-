package com.meditrack.service;

import com.meditrack.dto.MessageRequest;
import com.meditrack.dto.MessageResponse;

public interface MessageService {

    // Method to send private message
    MessageResponse sendPrivateMessage(MessageRequest messageRequest);

    // Method to send group message
    MessageResponse sendGroupMessage(MessageRequest messageRequest);
}
