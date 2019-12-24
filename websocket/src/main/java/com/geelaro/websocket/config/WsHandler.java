package com.geelaro.websocket.config;

import com.alibaba.fastjson.JSONObject;
import com.geelaro.websocket.entity.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Date;
import java.util.HashSet;

@Component
public class WsHandler extends AbstractWebSocketHandler {

    private static final HashSet<WebSocketSession> SESSIONS_SET;

    static {
        SESSIONS_SET = new HashSet<>();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        Message msg = JSONObject.parseObject(payload, Message.class);
        msg.setDate(new Date());
        System.out.println(session.getId() + "==接收到的数据：" + payload);
        Thread.sleep(2000);
        session.sendMessage(new TextMessage(JSONObject.toJSONStringWithDateFormat(msg, "yyyy-MM-dd HH:mm:ss")));

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSIONS_SET.add(session);
        System.out.println(session.getId() + " 已连接" + "，当前连接量:" + getOnlineCount());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        SESSIONS_SET.remove(session);
        System.out.println(session.getId() + " 已关闭:" + status.getCode() + "，当前连接量:" + getOnlineCount());
    }


    public int getOnlineCount() {
        return SESSIONS_SET.size();
    }
}
