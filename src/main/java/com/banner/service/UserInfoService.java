package com.banner.service;

import com.banner.dto.UserInfoDto;
import com.banner.po.UserInfo;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface UserInfoService extends IService<UserInfo> {

    R<UserInfoDto> getUserInfo(String userId);

    R<String> addUserInfo(UserInfo userInfo);
}
