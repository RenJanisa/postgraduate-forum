package com.banner.po;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
     * 学院id
     */
    private Long institutionId;

    /**
     * 专业id
     */
    private Long professionId;

    /**
     * 公布分数线的年份
     */
    private LocalDate year;

    /**
     * 总分数线
     */
    private Integer totalScores;

    /**
     * 单科分数超过100的单科分数线
     */
    private Integer bigScores;

    /**
     * 单科分数为100的单科分数线
     */
    private Integer smallScores;

    /**
     * 是否为自划线院校的专业分类 0:是 1:不是
     */
    private Integer selfScore;


}
