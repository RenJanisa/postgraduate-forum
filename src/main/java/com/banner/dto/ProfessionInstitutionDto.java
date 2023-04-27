package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author rjj
 * @date 2023/4/1 - 21:48
 */
@Data
public class ProfessionInstitutionDto {

    private String professionId;
    @NotNull
    private String institutionId;
    private String categoryId;
    private String description;


}
