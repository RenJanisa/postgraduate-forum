package com.banner.mapper;

import com.banner.dto.ReplyDto;
import com.banner.po.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 评论回复表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface ReplyMapper extends BaseMapper<Reply> {

    List<ReplyDto> getReplyByCommentId(Long commentId);
}
