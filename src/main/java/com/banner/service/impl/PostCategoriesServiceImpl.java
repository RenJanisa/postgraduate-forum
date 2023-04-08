package com.banner.service.impl;

import com.banner.mapper.PostCategoriesMapper;
import com.banner.mapper.PostCategoriesRelationMapper;
import com.banner.po.PostCategory;
import com.banner.service.PostCategoriesService;
import com.banner.utils.R;
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
    @Resource
    private PostCategoriesRelationMapper postCategoriesRelationMapper;

    @Override
    public R<List<String>> getPostCategories(String postId) {


        List<String> postCategoriesIds = postCategoriesRelationMapper.getCategoriesId(postId);
        List<String> postCategoriesList = new ArrayList<>();

        for (String postCategoriesId : postCategoriesIds) {
            String postCategoriesName = postCategoriesMapper.getCategories(postCategoriesId);
            postCategoriesList.add(postCategoriesName);
        }

        return R.success(postCategoriesList);
    }
}
