package com.banner.controller;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.banner.dto.LoginDto;
import com.banner.dto.LoginSuccessDto;
import com.banner.dto.UserDto;
import com.banner.po.User;
import com.banner.service.UserService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    @GetMapping
    public R<UserDto> getUser(String userId){
        return userService.getUser(userId);
    }

    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    @GetMapping("/sendCode")
    public R<String> sendCode(String email) {
        return userService.sendCode(email);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping
    public R<String> register(@RequestBody @Validated LoginDto user) {
        return userService.register(user);
    }


    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public R<LoginSuccessDto> login(@RequestBody @Validated LoginDto loginDto, HttpServletRequest request){
        return userService.userLogin(loginDto, request);
    }


    /**
     * 用户登出
     * @return
     */
    @GetMapping("/logout")
    public R<String> loginOut(HttpServletRequest request) {
        return userService.logout(request);
    }


    @DeleteMapping
    public R<String> deleteUser(String userIds){
        return userService.deleteUser(userIds);
    }


}
