package com.banner.po;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 评论回复表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Reply implements Serializable {


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
     * 回复对象id
     */
    private Long otherId;

    /**
     * 区分是回复一级评论还是回复
     */
    private Integer flag;


    /**
     * 回复内容
     */
    private String content;

    /**
     * 回复时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public Reply(Long userId,String content, Long commentId, Integer flag, Long otherId){
        this.userId = userId;
        this.commentId = commentId;
        this.content = content;
        this.flag = flag;
        this.otherId = otherId;
    }

}
