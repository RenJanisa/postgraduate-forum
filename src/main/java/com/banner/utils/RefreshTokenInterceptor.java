package com.banner.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.banner.utils.RedisConstants.LOGIN_TOKEN_KEY;
import static com.banner.utils.RedisConstants.LOGIN_TOKEN_TIME;


/**
 * @author rjj
 * @date 2022/9/16 - 8:52
 */
@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //跨域处理,cros跨域请求会发起两次请求,放行opinions方式请求容许跨域
//        if (request.getMethod().equals("OPTIONS")) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            return true;
//        }

        //获取请求头中token
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            return true;
        }

        String key = LOGIN_TOKEN_KEY + token;
        //基于token获取redis中用户
        String id = stringRedisTemplate.opsForValue().get(key);
        //判断用户是否存在
        if (StrUtil.isEmpty(id)) {
            return true;
        }
        //存在,保存用户信息到ThreadLocal
        BaseContext.setUser(Long.valueOf(id));
        //刷新token有效期
        stringRedisTemplate.expire(key, LOGIN_TOKEN_TIME, TimeUnit.HOURS);
        //放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除用户
        BaseContext.removeUser();
    }

}
