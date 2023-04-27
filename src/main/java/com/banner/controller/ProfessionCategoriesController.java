package com.banner.controller;


import com.banner.po.PostCategoriesRelation;
import com.banner.po.ProfessionCategories;
import com.banner.service.ProfessionCategoriesService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 专业分类表 前端控制器
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/profession-categories")
public class ProfessionCategoriesController {

    @Resource
    private ProfessionCategoriesService professionCategoriesService;

    @PostMapping
    public R<String> addProfessionCategory(@Validated @RequestBody ProfessionCategories professionCategories){
        return professionCategoriesService.addProfessionCategory(professionCategories);
    }

    @PutMapping
    public R<String> updateProfessionCategory(@Validated @RequestBody ProfessionCategories professionCategories){
        return professionCategoriesService.updateProfessionCategory(professionCategories);
    }


}
