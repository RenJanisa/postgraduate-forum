package com.banner.service;

import com.banner.dto.FollowAddDto;
import com.banner.dto.FollowUserDto;
import com.banner.dto.UserDto;
import com.banner.po.FollowRelation;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 关注关系表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface FollowRelationService extends IService<FollowRelation> {

    R<List<FollowUserDto>> getFollow(Integer flag);

    R<Integer> getIsHave(String otherId);

    R<Integer> addFollow(FollowAddDto followAddDto);
}
