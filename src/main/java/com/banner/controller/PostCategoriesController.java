package com.banner.controller;

import com.banner.po.PostCategory;
import com.banner.service.PostCategoriesService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rjj
 * @date 2023/3/25 - 8:33
 */
@RestController
@RequestMapping("/post-categories")
public class PostCategoriesController {

    @Resource
    private PostCategoriesService postCategoriesService;

    @GetMapping
    public R<List<String>> getPostCategories(String postId){
        return postCategoriesService.getPostCategories(postId);
    }

    @GetMapping("/all")
    public R<List<PostCategory>> getAllCategories(String categoryName){
        return postCategoriesService.getAllCategories(categoryName);
    }

    @PostMapping
    public R<String> addPostCategory(@Validated PostCategory postCategory){
        return postCategoriesService.addPostCategory(postCategory);
    }

}
