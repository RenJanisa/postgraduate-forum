package com.banner.dto;

import com.banner.po.Institution;
import lombok.Data;

/**
 * @author rjj
 * @date 2023/3/29 - 10:42
 */
@Data
public class InstitutionDto extends Institution {
    private String cname;
}
