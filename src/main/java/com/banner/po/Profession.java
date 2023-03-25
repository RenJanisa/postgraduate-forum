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

    private static final long serialVersionUID = 1L;

    /**
     * 专业id
     */
    private Long id;

    /**
     * 学院id
     */
    private Long institutionId;

    /**
     * 专业名称
     */
    private String professionName;

    /**
     * 专业分类的描述
     */
    private String description;

    /**
     * 招生人数
     */
    private Integer studentEnrollment;

    /**
     * 报考人数
     */
    private Integer studentCandidate;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
