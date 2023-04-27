package com.banner.mapper;

import com.banner.dto.InstitutionDto;
import com.banner.po.Institution;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    Long getIdByName(String institutionName);

    @Select("SELECT i.*,p.cname FROM institution i,place p WHERE i.institution_name like #{institutionName} AND i.area = p.id")
    List<InstitutionDto> getLikeName(String institutionName);

    @Select("SELECT i.*,p.cname FROM institution i,place p WHERE i.area in (SELECT id FROM place WHERE parent_id = #{placeId}) AND i.area = p.id")
    List<InstitutionDto> getByPlaceId(String placeId);

    @Select("SELECT i.*,p.cname FROM institution i,place p WHERE i.id like #{institutionId} AND i.area = p.id")
    List<InstitutionDto> getById(String institutionId);

    @Select("SELECT i.*,p.cname FROM institution i,place p WHERE i.institution_name like #{institutionName} AND i.area = p.id limit #{page},#{pageSize}")
    List<InstitutionDto> getInstitutionPageWithName(Integer page, Integer pageSize, String institutionName);
    @Select("SELECT i.*,p.cname FROM institution i,place p WHERE i.area = p.id limit #{page},#{pageSize}")
    List<InstitutionDto> getInstitutionPage(Integer page, Integer pageSize);
}
