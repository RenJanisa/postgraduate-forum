package com.banner.mapper;

import com.banner.po.ProfessionInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author rjj
 * @date 2023/4/8 - 15:46
 */
public interface ProfessionInfoMapper extends BaseMapper<ProfessionInfo> {
    @Select("Select * from profession_info where institution_id = #{institutionId} and profession_id = #{professionId}")
    ProfessionInfo getProfessionInfo(String institutionId, String professionId);
}
