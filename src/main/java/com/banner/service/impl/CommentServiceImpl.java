package com.banner.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.banner.dto.CommentAddDto;
import com.banner.dto.CommentDeleteDto;
import com.banner.dto.CommentDto;
import com.banner.dto.CommentsUserDto;
import com.banner.mapper.CommentMapper;
import com.banner.mapper.ReplyMapper;
import com.banner.po.Comments;
import com.banner.po.Reply;
import com.banner.service.CommentService;
import com.banner.utils.BaseContext;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comments> implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private ReplyMapper replyMapper;

    @Override
    public R<CommentDto> getComment(String postId) {
        if (StrUtil.isBlank(postId)) PostgraduateForumException.error(CommonError.REQUEST_NULL);

        CommentDto commentDto = commentMapper.getCommentByPostId(postId);
        commentDto.setReply(replyMapper.getReplyByCommentId(commentDto.getId()));
        return R.success(commentDto);
    }

    @Override
    public R<String> addComment(CommentAddDto commentAddDto) {

        Comments comments = new Comments(BaseContext.getUserId(),commentAddDto.getContent(), Long.valueOf(commentAddDto.getObjId()));
        int insert = commentMapper.insert(comments);
        if (insert<=0) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);

        return R.success("评论成功!");
    }

    @Override
    public R<String> addReply(CommentAddDto replyAddDto) {

        Integer flag = replyAddDto.getFlag();
        String otherId = replyAddDto.getOtherId();
        if (StrUtil.isBlank(otherId)) PostgraduateForumException.error(500,"被评论用户不为空");
        // TODO 校验flag
        if (flag != 0 && flag != 1) PostgraduateForumException.error(500,"区分参数不合法");
        Reply reply = new Reply(BaseContext.getUserId(),replyAddDto.getContent(),Long.valueOf(replyAddDto.getObjId()), flag, Long.valueOf(otherId));

        int insert = replyMapper.insert(reply);
        if (insert<=0) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);

        return R.success("回复成功!");
    }

    @Override
    public R<String> deleteComment(CommentDeleteDto commentDeleteDto) {
        if (commentDeleteDto.getFlag() == 0){
            commentMapper.deleteById(commentDeleteDto.getId());
        }else if(commentDeleteDto.getFlag() == 1) {
            replyMapper.deleteById(commentDeleteDto.getId());
        }else PostgraduateForumException.error(CommonError.PARAMS_ERROR);
        return R.success("删除成功!");
    }

    @Override
    public R<List<CommentsUserDto>> getUserComment() {
        Long userId = BaseContext.getUserId();
        List<CommentsUserDto> commentByUserId = commentMapper.getCommentByUserId(userId);
        return R.success(commentByUserId);
    }
}
