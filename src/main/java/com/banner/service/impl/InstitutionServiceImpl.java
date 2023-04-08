package com.banner.service.impl;


import cn.hutool.core.util.StrUtil;
import com.banner.dto.InstitutionDto;
import com.banner.dto.InstitutionGetDto;
import com.banner.dto.PlaceDto;
import com.banner.mapper.InstitutionMapper;
import com.banner.mapper.PlaceMapper;
import com.banner.po.Institution;
import com.banner.po.Place;
import com.banner.service.InstitutionService;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kotlin.jvm.internal.Lambda;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 院校信息表 服务实现类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Service
public class InstitutionServiceImpl extends ServiceImpl<InstitutionMapper, Institution> implements InstitutionService {

    @Resource
    private PlaceMapper placeMapper;
    @Resource
    private InstitutionMapper institutionMapper;

    @Override
    public R<List<PlaceDto>> getPlace(String placeId) {
        List<PlaceDto> placeDtos;
        if (StrUtil.isBlank(placeId)) {
            //查询省份
             placeDtos = placeMapper.getOnePlace();
            return R.success(placeDtos);
        }
        //查询下级地区
        placeDtos = placeMapper.getById(placeId);
        return R.success(placeDtos);
    }

    @Override
    public R<List<InstitutionDto>> getInstitution(InstitutionGetDto institutionGet) {
        String placeId = institutionGet.getPlaceId();
        String institutionName = institutionGet.getInstitutionName();
        String institutionId = institutionGet.getInstitutionId();
        List<InstitutionDto> institutions;
        if(StrUtil.isNotBlank(institutionName)){
            //关键字搜索
             institutions = institutionMapper.getLikeName("%"+institutionName+"%");
            return R.success(institutions);
        }
        if (StrUtil.isNotBlank(institutionId)){
            institutions = institutionMapper.getById(institutionId);
            return R.success(institutions);
        }
        //根据地区id搜索
        institutions = institutionMapper.getByPlaceId(placeId);
        return R.success(institutions);
    }

    @Override
    @Transactional
    public R<String> addInstitution(Institution institution) {
        int save = institutionMapper.insert(institution);
        if (save <= 0) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);
        return R.success("添加成功!");
    }

    @Override
    @Transactional
    public R<String> updateInstitution(Institution institution) {
        int update = institutionMapper.updateById(institution);
        if (update <= 0) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);
        return R.success("修改成功!");
    }

    @Override
    @Transactional
    public R<String> deleteInstitution(String institutionId) {
        if (StrUtil.isBlank(institutionId)) PostgraduateForumException.error(CommonError.PARAMS_ERROR);
        String[] postList = institutionId.split(",");
        int delete = institutionMapper.deleteBatchIds(Arrays.asList(postList));
        if (delete <= 0) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);
        return R.success("删除成功");
    }
}
