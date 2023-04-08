package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author rjj
 * @date 2023/3/11 - 15:17
 */
@Data
public class LoginDto {
    @NotEmpty(message = "邮箱不能为空")
    private String email;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "验证码不能为空")
    private String code;
}
