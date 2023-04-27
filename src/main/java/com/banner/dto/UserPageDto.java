package com.banner.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author rjj
 * @date 2023/4/22 - 15:52
 */
@Data
public class UserPageDto {

    /**
     * 用户id
     */
    private Long id;

    private String userName;

    private Integer sex;

    private Integer age;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;



}
