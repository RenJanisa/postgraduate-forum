package com.banner.service;

import com.banner.dto.CommentAddDto;
import com.banner.dto.CommentDeleteDto;
import com.banner.dto.CommentDto;
import com.banner.dto.CommentsUserDto;
import com.banner.po.Comments;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface CommentService extends IService<Comments> {

    R<CommentDto> getComment(String postId);

    R<String> addComment(CommentAddDto commentAddDto);

    R<String> addReply(CommentAddDto replyAddDto);

    R<String> deleteComment(CommentDeleteDto commentDeleteDto);

    R<List<CommentsUserDto>> getUserComment();
}
