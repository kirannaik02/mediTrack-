package com.meditrack.service.impl;

import com.meditrack.dto.MessageRequest;
import com.meditrack.entity.Message;
import com.meditrack.repository.MessageRepo;
import com.meditrack.service.MessageService;
import com.meditrack.dto.MessageResponse;  // Import the correct class
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public MessageResponse sendPrivateMessage(MessageRequest messageRequest) {
        // Save the message to the database
        Message message = saveMessage(messageRequest);

        // Send the message via WebSocket to the recipient
        messagingTemplate.convertAndSendToUser(messageRequest.getRecipientId(), "/queue/messages", messageRequest);

        // Return the response
        return mapToResponse(message);
    }

    @Override
    public MessageResponse sendGroupMessage(MessageRequest messageRequest) {
        // Save the message to the database
        Message message = saveMessage(messageRequest);

        // Send the message via WebSocket to the group
        messagingTemplate.convertAndSend("/group/" + messageRequest.getGroupId(), messageRequest);

        // Return the response
        return mapToResponse(message);
    }

    // Helper method to save the message to the database
    private Message saveMessage(MessageRequest messageRequest) {
        Message message = new Message();
        message.setSenderId(messageRequest.getSenderId());
        message.setRecipientId(messageRequest.getRecipientId());
        message.setGroupId(messageRequest.getGroupId());
        message.setContent(messageRequest.getContent());
        message.setTimestamp(LocalDateTime.now());


        // Save the message to the database and return it
        return messageRepository.save(message);
    }

    // Helper method to map the message entity to the response DTO
    private MessageResponse mapToResponse(Message message) {
        MessageResponse response = new MessageResponse();  // Use MessageResponse here
        response.setId(message.getId());
        response.setSenderId(message.getSenderId());
        response.setRecipientId(message.getRecipientId());
        response.setGroupId(message.getGroupId());
        response.setContent(message.getContent());
        response.setTimestamp(message.getTimestamp());

        return response;
    }
}
