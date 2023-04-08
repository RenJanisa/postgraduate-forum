package com.banner.service;

import com.banner.dto.BorderlineDto;
import com.banner.po.Borderline;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 分数线统计表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface BorderlineService extends IService<Borderline> {

    R<BorderlineDto> getBorderline(String professionId);
}
