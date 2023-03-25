package com.banner.utils;

import cn.hutool.json.JSONUtil;
import com.banner.po.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.banner.utils.RedisConstants.LOGIN_TOKEN_KEY;

/**
 * @author rjj
 * @date 2022/10/13 - 19:36
 * 私信处理器
 */
@Slf4j
public class MessageHandler extends TextWebSocketHandler {

    @Resource
    private MessageMapper messageMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Map<Long, WebSocketSession> SESSIONS = new HashMap<>();

    //用户和服务端建立连接后
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        Long id = BaseContext.getUserId();
        //将用户session放入map,后续会使用相应session通信
        SESSIONS.put(id, session);
    }

    //用户关闭连接后
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long id = BaseContext.getUserId();
        SESSIONS.remove(id);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {

        String path = session.getUri().getPath();
        String[] flag = path.split("/");
        String key = LOGIN_TOKEN_KEY + flag[2];

        String strId = stringRedisTemplate.opsForValue().get(key);
        assert strId != null;
        Long id = Long.valueOf(strId);

        Message message = JSONUtil.toBean(textMessage.getPayload(),Message.class);
        message.setFrom(id);

        //将消息保存到mongodb
        message = messageMapper.saveMessage(message);
        String messageJSON = JSONUtil.toJsonStr(message);

        //判断to用户是否在线
        WebSocketSession toSession = SESSIONS.get(message.getTo());
        if (toSession != null && toSession.isOpen()) {
            //向to用户发送消息
            toSession.sendMessage(new TextMessage(messageJSON));
            messageMapper.updateMessageStatus(message.getId(), 0);
        }
    }
}
