package com.banner.service;

import com.banner.dto.InstitutionDto;
import com.banner.dto.InstitutionGetDto;
import com.banner.dto.PlaceDto;
import com.banner.po.Institution;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 院校信息表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface InstitutionService extends IService<Institution> {

    R<List<PlaceDto>> getPlace(String placeId);

    R<List<InstitutionDto>> getInstitution(InstitutionGetDto institutionGet);

    R<String> addInstitution(Institution institution);

    R<String> updateInstitution(Institution institution);

    R<String> deleteInstitution(String institutionId);
}
