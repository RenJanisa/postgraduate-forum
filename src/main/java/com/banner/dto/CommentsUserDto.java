package com.banner.dto;

import com.banner.po.Comments;
import lombok.Data;

/**
 * @author rjj
 * @date 2023/4/20 - 12:57
 */
@Data
public class CommentsUserDto extends Comments {
    private String title;
    private String img;
    private String digest;
}
