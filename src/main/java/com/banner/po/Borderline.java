package com.banner.po;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Year;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 分数线统计表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Borderline implements Serializable {


    /**
     * 分数线id
     */
    private Long id;


    /**
     * 专业id
     */
    private Long professionId;

    /**
     * 总分数线
     */
    private Integer atotalScore;

    /**
     * 单科分数超过100的单科分数线
     */
    private Integer abigScores;

    /**
     * 单科分数为100的单科分数线
     */
    private Integer asmallScores;


    /**
     * 总分数线
     */
    private Integer btotalScore ;

    /**
     * 单科分数超过100的单科分数线
     */
    private Integer bbigScores;

    /**
     * 单科分数为100的单科分数线
     */
    private Integer bsmallScores;

    /**
     * 创建时间
     */
    private String createTime;


}
