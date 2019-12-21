package com.geelaro.websocket.config;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class MyHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
//        HashMap map = JSONObject.parseObject(payload, HashMap.class);

        System.out.println(session.getId() + "===接收到的数据：" + payload);
        session.sendMessage(new TextMessage(session.getId() + "=服务器返回收到的信息：" + payload));

    }
}
