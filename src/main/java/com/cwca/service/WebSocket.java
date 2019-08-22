package com.cwca.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: liforever
 * @Date: 2019/5/5 19:00
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        int size = webSocketSet.size();
        log.info("websocket消息 -- 建立连接,总数：" + size);
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("websocket消息 -- 连接断开,总数：" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("websocket 消息 -- 收到客户端发来的消息：{}", message);

    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("websockt消息 -- 广播消息，message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
