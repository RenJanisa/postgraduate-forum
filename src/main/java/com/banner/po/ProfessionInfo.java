package com.banner.po;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author rjj
 * @date 2023/4/8 - 15:42
 */
@Data
public class ProfessionInfo {

    private Long id;

    /**
     * 学院id
     */
    @NotNull
    private Long institutionId;

    /**
     * 专业id
     */
    @NotNull
    private Long professionId;
    /**
     * 初始科目
     */
    @NotNull
    private String initialExamination;

    /**
     * 复试科目
     */
    @NotNull
    private String reExamination;

    /**
     * 同等学力加试科目
     */
    private String additionalTest;
    /**
     * 拟招生人数
     */
    @NotNull
    private Integer enrollmentsNumber;

    /**
     * 创建时间
     */
    @Size(max = 4,message = "年份格式错误")
    private String createTime;


}
