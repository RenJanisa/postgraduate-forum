package com.banner.po;

import lombok.Data;

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
    private Long institutionId;

    /**
     * 专业id
     */
    private Long professionId;
    /**
     * 初始科目
     */
    private String initialExamination;

    /**
     * 复试科目
     */
    private String reExamination;

    /**
     * 同等学力加试科目
     */
    private String additionalTest;
    /**
     * 拟招生人数
     */
    private Integer enrollmentsNumber;

    /**
     * 创建时间
     */
    private String createTime;


}
