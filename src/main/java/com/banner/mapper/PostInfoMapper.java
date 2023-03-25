package com.banner.mapper;

import com.banner.dto.PostDto;
import com.banner.po.PostInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 帖子信息表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface PostInfoMapper extends BaseMapper<PostInfo> {

    @Select("select id,title,content,update_time,digest from post_info where id = #{postId}")
    PostDto getById(String postId);
}
