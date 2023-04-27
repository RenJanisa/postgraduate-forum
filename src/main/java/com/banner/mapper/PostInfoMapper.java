package com.banner.mapper;

import com.banner.dto.PostDto;
import com.banner.dto.PostRecommendDto;
import com.banner.dto.PostUserDto;
import com.banner.po.PostInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 帖子信息表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface PostInfoMapper extends BaseMapper<PostInfo> {

    @Select("select id,user_id,title,content,update_time,digest from post_info where id = #{postId}")
    PostDto getById(String postId);


    List<PostRecommendDto> getRecommendPost(Integer page, Integer pageSize);

    @Select("select id,title,update_time,digest,img,status from post_info where user_id = #{userId} AND status = #{status}")
    List<PostUserDto> getUserPost(Long userId, Integer status);

    List<PostUserDto> getPostPageWithName(Integer page, Integer pageSize, String name);

    List<PostUserDto> getPostPage(Integer page, Integer pageSize);
}
