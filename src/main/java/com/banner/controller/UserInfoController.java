package com.banner.controller;


import com.banner.dto.UserInfoDto;
import com.banner.service.UserInfoService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;


    @GetMapping
    public R<UserInfoDto> getUserInfo(String userId){
        return userInfoService.getUserInfo(userId);
    }

    @PutMapping
    public R<String> addUserInfo(@RequestBody @Validated UserInfoDto userInfoDto){
        return userInfoService.addUserInfo(userInfoDto);
    }



}
