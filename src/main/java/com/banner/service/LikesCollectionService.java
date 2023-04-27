package com.banner.service;

import com.banner.dto.LikesCollectionContDto;
import com.banner.dto.UserLikesCollectionInfoDto;
import com.banner.po.LikesCollection;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 赞和收藏表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface LikesCollectionService extends IService<LikesCollection> {

    R<Map<String,Integer>> getLikesCollectionCont(LikesCollectionContDto likesCollectionContDto);

    R<String> addLikesCollection(LikesCollectionContDto likesCollectionContDto);

    R<List<UserLikesCollectionInfoDto>> getUserLikesCollectionInfo(LikesCollectionContDto likesCollectionContDto);
}
