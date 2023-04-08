package com.banner.controller;


import com.banner.dto.ProfessionDto;
import com.banner.dto.ProfessionGetDto;
import com.banner.dto.ProfessionInfoGetDto;
import com.banner.po.Profession;
import com.banner.po.ProfessionInfo;
import com.banner.service.ProfessionService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 专业表 前端控制器
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/profession")
public class ProfessionController {

    @Resource
    private ProfessionService professionService;



    @GetMapping
    public R<List<ProfessionDto>> getProfession(ProfessionGetDto professionGetDto){
        return professionService.getProfession(professionGetDto);
    }

    @GetMapping("/info")
    public R<ProfessionInfo> getProfessionInfo(@Validated ProfessionInfoGetDto professionInfoGetDto){
        return professionService.getProfessionInfo(professionInfoGetDto);
    }

}
