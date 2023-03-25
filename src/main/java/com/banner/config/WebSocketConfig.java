package com.banner.config;

import com.banner.utils.MessageHandler;
import com.banner.utils.MessageHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * @author rjj
 * @date 2022/10/13 - 20:08
 */
@Configuration
@EnableWebSocket
@Import(MessageHandler.class)
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    private MessageHandler messageHandler;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(this.messageHandler,"/ws/{token}")
                //允许跨域
                .setAllowedOrigins("*")
                .addInterceptors(new MessageHandshakeInterceptor(stringRedisTemplate));
    }
}
