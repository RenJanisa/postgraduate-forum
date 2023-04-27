package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author rjj
 * @date 2023/4/10 - 20:50
 */
@Data
public class CommentDeleteDto {

    @NotBlank(message = "删除评论或回复的id")
    private String id;
    /**
     * 0 评论; 1 回复
     */
    @NotNull(message = "删除类型不为空")
    private Integer flag;

}
