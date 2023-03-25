package com.banner.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.banner.dto.UserInfoDto;
import com.banner.mapper.InstitutionMapper;
import com.banner.mapper.UserInfoMapper;
import com.banner.po.Institution;
import com.banner.po.UserInfo;
import com.banner.service.UserInfoService;
import com.banner.utils.BaseContext;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private InstitutionMapper institutionMapper;


    @Override
    public R<UserInfoDto> getUserInfo(String strUserId) {
        Long userId;
        if (StrUtil.isNotBlank(strUserId)){
            userId= Long.valueOf(strUserId);
        }else {
            userId = BaseContext.getUserId();
        }

        UserInfoDto userInfoDTO = userInfoMapper.getUserInfo(userId);
        return R.success(userInfoDTO);
    }

    @Override
    @Transactional
    public R<String> addUserInfo(UserInfoDto userInfoDto) {
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(userInfoDto, userInfo);
        Long userId = BaseContext.getUserId();
        userInfo.setUserId(userId);

        Long institutionId = institutionMapper.getByName(userInfoDto.getInstitutionName());

        if (institutionId == null) {
            //该院校记录不存在
            Institution institution = new Institution(userInfoDto.getInstitutionName(), userInfoDto.getInstitutionUrl());
            institutionMapper.insert(institution);
            institutionId = institutionMapper.getByName(userInfoDto.getInstitutionName());
        }
        userInfo.setGoalId(institutionId);

        userInfoMapper.update(userInfo,new LambdaUpdateWrapper<UserInfo>().eq(UserInfo::getUserId,userId));

        return R.success("完善成功!");
    }
}
