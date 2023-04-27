package com.banner.controller;


import com.banner.dto.CommentAddDto;
import com.banner.dto.CommentDeleteDto;
import com.banner.dto.CommentDto;
import com.banner.dto.CommentsUserDto;
import com.banner.po.Comments;
import com.banner.service.CommentService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping
    public R<CommentDto> getComment(String postId){
        return commentService.getComment(postId);
    }

    @PostMapping
    public R<String> addComment(@Validated CommentAddDto commentAddDto){
        return commentService.addComment(commentAddDto);
    }

    @PostMapping("/reply")
    public R<String> addReply(@Validated CommentAddDto replyAddDto){
        return commentService.addReply(replyAddDto);
    }

    @DeleteMapping
    public R<String> deleteComment(@Validated CommentDeleteDto commentDeleteDto){
        return commentService.deleteComment(commentDeleteDto);
    }

    @GetMapping("/user")
    public R<List<CommentsUserDto>> getUserComment(){
        return commentService.getUserComment();
    }

}
