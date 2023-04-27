package com.banner.mapper;

import com.banner.dto.FollowUserDto;
import com.banner.dto.UserDto;
import com.banner.po.FollowRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 关注关系表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface FollowRelationMapper extends BaseMapper<FollowRelation> {
    List<Long> getOtherId(@Param("myId") Long myId);

    List<FollowUserDto> getFollowByUserId(Long userId);

    List<FollowUserDto> getFanByUserId(Long userId);

    @Select("SELECT COUNT(user_id) FROM follow_relation WHERE user_id = #{userId} AND obj_id = #{otherId}")
    int getIsHave(Long userId, String otherId);

    @Delete("delete from follow_relation where user_id = #{userId} and obj_id = #{otherId}")
    void deleteFollow(Long userId, Long otherId);
}
