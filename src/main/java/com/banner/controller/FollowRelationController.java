package com.banner.controller;


import com.banner.dto.FollowAddDto;
import com.banner.dto.FollowUserDto;
import com.banner.service.FollowRelationService;
import com.banner.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 关注关系表 前端控制器
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/follow-relation")
public class FollowRelationController {

    @Resource
    private FollowRelationService followRelationService;


    @GetMapping
    public R<List<FollowUserDto>> getFollow(Integer flag) {
        return followRelationService.getFollow(flag);
    }

    @GetMapping("/isHave")
    public R<Integer> getIsHave(String otherId) {
        return followRelationService.getIsHave(otherId);
    }

    @PostMapping
    public R<Integer> addFollow(FollowAddDto followAddDto) {
        return followRelationService.addFollow(followAddDto);
    }


}
