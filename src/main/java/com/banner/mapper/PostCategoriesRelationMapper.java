package com.banner.mapper;

import com.banner.po.PostCategoriesRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author rjj
 * @date 2023/3/25 - 9:43
 */
public interface PostCategoriesRelationMapper extends BaseMapper<PostCategoriesRelation> {
    @Select("select category_id from post_categories_relation where post_id = #{postId}")
    List<String> getCategoriesId(String postId);

    @Insert("insert into post_categories_relation values (#{postId},#{postCategoryId})")
    void addRelation(Long postId, String postCategoryId);
}
