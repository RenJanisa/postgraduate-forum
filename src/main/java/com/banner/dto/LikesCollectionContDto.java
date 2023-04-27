package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author rjj
 * @date 2023/4/11 - 21:28
 */
@Data
public class LikesCollectionContDto {

    @NotBlank(message = "目标不为空")
    private String id;

    /**
     * 该操作的类型;0表示赞，1表示收藏
     */
    @Max(value = 1,message = "非法type")
    @Min(value = 0,message = "非法type")
    @NotNull(message = "非法type")
    private Integer type;

    /**
     * 查询文章(0),评论(1)点赞
     */
    @Max(value = 1, message = "非法flag")
    @Min(value = 0,message = "非法flag")
    @NotNull(message = "非法type")
    private Integer flag;

}
