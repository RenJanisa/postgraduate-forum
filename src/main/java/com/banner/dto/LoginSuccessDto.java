package com.banner.dto;

import com.banner.po.User;
import lombok.Data;

/**
 * @author rjj
 * @date 2023/3/11 - 16:32
 */
@Data
public class LoginSuccessDto extends User {
    private String token;
    private String userName;
    private String avatar;
    private String institutionId;
    private String institutionName;
}
