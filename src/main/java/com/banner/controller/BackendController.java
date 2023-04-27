package com.banner.controller;

import com.banner.dto.*;
import com.banner.po.User;
import com.banner.service.InstitutionService;
import com.banner.service.PostInfoService;
import com.banner.service.ProfessionService;
import com.banner.service.UserService;
import com.banner.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author rjj
 * @date 2023/4/22 - 10:57
 */
@RestController
@RequestMapping("/backend")
public class BackendController {

    @Resource
    private UserService userService;
    @Resource
    private InstitutionService institutionService;
    @Resource
    private ProfessionService professionService;
    @Resource
    private PostInfoService postInfoService;

    @GetMapping("/user")
    public R<PageDto<UserPageDto>> getUserPage(PageGetDto pageGetDto){
        return userService.getUserPage(pageGetDto);
    }

    @GetMapping("/institution")
    public R<PageDto<InstitutionDto>> getInstitutionPage(PageGetDto pageGetDto){
        return institutionService.getInstitutionPage(pageGetDto);
    }

    @GetMapping("/profession")
    public R<PageDto<ProfessionSimpleDto>> getProfessionPage(PageGetDto pageGetDto){
        return professionService.getProfessionPage(pageGetDto);
    }

    @GetMapping("/post")
    public R<PageDto<PostUserDto>> getPostPage(PageGetDto pageGetDto){
        return postInfoService.getPostPage(pageGetDto);
    }


}
