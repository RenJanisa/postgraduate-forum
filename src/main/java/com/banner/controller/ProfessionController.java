package com.banner.controller;


import com.banner.dto.*;
import com.banner.po.Profession;
import com.banner.po.ProfessionCategories;
import com.banner.po.ProfessionInfo;
import com.banner.service.ProfessionService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/parent")
    public R<List<ProfessionCategories>> getProfessionParent(String institutionId, String flag) {
        return professionService.getProfessionParent(institutionId, flag);
    }

    @GetMapping
    public R<List<ProfessionSimpleDto>> getProfession(ProfessionGetDto professionGetDto) {
        return professionService.getProfession(professionGetDto);
    }

    @GetMapping("/relation")
    public R<List<ProfessionRelationDto>> getProfessionRelation(String professionId){
        return professionService.getProfessionRelation(professionId);
    }

    @GetMapping("/info")
    public R<ProfessionInfo> getProfessionInfo(@Validated ProfessionInfoGetDto professionInfoGetDto) {
        return professionService.getProfessionInfo(professionInfoGetDto);
    }

    @PostMapping
    public R<String> addProfession(@RequestBody @Validated Profession profession){
        return professionService.addProfession(profession);
    }

    @PostMapping("/institution")
    public R<String> addRelation(@RequestBody @Validated ProfessionInstitutionDto professionInstitutionDto){
        return professionService.addRelation(professionInstitutionDto);
    }

    @PostMapping("/info")
    public R<String> addPostInfo(@RequestBody @Validated ProfessionInfo professionInfo){
        return professionService.addProfessionInfo(professionInfo);
    }

    @PutMapping
    public R<String> updatePost(@RequestBody @Validated Profession profession){
        return professionService.updateProfession(profession);
    }

    @PutMapping("/institution")
    public R<String> updateRelation(@RequestBody @Validated ProfessionInstitutionDto professionInstitutionDto){
        return professionService.updateRelation(professionInstitutionDto);
    }

    @PutMapping("/info")
    public R<String> updatePostInfo(@RequestBody @Validated ProfessionInfo professionInfo){
        return professionService.updateProfessionInfo(professionInfo);
    }

    @DeleteMapping
    public R<String> deleteAll(String id, Integer flag){
        return professionService.deleteAll(id,flag);
    }

}
