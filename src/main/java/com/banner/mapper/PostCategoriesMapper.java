package com.banner.mapper;

import com.banner.po.PostCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author rjj
 * @date 2023/3/25 - 9:31
 */
public interface PostCategoriesMapper extends BaseMapper<PostCategory> {
    @Select("select name from post_category where id = #{postCategoriesId}")
    String getCategories(String postCategoriesId);
}
