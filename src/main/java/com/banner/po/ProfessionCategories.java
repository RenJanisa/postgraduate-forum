package com.banner.po;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    private static final long serialVersionUID = 1L;

    /**
     * 专业分类id
     */
    private Long id;

    /**
     * 专业名称
     */
    private String professionCategoriesName;

    /**
     * 专业分类的描述
     */
    private String description;

    /**
     * 专业分类的类别，0:专硕 1:学硕
     */
    private Integer flag;


}
