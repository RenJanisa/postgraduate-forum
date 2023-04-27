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
    @Select("select p.name from post_category p where p.id in (select category_id from post_categories_relation where post_id = #{postId} )")
    List<String> getCategories(String postId);
}
