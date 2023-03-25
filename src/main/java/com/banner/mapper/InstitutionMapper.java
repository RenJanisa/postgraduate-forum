package com.banner.mapper;

import com.banner.po.Institution;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 院校信息表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface InstitutionMapper extends BaseMapper<Institution> {

    @Select("select id from institution where institution_name = #{institutionName}")
    Long getByName(String institutionName);
}
