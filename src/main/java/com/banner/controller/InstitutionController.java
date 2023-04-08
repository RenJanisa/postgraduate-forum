package com.banner.controller;


import com.banner.dto.InstitutionDto;
import com.banner.dto.InstitutionGetDto;
import com.banner.dto.PlaceDto;
import com.banner.po.Institution;
import com.banner.service.InstitutionService;
import com.banner.utils.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 院校信息表 前端控制器
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/institution")
public class InstitutionController {

    @Resource
    private InstitutionService institutionService;


    @GetMapping("/place")
    public R<List<PlaceDto>> getPlace(String placeId){
        return institutionService.getPlace(placeId);
    }

    @GetMapping
    public R<List<InstitutionDto>> getInstitution(InstitutionGetDto institutionGet){
        return institutionService.getInstitution(institutionGet);
    }
    @PostMapping
    public R<String> addInstitution(@RequestBody @Validated Institution institution){
        return institutionService.addInstitution(institution);
    }

    @PutMapping
    public R<String> updateInstitution(@RequestBody @Validated Institution institution){
        return institutionService.updateInstitution(institution);
    }

    @DeleteMapping
    public R<String> deleteInstitutionId(String institutionId){
        return institutionService.deleteInstitution(institutionId);
    }

}
