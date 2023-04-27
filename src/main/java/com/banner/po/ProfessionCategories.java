package com.banner.po;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 专业分类表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProfessionCategories implements Serializable {


    /**
     * 专业分类id
     */
    private Long id;

    /**
     * 专业名称
     */
    @NotBlank(message = "专业分类名不为空")
    private String professionCategoriesName;

    /**
     * 专业分类的描述
     */
    private String description;

    /**
     * 专业分类的类别，0:学硕 ; 1:专硕
     */
    @Min(value = 0)
    @Max(value = 1)
    @NotNull
    private Integer flag;


}
