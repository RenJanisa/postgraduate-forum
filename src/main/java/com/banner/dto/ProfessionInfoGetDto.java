package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author rjj
 * @date 2023/4/8 - 16:56
 */
@Data
public class ProfessionInfoGetDto {
    /**
     * 学院id
     */
    @NotBlank(message = "学院id不为空")
    private String institutionId;

    /**
     * 专业id
     */
    @NotBlank(message = "专业id不为空")
    private String professionId;
}
