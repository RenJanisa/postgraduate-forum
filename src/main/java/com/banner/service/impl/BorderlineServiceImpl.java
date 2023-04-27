package com.banner.service.impl;


import cn.hutool.core.util.StrUtil;
import com.banner.dto.BorderlineDto;
import com.banner.mapper.BorderlineMapper;
import com.banner.po.Borderline;
import com.banner.service.BorderlineService;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public R<BorderlineDto> getBorderline(String categoryId) {
        if (StrUtil.isBlank(categoryId)) PostgraduateForumException.error(CommonError.REQUEST_NULL);
        BorderlineDto borderlineDto = borderlineMapper.getBorderline(categoryId);
        return R.success(borderlineDto);
    }

    @Override
    public R<String> addBorderline(Borderline borderline) {

        Integer count = borderlineMapper.selectCount(new LambdaQueryWrapper<Borderline>()
                .eq(Borderline::getCategoryId, borderline.getCategoryId())
                .eq(Borderline::getCreateTime, borderline.getCreateTime()));

        if(count > 0) PostgraduateForumException.error(500,"已存在");
        int insert = borderlineMapper.insert(borderline);
        if (insert<=0) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);

        return R.success("添加成功");
    }

    @Override
    public R<String> updateBorderline(Borderline borderline) {

        if (borderline.getId() == null) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        boolean update = this.updateById(borderline);
        if (!update) PostgraduateForumException.error(500,"目标不存在!");
        return R.success("更新成功!");
    }
}
