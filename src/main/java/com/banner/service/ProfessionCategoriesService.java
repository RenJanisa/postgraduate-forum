package com.banner.service;

import com.banner.po.ProfessionCategories;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 专业分类表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface ProfessionCategoriesService extends IService<ProfessionCategories> {

    R<String> addProfessionCategory(ProfessionCategories professionCategories);

    R<String> updateProfessionCategory(ProfessionCategories professionCategories);

}
