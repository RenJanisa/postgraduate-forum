package com.banner.service.impl;


import cn.hutool.core.util.StrUtil;
import com.banner.dto.LikesCollectionContDto;
import com.banner.dto.UserLikesCollectionInfoDto;
import com.banner.mapper.LikesCollectionMapper;
import com.banner.mapper.PostInfoMapper;
import com.banner.po.LikesCollection;
import com.banner.po.PostInfo;
import com.banner.service.LikesCollectionService;
import com.banner.utils.BaseContext;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.beancontext.BeanContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.banner.utils.RedisConstants.LIKE_COLLECTION_IS;

/**
 * <p>
 * 赞和收藏表 服务实现类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Service
public class LikesCollectionServiceImpl extends ServiceImpl<LikesCollectionMapper, LikesCollection> implements LikesCollectionService {


    @Resource
    private LikesCollectionMapper likesCollectionMapper;

    @Resource
    private PostInfoMapper postInfoMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public R<Map<String, Integer>> getLikesCollectionCont(LikesCollectionContDto likesCollectionContDto) {
        Integer flag = likesCollectionContDto.getFlag();
        Integer type = likesCollectionContDto.getType();
        String id = likesCollectionContDto.getId();
        Long userId = BaseContext.getUserId();

        String key = LIKE_COLLECTION_IS + type + ":" + userId + ":" + id;
        String con = stringRedisTemplate.opsForValue().get(key);
        Map<String, Integer> rMap = new HashMap<String, Integer>();
        rMap.put("type", type);
        if (StrUtil.isNotBlank(con)) rMap.put("isHave", 1);
        else rMap.put("isHave", 0);
        Integer cont = likesCollectionMapper.getLikesCollectionCont(flag, id, type);
        rMap.put("cont", cont);
        return R.success(rMap);
    }

    @Override
    public R<String> addLikesCollection(LikesCollectionContDto likesCollectionContDto) {

        Integer flag = likesCollectionContDto.getFlag();
        Integer type = likesCollectionContDto.getType();
        String id = likesCollectionContDto.getId();
        Long userId = BaseContext.getUserId();
        Long mainId = IdWorker.getId();
        LocalDateTime time = LocalDateTime.now();
        String key = LIKE_COLLECTION_IS + type + ":" + userId + ":" + id;

        String con = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isNotBlank(con)) {
            likesCollectionMapper.deleteLikeCollection(flag, id, userId);
            stringRedisTemplate.delete(key);
            return R.success("取消成功", "0");
        } else {
            if (flag == 0) likesCollectionMapper.addLikesCollection(mainId, id, type, userId, time);
            else if (flag == 1) likesCollectionMapper.addLikesCollectionComment(mainId, id, type, userId, time);
            else PostgraduateForumException.error(500, "非法flag");
            stringRedisTemplate.opsForValue().set(key, "isHave");
            return R.success("操作成功", "1");
        }

    }

    @Override
    public R<List<UserLikesCollectionInfoDto>> getUserLikesCollectionInfo(LikesCollectionContDto likesCollectionContDto) {

        Integer flag = likesCollectionContDto.getFlag();
        String id = likesCollectionContDto.getId();
        if (StrUtil.isBlank(id)) id = BaseContext.getUserId().toString();
        Integer type = likesCollectionContDto.getType();
        List<UserLikesCollectionInfoDto> likesCollectionInfoDtos = new ArrayList<>();
        if (flag == 0)
            likesCollectionInfoDtos = likesCollectionMapper.getUserLikesCollectionInfoPost(id, type);
        else if (flag == 1)
            likesCollectionInfoDtos = likesCollectionMapper.getUserLikesCollectionInfoComment(id, type);
        else
            PostgraduateForumException.error(500, "非法flag");

        return R.success(likesCollectionInfoDtos);
    }
}
