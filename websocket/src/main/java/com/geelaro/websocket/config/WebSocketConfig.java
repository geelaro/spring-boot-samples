package com.geelaro.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler(), "ws/socket").setAllowedOrigins("*");
        registry.addHandler(wsHandler(), "ws/socketjs").setAllowedOrigins("*").withSockJS();
    }

    public WebSocketHandler wsHandler() {
        return new WsHandler();
    }
}
