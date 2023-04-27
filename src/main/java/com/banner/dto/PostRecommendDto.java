package com.banner.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author rjj
 * @date 2023/4/21 - 18:57
 */
@Data
public class PostRecommendDto {
    /**
     * 帖子id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    private String userName;

    private String avatar;

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

}
