package com.banner.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author rjj
 * @date 2023/4/1 - 21:00
 */
@Data
public class ProfessionGetDto {
    private String professionId;
    //一级分类传0,二级分类传1
    private Integer flag;
    private String institutionId;
    private String professionName;
}
