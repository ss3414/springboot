package com.websocket;

import com.util.Util;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint(value = "/websocket1")
public class WebSocket1 {

    private Session session;

    /* WebSocket无法直接注入 */
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocket1.applicationContext = applicationContext;
    }

    /* 每次连接相当于新建一个WebSocket对象进行通信 */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        System.out.println("WebSocket1 onOpen:" + this.session.getId());
        Util util = applicationContext.getBean(Util.class);
        util.test();
        for (int i = 1; i < 10; i++) {
            session.getBasicRemote().sendText("onOpen" + i);
        }
    }

    @OnClose
    public void onClose() {
        System.out.println("WebSocket1 onClose:" + session.getId());
    }

    /* 接收消息 */
    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("WebSocket1 onMessage:" + session.getId());
        System.out.println(message);
        session.getBasicRemote().sendText(message); /* 发送消息 */
    }

    /* 必须包含参数（Throwable error），否则报错 */
    @OnError
    public void onError(Throwable error) {
        System.out.println("WebSocket1 onError:" + session.getId());
    }

}
