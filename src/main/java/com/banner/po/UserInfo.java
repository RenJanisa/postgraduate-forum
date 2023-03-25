package com.banner.po;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private String school;

    /**
     * 用户所读专业
     */
    private String profession;

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
