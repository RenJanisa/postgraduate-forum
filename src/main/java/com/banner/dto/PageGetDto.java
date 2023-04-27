package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author rjj
 * @date 2023/4/22 - 16:42
 */
@Data
public class PageGetDto {

    @NotBlank(message = "非法参数")
    private Integer page;
    @NotBlank(message = "非法参数")
    private Integer pageSize;

    private String name;

}
