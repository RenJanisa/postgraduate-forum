package com.banner.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author rjj
 * @date 2023/3/11 - 9:53
 */
@Data
@TableName("post_category")
public class PostCategory {

    /**
     * 主键
     */
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类标签默认和名称一样
     */
    private String label;

    /**
     * 父结点id（第一级的父节点是0，自关联字段id）
     */
    private String parentid;

    /**
     * 排序字段
     */
    private Integer orderby;

    /**
     * 是否叶子
     */
    private Integer isLeaf;


}
