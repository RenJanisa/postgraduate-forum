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
     * 被操作用户id
     */
    private Long objId;

    /**
     * 执行操作用户id
     */
    private Long mId;

    /**
     * 用户操作的时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime;

    /**
     * 该操作的类型;1表示赞，2表示收藏
     */
    private Integer type;

    /**
     * 帖子对应的id，与帖子表关联起来
     */
    @TableField("postId")
    private Long postid;


}
