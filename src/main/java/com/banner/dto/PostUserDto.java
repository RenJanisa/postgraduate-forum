package com.banner.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author rjj
 * @date 2023/4/23 - 18:46
 */
@Data
public class PostUserDto {
    /**
     * 帖子id
     */
    private Long id;

    private String img;

    /**
     * 标题
     */
    private String title;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 帖子的简单摘要
     */
    private String digest;

    private Integer status;

}
