package com.banner.controller;


import com.banner.dto.BorderlineDto;
import com.banner.po.Borderline;
import com.banner.service.BorderlineService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 分数线统计表 前端控制器
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/borderline")
public class BorderlineController {

    @Resource
    private BorderlineService borderlineService;

    @GetMapping
    public R<BorderlineDto> getBorderline(String categoryId){
        return borderlineService.getBorderline(categoryId);
    }

    @PostMapping
    public R<String> addBorderline(@RequestBody @Validated Borderline borderline){
        return borderlineService.addBorderline(borderline);
    }

    @PutMapping
    public R<String> updateBorderline(@RequestBody @Validated Borderline borderline){
        return borderlineService.updateBorderline(borderline);
    }

}
