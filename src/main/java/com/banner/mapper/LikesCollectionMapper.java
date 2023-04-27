package com.banner.mapper;

import com.banner.dto.UserLikesCollectionInfoDto;
import com.banner.po.LikesCollection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 赞和收藏表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface LikesCollectionMapper extends BaseMapper<LikesCollection> {

    Integer getLikesCollectionCont(@Param("flag") Integer flag, @Param("id") String id, @Param("type") Integer type);

    @Insert(" insert into likes_collection(id,post_id, m_id,create_time ,type) values (#{mainId},#{id}, #{userId}, #{time},#{type})")
    void addLikesCollection(Long mainId, String id, Integer type, Long userId, LocalDateTime time);

    @Insert(" insert into likes_collection(id,comment_id, m_id,create_time ,type) values (#{mainId},#{id}, #{userId}, #{time},#{type})")
    void addLikesCollectionComment(Long mainId, String id, Integer type, Long userId, LocalDateTime time);

    void deleteLikeCollection(@Param("flag") Integer flag, @Param("id") String id, @Param("userId") Long userId);

    List<UserLikesCollectionInfoDto> getUserLikesCollectionInfoPost(@Param("id") String id, @Param("type") Integer type);

    List<UserLikesCollectionInfoDto> getUserLikesCollectionInfoComment(@Param("id") String id, @Param("type") Integer type);

}

