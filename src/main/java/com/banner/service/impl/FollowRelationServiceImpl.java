package com.banner.service.impl;


import cn.hutool.core.util.StrUtil;
import com.banner.dto.FollowAddDto;
import com.banner.dto.FollowUserDto;
import com.banner.dto.UserDto;
import com.banner.mapper.FollowRelationMapper;
import com.banner.po.FollowRelation;
import com.banner.service.FollowRelationService;
import com.banner.utils.BaseContext;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.beancontext.BeanContext;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 关注关系表 服务实现类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Service
public class FollowRelationServiceImpl extends ServiceImpl<FollowRelationMapper, FollowRelation> implements FollowRelationService {

    @Resource
    private FollowRelationMapper followRelationMapper;

    @Override
    public R<List<FollowUserDto>> getFollow(Integer flag) {
        if (flag != 0 && flag != 1) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        Long userId = BaseContext.getUserId();
        List<FollowUserDto> userDtos;
        if (flag == 0) userDtos = followRelationMapper.getFollowByUserId(userId);
        else userDtos = followRelationMapper.getFanByUserId(userId);
        return R.success(userDtos);
    }

    @Override
    public R<Integer> getIsHave(String otherId) {

        if (StrUtil.isBlank(otherId)) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        Long userId = BaseContext.getUserId();
        int cont = followRelationMapper.getIsHave(userId,otherId);

        if (cont == 1) return R.success(1);
        return R.success(0);
    }

    @Override
    public R<Integer> addFollow(FollowAddDto followAddDto) {
        Integer isHave = followAddDto.getIsHave();
        Long userId = BaseContext.getUserId();
        Long otherId = Long.valueOf(followAddDto.getOtherId());
        String address = followAddDto.getAddress();
        if (isHave == 0){
            //未关注
            followRelationMapper.insert(new FollowRelation(userId,otherId,address));
            return R.success(1);
        } else if (isHave == 1) {
            //已关注 再次点击取消
            followRelationMapper.deleteFollow(userId,otherId);
            return R.success(0);
        }else PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        return null;
    }
}
