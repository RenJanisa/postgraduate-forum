package com.banner.po;

import lombok.Data;

/**
 * @author rjj
 * @date 2023/3/11 - 9:55
 */
@Data
public class Place {
    private int id;
    private int parentId;
    private String cname;
    private int ctype;
}
