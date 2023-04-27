package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * @author rjj
 * @date 2023/3/25 - 11:19
 */
@Data
public class PostCompleteDto {

    /**
     * 帖子id
     */
    private Long id;
    /**
     * 帖子状态;0表示已审核 1表示未审核，2表示草稿
     */
    private Integer status;

    /**
     * 帖子的简单摘要
     */
    @Size(max = 256,message = "最多256个字")
    private String digest;

    private String img;

    private String postCategoryId;



}
