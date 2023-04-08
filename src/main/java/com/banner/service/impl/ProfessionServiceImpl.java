package com.banner.service.impl;

import cn.hutool.core.util.StrUtil;
import com.banner.dto.ProfessionDto;
import com.banner.dto.ProfessionGetDto;
import com.banner.dto.ProfessionInfoGetDto;
import com.banner.mapper.ProfessionInfoMapper;
import com.banner.mapper.ProfessionMapper;
import com.banner.po.Profession;
import com.banner.po.ProfessionInfo;
import com.banner.service.ProfessionService;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public R<List<ProfessionDto>> getProfession(ProfessionGetDto professionGetDto) {
        String professionName = professionGetDto.getProfessionName();
        String professionId = professionGetDto.getProfessionId();
        String institutionId = professionGetDto.getInstitutionId();
        Integer flag = professionGetDto.getFlag();
        List<ProfessionDto> professionDtos;
        if (StrUtil.isNotBlank(professionName)) {
            professionDtos = professionMapper.getProfessionByName("%" + professionName + "%");
            return R.success(professionDtos);
        }
        if (StrUtil.isNotBlank(institutionId)){
            professionDtos =  professionMapper.getProfessionByInstitutionId(institutionId);
            return R.success(professionDtos);
        }
        professionDtos = professionMapper.getProfessionByCategoryId(professionId,flag);
        return R.success(professionDtos);

    }

    @Override
    public R<ProfessionInfo> getProfessionInfo(ProfessionInfoGetDto professionInfoGetDto) {
        ProfessionInfo professionInfo = professionInfoMapper.getProfessionInfo(professionInfoGetDto.getInstitutionId(),professionInfoGetDto.getProfessionId());
        return R.success(professionInfo);
    }
}
