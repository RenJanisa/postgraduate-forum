package com.banner.mapper;

import com.banner.dto.ProfessionDto;
import com.banner.po.Profession;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 专业表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface ProfessionMapper extends BaseMapper<Profession> {

    List<ProfessionDto> getProfessionByName(@Param("professionName") String professionName);

    List<ProfessionDto> getProfessionByCategoryId(@Param("professionId") String professionId, @Param("flag") int flag);

    List<ProfessionDto> getProfessionByInstitutionId(@Param("institutionId") String institutionId);
}
