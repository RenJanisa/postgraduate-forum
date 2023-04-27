package com.banner.dto;

import com.banner.po.UserInfo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author rjj
 * @date 2023/3/21 - 20:26
 */
@Data
public class UserInfoDto extends UserInfo implements Serializable {

    private String institutionName;
    private String professionName;
    /**
     * 目标院校名称
     */
    private String goalName;
}
