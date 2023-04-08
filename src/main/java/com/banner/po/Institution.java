package com.banner.po;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotEmpty(message = "院校名不为空")
    private String institutionName;

    /**
     * 该院校所属地区
     */
    @NotNull(message = "未指定院校地区")
    private Integer area;

    /**
     * 院校所隶属的部门，例:教育部...
     */
    private String dependent;

    /**
     * 院校所具有的特性
     */
    private String peculiarity;

    /**
     * 院校官网的网址
     */
    @NotEmpty(message = "官网地址不为空")
    private String institutionUrl;

    public Institution(Long id,String institutionName,String institutionUrl){
        this.id = id;
        this.institutionName = institutionName;
        this.institutionUrl = institutionUrl;
    }

}
