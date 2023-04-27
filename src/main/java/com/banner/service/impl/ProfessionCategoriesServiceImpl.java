package com.banner.service.impl;

import com.banner.mapper.ProfessionCategoriesMapper;
import com.banner.po.ProfessionCategories;
import com.banner.service.ProfessionCategoriesService;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 专业分类表 服务实现类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Service
public class ProfessionCategoriesServiceImpl extends ServiceImpl<ProfessionCategoriesMapper, ProfessionCategories> implements ProfessionCategoriesService {

    @Override
    public R<String> addProfessionCategory(ProfessionCategories professionCategories) {

        int count = this.count(new LambdaQueryWrapper<ProfessionCategories>().eq(ProfessionCategories::getProfessionCategoriesName, professionCategories.getProfessionCategoriesName()));
        if (count > 0) PostgraduateForumException.error(500,"分类已存在");

        boolean save = this.save(professionCategories);
        if (!save) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);
        return R.success("添加成功");
    }

    @Override
    public R<String> updateProfessionCategory(ProfessionCategories professionCategories) {

        if (professionCategories.getId() == null) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        boolean update = this.updateById(professionCategories);
        if (!update) PostgraduateForumException.error(500,"更新目标不存在");
        return R.success("更新成功");
    }

}
