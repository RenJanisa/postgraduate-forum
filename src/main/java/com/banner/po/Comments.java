package com.banner.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Comments implements Serializable {


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

    public Comments(Long userId,String content, Long postId){
        this.userId = userId;
        this.content = content;
        this.postId = postId;
    }

}
