package com.banner.utils;

import cn.hutool.core.util.StrUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

import static com.banner.utils.RedisConstants.LOGIN_TOKEN_KEY;

/**
 * @author rjj
 * @date 2022/10/13 - 20:19
 */
public class MessageHandshakeInterceptor implements HandshakeInterceptor {
    
    private StringRedisTemplate stringRedisTemplate;

    public MessageHandshakeInterceptor(){}

    public MessageHandshakeInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }
    
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        //获取路径中的token
        String path = request.getURI().getPath();
        String[] flag = path.split("/");
        if (flag.length != 3){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }

        //在redis查询是否存在(是否登陆)
        String key = LOGIN_TOKEN_KEY + flag[2];
        String id = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isEmpty(id)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }
        BaseContext.setUser(Long.valueOf(id));
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
