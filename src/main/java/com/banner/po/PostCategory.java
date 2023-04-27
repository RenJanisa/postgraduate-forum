package com.banner.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "分类名不为空")
    private String name;

    /**
     * 分类标签默认和名称一样
     */
    private String label;


}
