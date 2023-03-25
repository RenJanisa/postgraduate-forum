package com.banner.po;

import java.time.LocalDate;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户项目经历表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目内容
     */
    private String projectContent;

    /**
     * 项目成果
     */
    private String projectResults;

    /**
     * 项目完成时间
     */
    private LocalDateTime finishTime;


}
