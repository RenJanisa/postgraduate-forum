package com.banner.service.impl;

import cn.hutool.core.util.StrUtil;
import com.banner.mapper.PostCategoriesMapper;
import com.banner.mapper.PostCategoriesRelationMapper;
import com.banner.po.PostCategory;
import com.banner.service.PostCategoriesService;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rjj
 * @date 2023/3/25 - 9:27
 */
@Service
@Slf4j
public class PostCategoriesServiceImpl extends ServiceImpl<PostCategoriesMapper, PostCategory> implements PostCategoriesService {

    @Resource
    private PostCategoriesMapper postCategoriesMapper;

    @Override
    public R<List<String>> getPostCategories(String postId) {

        List<String> postCategoriesList = postCategoriesMapper.getCategories(postId);
        return R.success(postCategoriesList);
    }

    @Override
    public R<List<PostCategory>> getAllCategories(String categoryName) {
        LambdaQueryWrapper<PostCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(categoryName),PostCategory::getName,categoryName);
        queryWrapper.last("limit 5");
        List<PostCategory> postCategories = postCategoriesMapper.selectList(queryWrapper);
        return R.success(postCategories);
    }

    @Override
    public R<String> addPostCategory(PostCategory postCategory) {

        LambdaQueryWrapper<PostCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostCategory::getName,postCategory.getName());
        Integer count = postCategoriesMapper.selectCount(queryWrapper);
        if (count > 0) return R.error("已存在!");

        postCategoriesMapper.insert(postCategory);
        return R.success("添加成功");
    }
}
