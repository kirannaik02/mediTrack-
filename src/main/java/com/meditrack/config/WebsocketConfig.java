package com.meditrack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")  // This is the WebSocket endpoint
                .setAllowedOriginPatterns("*")  // Allow all origins (you can specify here for your dev environment)
                .setAllowedOrigins("http://localhost:63342", "http://localhost:8080")  // Add allowed origins for MediTrack
                .withSockJS();  // Enables SockJS fallback for browsers without WebSocket support
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enabling message brokers for private messages and group chats
        registry.enableSimpleBroker("/queue", "/group");  // This will handle private messages to /queue and group messages to /group
        registry.setUserDestinationPrefix("/user");  // User-specific prefix for private messaging
        registry.setApplicationDestinationPrefixes("/app");  // Prefix for application-specific destinations, e.g., /app/private-message
    }
}
