package com.banner.mapper;

import com.banner.dto.PlaceDto;
import com.banner.po.Place;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author rjj
 * @date 2023/3/26 - 10:38
 */
public interface PlaceMapper extends BaseMapper<Place> {
    @Select("select id,cname from place where ctype = 1")
    List<PlaceDto> getOnePlace();

    @Select("select id,cname from place where parent_id = #{placeId}")
    List<PlaceDto> getById(String placeId);
}
