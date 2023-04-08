package com.banner.controller;


import com.banner.dto.PostAddDto;
import com.banner.dto.PostCompleteDto;
import com.banner.dto.PostDto;
import com.banner.service.PostInfoService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public R<Long> addPost(@RequestBody @Validated PostAddDto postAddDto){
        return postInfoService.addPost(postAddDto);
    }

    @PutMapping
    public R<String> completePost(@RequestBody @Validated PostCompleteDto postCompleteDto){
        return postInfoService.completePost(postCompleteDto);
    }

    @DeleteMapping
    public R<String> deletePost(String postId){
        return postInfoService.deletePost(postId);
    }


}
