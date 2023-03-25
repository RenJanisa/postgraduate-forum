package com.banner.controller;


import com.banner.dto.PostDto;
import com.banner.service.PostInfoService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 帖子信息表 前端控制器
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/post-info")
public class PostInfoController {

    @Resource
    private PostInfoService postInfoService;

    @GetMapping
    public R<PostDto> getPost(String postId){
        return postInfoService.getPost(postId);
    }
}
