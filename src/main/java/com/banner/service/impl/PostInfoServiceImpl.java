package com.banner.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.banner.dto.PostDto;
import com.banner.mapper.PostInfoMapper;
import com.banner.po.PostInfo;
import com.banner.service.PostInfoService;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 帖子信息表 服务实现类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo> implements PostInfoService {

    @Resource
    private PostInfoMapper postInfoMapper;

    @Override
    public R<PostDto> getPost(String postId) {

        if (StrUtil.isBlank(postId))
            PostgraduateForumException.error(CommonError.PARAMS_ERROR);
        PostDto postDto = postInfoMapper.getById(postId);
        return R.success(postDto);
    }
}
