package com.demo.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocket2 extends TextWebSocketHandler {

    /* OnOpen */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("WebSocket2 OnOpen:" + session.getId());
    }

    /* onClose */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("WebSocket2 onClose:" + session.getId());
    }

    /* onMessage */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        System.out.println("WebSocket2 onMessage:" + session.getId());
    }

    /* onError */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.out.println("WebSocket2 onError:" + session.getId());
    }

}
