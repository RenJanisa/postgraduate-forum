package com.banner.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author rjj
 * @date 2023/3/21 - 20:26
 */
@Data
public class UserInfoDto implements Serializable {

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
     * 目标院校名称
     */
    private String institutionName;

    /**
     * 院校官网的网址
     */
    private String institutionUrl;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 用户年龄
     */
    private Integer age;
}
