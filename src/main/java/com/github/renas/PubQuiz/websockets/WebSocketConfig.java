package com.example.quizgame.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 1. The Connection Endpoint
        // This is what the frontend Host and Players connect to initially.
        // e.g., let socket = new SockJS('http://localhost:8080/ws');
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // Allows cross-origin requests (change to specific domains in production, e.g., "http://localhost:3000")
                .withSockJS();                 // Provides fallback options for browsers that don't support raw WebSockets
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 2. Outbound Messaging (Server to Client)
        // Enable an in-memory broker for public broadcasts (/topic) and private messages (/queue)
        config.enableSimpleBroker("/topic", "/queue");

        // 3. Inbound Messaging (Client to Server)
        // Messages sent from the frontend must start with this prefix to trigger your @MessageMapping controllers.
        // e.g., Frontend sends to "/app/game.join" -> Triggers @MessageMapping("/game.join")
        config.setApplicationDestinationPrefixes("/app");

        // 4. Private Messaging (User Specific)
        // Defines the prefix used to send messages to specific, targeted users instead of broadcasting.
        // e.g., Frontend subscribes to "/user/queue/reply" to get private acknowledge receipts.
        config.setUserDestinationPrefix("/user");
    }
}