package com.banner.mapper;

import com.banner.po.FollowRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 关注关系表 Mapper 接口
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface FollowRelationMapper extends BaseMapper<FollowRelation> {
    List<Long> getOtherId(@Param("myId") Long myId);
}
