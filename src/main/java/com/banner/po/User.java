package com.banner.po;

import java.time.LocalDate;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户身份
     */
    private Integer flag;

    public User(Long id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }


}
