package com.banner.mapper;

import com.banner.dto.UserPageDto;
import com.banner.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select COUNT(id) from user where email = #{email}")
    int isHave(String email);

    @Select("select u.id,u.email,u.password,u.create_time,i.user_name,i.sex,i.age from user u,user_info i where u.id = i.user_id limit #{page},#{pageSize}")
    List<UserPageDto> getUserPage(Integer page, Integer pageSize);
    @Select("select u.id,u.email,u.password,u.create_time,i.user_name,i.sex,i.age from user u,user_info i where i.user_name like #{userName} and u.id = i.user_id limit #{page},#{pageSize}")
    List<UserPageDto> getUserPageWithName(Integer page, Integer pageSize, String userName);
}
