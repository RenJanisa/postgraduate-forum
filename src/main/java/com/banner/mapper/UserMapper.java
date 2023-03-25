package com.banner.mapper;

import com.banner.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

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
}
