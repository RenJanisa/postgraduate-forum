package com.banner.service;

import com.banner.dto.ProfessionDto;
import com.banner.dto.ProfessionGetDto;
import com.banner.dto.ProfessionInfoGetDto;
import com.banner.po.Profession;
import com.banner.po.ProfessionInfo;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 专业表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface ProfessionService extends IService<Profession> {

    R<List<ProfessionDto>> getProfession(ProfessionGetDto professionGetDto);

    R<ProfessionInfo> getProfessionInfo(ProfessionInfoGetDto professionInfoGetDto);
}
