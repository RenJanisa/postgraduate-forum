package com.banner.dto;

import com.banner.po.Borderline;
import lombok.Data;

/**
 * @author rjj
 * @date 2023/4/7 - 20:48
 */
@Data
public class BorderlineDto extends Borderline {


    private String description;
    private String professionCategoriesName;
    private Integer flag;

}
