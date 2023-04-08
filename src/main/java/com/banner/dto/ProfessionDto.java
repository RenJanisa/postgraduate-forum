package com.banner.dto;

import com.banner.po.Profession;
import lombok.Data;

/**
 * @author rjj
 * @date 2023/4/1 - 21:04
 */
@Data
public class ProfessionDto extends Profession {
    private String professionCategoriesName;
    private String categoryDescription;
    private String institutionName;
    private Long institutionId;
    /**
     * 专业分类的类别，0:专硕 1:学硕
     */
    private Integer flag;


}
