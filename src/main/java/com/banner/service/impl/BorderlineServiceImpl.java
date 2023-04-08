package com.banner.service.impl;


import cn.hutool.core.util.StrUtil;
import com.banner.dto.BorderlineDto;
import com.banner.mapper.BorderlineMapper;
import com.banner.po.Borderline;
import com.banner.service.BorderlineService;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 分数线统计表 服务实现类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Service
public class BorderlineServiceImpl extends ServiceImpl<BorderlineMapper, Borderline> implements BorderlineService {

    @Resource
    private BorderlineMapper borderlineMapper;
    @Override
    public R<BorderlineDto> getBorderline(String professionId) {
        if (StrUtil.isBlank(professionId)) PostgraduateForumException.error(CommonError.REQUEST_NULL);
        BorderlineDto borderlineDto = borderlineMapper.getBorderline(professionId);
        return R.success(borderlineDto);
    }
}
