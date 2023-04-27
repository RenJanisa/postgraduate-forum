package com.banner.mapper;

import com.banner.dto.CommentDto;
import com.banner.dto.CommentsUserDto;
import com.banner.po.Comments;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface CommentMapper extends BaseMapper<Comments> {

    CommentDto getCommentByPostId(String postId);

    List<CommentsUserDto> getCommentByUserId(Long userId);
}
