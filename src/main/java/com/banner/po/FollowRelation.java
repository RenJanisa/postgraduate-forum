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
 * 关注关系表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FollowRelation implements Serializable {

    /**
     * 要进行关注操作的用户id
     */
    private Long userId;

    /**
     * 关注对象id
     */
    private Long objId;

    /**
     * 关注的地址
     */
    private String address;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public FollowRelation(Long userId, Long objId, String address) {
        this.userId = userId;
        this.objId = objId;
        this.address = address;
    }
}
