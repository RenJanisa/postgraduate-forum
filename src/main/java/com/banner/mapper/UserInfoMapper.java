package com.banner.mapper;

import com.banner.dto.LoginSuccessDto;
import com.banner.dto.UserDto;
import com.banner.dto.UserInfoDto;
import com.banner.po.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select user_name,avatar from user_info where user_id = #{item}")
    UserDto getNameAndAvatarById(Long item);


    UserInfoDto getUserInfo(@Param("userId") Long userId);

    @Select("select u.user_name,u.avatar,u.institution_id,i.institution_name from user_info u,institution i where user_id = #{id} and u.institution_id = i.id")
    LoginSuccessDto getUserLoginInfo(Long id);
}
