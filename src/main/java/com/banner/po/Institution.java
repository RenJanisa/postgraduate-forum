package com.banner.po;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 院校信息表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Institution implements Serializable {


    /**
     * 学院id
     */
    private Long id;

    /**
     * 院校名称
     */
    private String institutionName;

    /**
     * 该院校所属地区
     */
    private Integer area;

    /**
     * 院校所代表的编码
     */
    private Integer number;

    /**
     * 院校所隶属的部门，例:教育部...
     */
    private String dependent;

    /**
     * 院校所具有的特性
     */
    private String peculiarity;

    /**
     * 院校的一些情况的介绍
     */
    private String description;

    /**
     * 院校官网的网址
     */
    private String institutionUrl;

    public Institution(String institutionName,String institutionUrl){
        this.institutionName = institutionName;
        this.institutionUrl = institutionUrl;
    }

}
