package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author rjj
 * @date 2023/4/8 - 21:43
 */
@Data
public class CommentAddDto {


    private String otherId;

    /**
     * 关联对象id
     */
    @NotBlank(message = "对象不为空")
    private String objId;

    /**
     * 评论内容
     */
    @NotBlank(message = "内容不为空")
    private String content;

    /**
     * 区分是回复一级评论还是回复
     */
    private Integer flag;
}
