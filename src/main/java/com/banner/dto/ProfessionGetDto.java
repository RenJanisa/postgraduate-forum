package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author rjj
 * @date 2023/4/1 - 21:00
 */
@Data
public class ProfessionGetDto {
    private String categoryId;
    private String professionName;
}
