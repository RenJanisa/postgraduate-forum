package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author rjj
 * @date 2023/3/25 - 9:37
 */
@Data
public class PostAddDto {

    /**
     * 标题
     */
    @Size(min = 5,max = 100,message = "标题在5~100字")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不为空")
    private String content;

}
