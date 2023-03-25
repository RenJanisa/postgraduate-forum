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
 * 评论回复表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 回复id
     */
    private Long id;

    /**
     * 回复目标评论id
     */
    private Long commentId;

    /**
     * 发布该回复用户id
     */
    private Long userId;

    /**
     * 点赞数量
     */
    private Long likeCount;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 回复时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
