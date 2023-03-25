package com.banner.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.banner.dto.LoginDto;
import com.banner.dto.LoginSuccessDto;
import com.banner.dto.UserDto;
import com.banner.mapper.UserInfoMapper;
import com.banner.mapper.UserMapper;
import com.banner.po.User;
import com.banner.po.UserInfo;
import com.banner.service.UserService;
import com.banner.utils.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

import static com.banner.utils.EmailInfo.CONTEXT;
import static com.banner.utils.EmailInfo.SUBJECT;
import static com.banner.utils.RedisConstants.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoMapper userInfoMapper;


    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.mail.username}")
    private String from;


    public void sendEmailCode(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from + "(考研吧)");
        message.setTo(email);
        message.setSubject(SUBJECT);
        message.setText(String.format(CONTEXT, code));
        javaMailSender.send(message);
    }

    @Override
    public R<String> sendCode(String email) {
        //获取邮箱地址
        if (RegexUtils.isNotEmail(email)) {
            PostgraduateForumException.error(CommonError.PARAMS_ERROR);
        }
        //生成验证码
        String code = RandomUtil.randomNumbers(6);
        //发送邮件
        this.sendEmailCode(email, code);
        log.info("已发送");

        //将生成的验证码保存
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + email, code, LOGIN_CODE_TIME, TimeUnit.MINUTES);
        return R.success("已发送");

    }

    @Override
    public R<LoginSuccessDto> userLogin(LoginDto loginDto) {


        //获取邮箱地址
        String email = loginDto.getEmail();
        //获取密码
        String password = loginDto.getPassword();

        if (!loginDto.getCode().equals(loginDto.getUserCode())) {
            PostgraduateForumException.error(CommonError.CODE_ERROR);
        }

        //判断邮箱和密码是否符合规则
        if (RegexUtils.isNotEmail(email) || RegexUtils.isNotPassword(password)) {
            PostgraduateForumException.error(CommonError.PARAMS_ERROR);
        }

        //在数据库中查询用户是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        User one = this.getOne(queryWrapper);

        if (one == null) {
            PostgraduateForumException.error(CommonError.QUERY_NULL);
        }

        if (!one.getPassword().equals(password)) {
            PostgraduateForumException.error(500, "密码错误");
        }

        //1.随机生成token,作为登陆令牌
        String token = UUID.randomUUID().toString(true);

        String keyToken = LOGIN_TOKEN_KEY + token;
        //2.将token存入redis
        stringRedisTemplate.opsForValue().set(keyToken, one.getId().toString());
        //3.设置token刷新时间
        stringRedisTemplate.expire(keyToken, LOGIN_TOKEN_TIME, TimeUnit.HOURS);

        LoginSuccessDto loginSuccessDto = new LoginSuccessDto();
        BeanUtil.copyProperties(one, loginSuccessDto);
        loginSuccessDto.setToken(token);

        return R.success(loginSuccessDto);
    }

    @Override
    @Transactional
    public R<String> register(LoginDto user) {
        String email = user.getEmail();
        String password = user.getPassword();
        String code = user.getCode();
        //从redis中获取验证码
//        String codeInRedis = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + email);
//        if (!code.equals(codeInRedis)) {
//            PostgraduateForumException.error(CommonError.CODE_ERROR);
//        }

        //判断邮箱和密码是否符合规则
        if (RegexUtils.isNotEmail(email) || RegexUtils.isNotPassword(password)) {
            PostgraduateForumException.error(CommonError.PARAMS_ERROR);
        }

        int isHave = userMapper.isHave(email);

        if (isHave > 0) {
            PostgraduateForumException.error(500, "账号已存在,请勿重复注册!");
        }

        long userId = IdWorker.getId();
        User newUser = new User(userId, email, password);
        userMapper.insert(newUser);
        String name = "banner" + RandomUtil.randomString(4);
        String avatar = "/default.jpeg";
        userInfoMapper.insert(new UserInfo(userId, name, avatar));

        return R.success("注册成功");
    }

    @Override
    public R<String> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        //删除redis中缓存token
        String key = LOGIN_TOKEN_KEY + token;
        stringRedisTemplate.delete(key);
        BaseContext.removeUser();
        return R.success("感谢使用,欢迎再次访问!");
    }

    @Override
    public R<String> deleteUser(String userIds) {

        String[] userIdList = userIds.split(",");
        for (String userId : userIdList) {
            if (StrUtil.isBlank(userId)) {
                userMapper.deleteById(BaseContext.getUserId());
            }
            userMapper.deleteById(userId);
        }
        return R.success("注销成功");
    }

    @Override
    public R<UserDto> getUser(String strUserId) {

        Long userId;
        if (StrUtil.isNotBlank(strUserId))
            userId = Long.valueOf(strUserId);
        else userId = BaseContext.getUserId();

        UserDto userDto = userInfoMapper.getNameAndAvatarById(userId);
        userDto.setId(userId);

        return R.success(userDto);
    }
}
