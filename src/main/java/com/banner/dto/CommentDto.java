package com.banner.dto;

import com.banner.po.Comments;
import lombok.Data;

import java.util.List;

/**
 * @author rjj
 * @date 2023/4/8 - 19:44
 */
@Data
public class CommentDto extends Comments {
    private String avatar;
    private String userName;
    private List<ReplyDto> reply;

}
