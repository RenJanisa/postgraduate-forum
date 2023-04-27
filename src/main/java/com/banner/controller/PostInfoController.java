package com.banner.controller;


import com.banner.dto.*;
import com.banner.service.PostInfoService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public R<Long> addPost(@RequestBody @Validated PostAUDto postAUDto){
        return postInfoService.addPost(postAUDto);
    }

    @PutMapping
    public R<String> completePost(@RequestBody @Validated PostCompleteDto postCompleteDto){
        return postInfoService.completePost(postCompleteDto);
    }

    @PutMapping("/all")
    public R<String> updatePost(@RequestBody @Validated PostAUDto postAUDto){
        return postInfoService.updatePost(postAUDto);
    }

    @DeleteMapping
    public R<String> deletePost(String postId){
        return postInfoService.deletePost(postId);
    }

    @GetMapping("/recommend")
    public R<PageDto<PostRecommendDto>> recommendPost(Integer page, Integer pageSize){
        return  postInfoService.recommendPost(page, pageSize);
    }

    @GetMapping("/user")
    public R<List<PostUserDto>> getUserPost(Integer status){
        return postInfoService.getUserPost(status);
    }


}
