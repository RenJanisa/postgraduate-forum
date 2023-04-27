package com.banner.dto;

import lombok.Data;

/**
 * @author rjj
 * @date 2023/4/20 - 14:45
 */
@Data
public class FollowUserDto {

    private Long id;
    private String avatar;
    private String userName;
    private String address;
    private String createTime;

}
