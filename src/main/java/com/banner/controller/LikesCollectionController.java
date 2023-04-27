package com.banner.controller;


import com.banner.dto.LikesCollectionContDto;
import com.banner.dto.UserLikesCollectionInfoDto;
import com.banner.service.LikesCollectionService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 赞和收藏表 前端控制器
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/likes-collection")
public class LikesCollectionController {

    @Resource
    private LikesCollectionService likesCollectionService;

    @GetMapping
    public R<Map<String,Integer>> getLikesCollectionCont(@Validated LikesCollectionContDto likesCollectionContDto){
        return likesCollectionService.getLikesCollectionCont(likesCollectionContDto);
    }

    @PostMapping
    public R<String> addLikesCollection(@Validated LikesCollectionContDto likesCollectionContDto){
        return likesCollectionService.addLikesCollection(likesCollectionContDto);
    }

    @GetMapping("/user")
    public R<List<UserLikesCollectionInfoDto>> getUserLikesCollectionInfo(LikesCollectionContDto likesCollectionContDto){
        return likesCollectionService.getUserLikesCollectionInfo(likesCollectionContDto);
    }


}
