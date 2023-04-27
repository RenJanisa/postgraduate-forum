package com.banner.dto;

import com.banner.po.Profession;
import lombok.Data;

/**
 * @author rjj
 * @date 2023/4/22 - 17:21
 */
@Data
public class ProfessionSimpleDto extends Profession {

    private String professionCategoriesName;
    private String categoryDescription;
    /**
     * 专业分类的类别，0:专硕 1:学硕
     */
    private Integer flag;
}
