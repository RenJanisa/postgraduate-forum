package com.banner.service.impl;

import cn.hutool.core.util.StrUtil;
import com.banner.dto.*;
import com.banner.mapper.BorderlineMapper;
import com.banner.mapper.ProfessionCategoriesMapper;
import com.banner.mapper.ProfessionInfoMapper;
import com.banner.mapper.ProfessionMapper;
import com.banner.po.Profession;
import com.banner.po.ProfessionCategories;
import com.banner.po.ProfessionInfo;
import com.banner.service.ProfessionService;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javafx.geometry.Pos;
import org.checkerframework.checker.units.qual.C;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 专业表 服务实现类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Service
public class ProfessionServiceImpl extends ServiceImpl<ProfessionMapper, Profession> implements ProfessionService {

    @Resource
    private ProfessionMapper professionMapper;
    @Resource
    private ProfessionInfoMapper professionInfoMapper;

    @Resource
    private ProfessionCategoriesMapper professionCategoriesMapper;
    @Resource
    private BorderlineMapper borderlineMapper;

    @Override
    public R<List<ProfessionSimpleDto>> getProfession(ProfessionGetDto professionGetDto) {
        String professionName = professionGetDto.getProfessionName();
        String categoryId = professionGetDto.getCategoryId();
        List<ProfessionSimpleDto> professionDtos;
        if (StrUtil.isNotBlank(professionName)) {
            //关键字查询
            professionDtos = professionMapper.getProfessionByName("%" + professionName + "%");
            return R.success(professionDtos);
        }
        professionDtos = professionMapper.getProfessionByCategoryId(categoryId);
        return R.success(professionDtos);

    }

    @Override
    public R<ProfessionInfo> getProfessionInfo(ProfessionInfoGetDto professionInfoGetDto) {
        ProfessionInfo professionInfo = professionInfoMapper.getProfessionInfo(professionInfoGetDto.getInstitutionId(), professionInfoGetDto.getProfessionId());
        return R.success(professionInfo);
    }

    @Override
    public R<List<ProfessionCategories>> getProfessionParent(String institutionId, String flag) {

        if (StrUtil.isBlank(flag)) PostgraduateForumException.error(CommonError.PARAMS_ERROR);
        if (StrUtil.isNotBlank(institutionId)) {
            List<ProfessionCategories> professions = professionMapper.getProfessionParentWithInstitutionId(institutionId, flag);
            return R.success(professions);
        }
        List<ProfessionCategories> professions = professionMapper.getProfessionParent(flag);
        return R.success(professions);
    }

    @Override
    public R<PageDto<ProfessionSimpleDto>> getProfessionPage(PageGetDto pageGetDto) {


        Integer page = pageGetDto.getPage();
        Integer pageSize = pageGetDto.getPageSize();
        String professionName = pageGetDto.getName();

        List<ProfessionSimpleDto> professions;
        if (StrUtil.isNotBlank(professionName))
            professions = professionMapper.getProfessionPageWithName((page - 1) * pageSize, pageSize, "%" + professionName + "%");
        else professions = professionMapper.getProfessionPage((page - 1) * pageSize, pageSize);
        PageDto<ProfessionSimpleDto> professionDtoPageDto = new PageDto<>(page, pageSize, professions);
        return R.success(professionDtoPageDto);
    }

    @Override
    public R<String> addProfession(Profession profession) {

        int count = this.count(new LambdaQueryWrapper<Profession>().eq(Profession::getProfessionName, profession.getProfessionName()));
        if (count > 0) PostgraduateForumException.error(500, "已存在");

        boolean save = this.save(profession);
        if (!save) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);

