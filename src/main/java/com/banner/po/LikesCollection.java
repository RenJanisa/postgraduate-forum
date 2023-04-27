package com.banner.po;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 赞和收藏表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LikesCollection implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 执行操作用户id
     */
    private Long mId;

    /**
     * 点赞或收藏目标对应的id
     */
    private Long postId;

    private Long commentId;

    /**
     * 用户操作的时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime;

    /**
     * 该操作的类型;0表示赞，1表示收藏
     */
    private Integer type;

}
