package com.banner.mapper;

import com.banner.dto.BorderlineDto;
import com.banner.po.Borderline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BorderlineMapper extends BaseMapper<Borderline> {

    BorderlineDto getBorderline(@Param("professionId") String professionId);
}