        return R.success("添加成功");

    }

    @Override
    @Transactional
    public R<String> addRelation(ProfessionInstitutionDto professionInstitutionDto) {

        String professionId = professionInstitutionDto.getProfessionId();
        String categoryId = professionInstitutionDto.getCategoryId();
        String institutionId = professionInstitutionDto.getInstitutionId();
        if (StrUtil.isNotBlank(categoryId)) {
            Integer count = professionMapper.isHaveCategoryAndInstitution(institutionId, categoryId);
            if (count > 0) PostgraduateForumException.error(500, "已存在");
            professionMapper.addRelationCategory(institutionId, categoryId);
            return R.success("添加成功");
        }
        if (StrUtil.isBlank(professionId)) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        String description = professionInstitutionDto.getDescription();
        //添加学院与二级专业
        //1.判断该学院是否与该专业建立关系
        Integer count = professionMapper.isHaveProfessionAndInstitution(institutionId, professionId);
        if (count > 0) PostgraduateForumException.error(500, "已存在");
        //2.未建立 判断该专业一级分类是否与该学院建立关系
        String subCategoryId = professionMapper.getCategoryId(professionId);
        Integer have = professionMapper.isHaveCategoryAndInstitution(institutionId, subCategoryId);
        if (have == 0)
            //未建立 建立联系
            professionMapper.addRelationCategory(institutionId, subCategoryId);
        //已建立 建立二级专业与学院关系
        professionMapper.addRelation(institutionId, professionId, description);

        return R.success("添加成功");
    }

    @Override
    public R<String> addProfessionInfo(ProfessionInfo professionInfo) {

        Integer count = professionInfoMapper.selectCount(new LambdaQueryWrapper<ProfessionInfo>()
                .eq(ProfessionInfo::getProfessionId, professionInfo.getProfessionId())
                .eq(ProfessionInfo::getInstitutionId, professionInfo.getInstitutionId()));
        if (count > 0) PostgraduateForumException.error(500, "已存在");

        int insert = professionInfoMapper.insert(professionInfo);
        if (insert <= 0) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);

        return R.success("添加成功");
    }

    @Override
    public R<List<ProfessionRelationDto>> getProfessionRelation(String professionId) {

        if (StrUtil.isBlank(professionId)) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        List<ProfessionRelationDto> professionRelationDtos = professionMapper.getProfessionRelation(professionId);
        return R.success(professionRelationDtos);
    }

    @Override
    public R<String> updateProfession(Profession profession) {

        if (profession.getId() == null) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        boolean update = this.updateById(profession);
        if (!update) PostgraduateForumException.error(500, "该专业不存在");

        return R.success("更新成功!");
    }

    @Override
    public R<String> updateRelation(ProfessionInstitutionDto professionInstitutionDto) {

        String professionId = professionInstitutionDto.getProfessionId();
        String institutionId = professionInstitutionDto.getInstitutionId();

        if (professionId == null) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        boolean update = professionMapper.updateProfessionInstitutionDescription(professionId, institutionId, professionInstitutionDto.getDescription());
        if (!update) PostgraduateForumException.error(500,"对应关系不存在!");

        return R.success("更新成功!");
    }

    @Override
    public R<String> updateProfessionInfo(ProfessionInfo professionInfo) {

        if (professionInfo.getId() == null) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        int update = professionInfoMapper.updateById(professionInfo);

        if (update == 0) PostgraduateForumException.error(500,"不存在!");

        return R.success("更新成功!");
    }

    @Override
    @Transactional
    public R<String> deleteAll(String id, Integer flag) {

        if (StrUtil.isBlank(id) || flag == null) PostgraduateForumException.error(CommonError.REQUEST_NULL);

        int i = 0;
        if(flag == 0) i = professionCategoriesMapper.deleteById(id);
        else if (flag == 1) i = professionMapper.deleteById(id);
        else if (flag == 2) i = professionInfoMapper.deleteById(id);
        else if (flag == 3) i = borderlineMapper.deleteById(id);
        else PostgraduateForumException.error(CommonError.REQUEST_NULL);

        if (i == 0) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);

        return R.success("删除成功");
    }
}
