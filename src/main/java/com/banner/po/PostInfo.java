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
 * 帖子信息表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PostInfo implements Serializable {


    /**
     * 帖子id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    private String img;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 帖子状态;2表示未审核，1表示已审核,0 草稿
     */
    private Integer status;

    /**
     * 帖子的简单摘要
     */
    private String digest;



}
