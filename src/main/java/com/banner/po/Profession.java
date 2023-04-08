package com.banner.po;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 专业表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Profession implements Serializable {

    /**
     * 专业id
     */
    private Long id;

    private Long categoryId;

    /**
     * 专业名称
     */
    private String professionName;

    /**
     * 专业描述
     */
    private String description;

    /**
     * 专业代码
     */
    private String code;




}
