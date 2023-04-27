package com.banner.po;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements Serializable {


    private Long id;

    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不为空")
    private String userName;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 用户头像路径
     */
    private String avatar;

    /**
     * 用户个性签名
     */
    private String signature;

    /**
     * 用户所在学校
     */
    private Long institutionId;

    /**
     * 用户所读专业
     */
    private Long professionId;

    /**
     * 用户年级
     */
    private String grade;

    /**
     * 目标院校id
     */
    private Long goalId;

    /**
     * 用户性别
     */
    @Max(value = 1,message = "非法type")
    @Min(value = 0,message = "非法type")
    private Integer sex;

    /**
     * 用户年龄
     */
    private Integer age;

    public UserInfo(Long userId, String userName, String avatar) {
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
    }

}
