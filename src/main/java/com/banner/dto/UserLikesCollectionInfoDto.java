package com.banner.dto;

import com.banner.po.LikesCollection;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author rjj
 * @date 2023/4/15 - 19:27
 */
@Data
public class UserLikesCollectionInfoDto extends LikesCollection {

    private String title;
    private Long otherId;
    private String otherName;
    private String otherAvatar;
    private String content;
}
