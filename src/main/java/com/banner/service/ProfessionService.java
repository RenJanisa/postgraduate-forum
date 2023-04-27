package com.banner.service;

import com.banner.dto.*;
import com.banner.po.Profession;
import com.banner.po.ProfessionCategories;
import com.banner.po.ProfessionInfo;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 专业表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface ProfessionService extends IService<Profession> {

    R<List<ProfessionSimpleDto>> getProfession(ProfessionGetDto professionGetDto);

    R<ProfessionInfo> getProfessionInfo(ProfessionInfoGetDto professionInfoGetDto);

    R<List<ProfessionCategories>> getProfessionParent(String institutionId, String flag);

    R<PageDto<ProfessionSimpleDto>> getProfessionPage(PageGetDto pageGetDto);

    R<String> addProfession(Profession profession);

    R<String> addRelation(ProfessionInstitutionDto professionInstitutionDto);

    R<String> addProfessionInfo(ProfessionInfo professionInfo);

    R<List<ProfessionRelationDto>> getProfessionRelation(String professionId);

    R<String> updateProfession(Profession profession);

    R<String> updateRelation(ProfessionInstitutionDto professionInstitutionDto);

    R<String> updateProfessionInfo(ProfessionInfo professionInfo);

    R<String> deleteAll(String id, Integer flag);
}
