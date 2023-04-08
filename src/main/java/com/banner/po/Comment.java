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
 * 评论表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment implements Serializable {


    /**
     * 评论id
     */
    private Long id;

    /**
     * 要进行评论用户id
     */
    private Long userId;

    /**
     * 关联对象id
     */
    private Long postId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
