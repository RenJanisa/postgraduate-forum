package com.banner.mapper;

import com.banner.dto.ProfessionRelationDto;
import com.banner.dto.ProfessionSimpleDto;
import com.banner.po.Profession;
import com.banner.po.ProfessionCategories;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    List<ProfessionSimpleDto> getProfessionByName(@Param("professionName") String professionName);

    List<ProfessionSimpleDto> getProfessionByCategoryId(@Param("categoryId") String categoryId);

    @Select("SELECT * FROM profession_categories where flag = #{flag}")
    List<ProfessionCategories> getProfessionParent(String flag);

    List<ProfessionCategories> getProfessionParentWithInstitutionId(@Param("institutionId") String institutionId, @Param("flag") String flag);

    List<ProfessionSimpleDto> getProfessionPageWithName(Integer page, Integer pageSize, String professionName);

    List<ProfessionSimpleDto> getProfessionPage(Integer page, Integer pageSize);

    @Insert("insert into profession_institution_relation values (#{professionId},#{institutionId},#{description})")
    void addRelation(String institutionId, String professionId, String description);
    @Insert("insert into profession_category_institution values (#{institutionId},#{categoryId})")
    void addRelationCategory(String institutionId, String categoryId);

    List<ProfessionRelationDto> getProfessionRelation(String professionId);

    Integer isHaveCategoryAndInstitution(String institutionId, String categoryId);

    Integer isHaveProfessionAndInstitution(String institutionId, String professionId);

    @Select("select category_id from profession where id = #{professionId}")
    String getCategoryId(String professionId);


    @Update("update profession_institution_relation set description = #{description} where profession_id = #{professionId} and institution_id = #{institutionId}")
    boolean updateProfessionInstitutionDescription(String professionId, String institutionId, String description);
}
