package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author rjj
 * @date 2023/4/21 - 10:10
 */
@Data
public class FollowAddDto {
    @NotBlank(message = "关注目标不为空")
    private String otherId;
    @NotNull(message = "状态不为空")
    private Integer isHave;

    private String address;
}


