package com.banner.service;

import com.banner.dto.LoginDto;
import com.banner.dto.LoginSuccessDto;
import com.banner.dto.UserDto;
import com.banner.po.User;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface UserService extends IService<User> {

    R<String> sendCode(String email);

    R<LoginSuccessDto> userLogin(LoginDto loginDto);

    R<String> register(LoginDto user);

    R<String> logout(HttpServletRequest request);

    R<String> deleteUser(String userIds);

    R<UserDto> getUser(String userId);
}
