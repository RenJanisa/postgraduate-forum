package com.banner.dto;

import com.banner.po.Reply;
import lombok.Data;

/**
 * @author rjj
 * @date 2023/4/8 - 19:47
 */
@Data
public class ReplyDto extends Reply {
    private String avatar;
    private String userName;
    private String otherName;
    private String otherAvatar;
}
