package com.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author rjj
 * @date 2023/4/22 - 8:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {
    private Integer page;
    private Integer pageSize;
    private List<T> content;
}
