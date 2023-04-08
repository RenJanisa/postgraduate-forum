package com.banner.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.banner.dto.PostAddDto;
import com.banner.dto.PostCompleteDto;
import com.banner.dto.PostDto;
import com.banner.mapper.PostCategoriesRelationMapper;
import com.banner.mapper.PostInfoMapper;
import com.banner.po.PostInfo;
import com.banner.service.PostInfoService;
import com.banner.utils.BaseContext;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Arrays;

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
    @Resource
    private PostCategoriesRelationMapper postCategoriesRelationMapper;

    @Override
    public R<PostDto> getPost(String postId) {

        if (StrUtil.isBlank(postId)) PostgraduateForumException.error(CommonError.PARAMS_ERROR);
        PostDto postDto = postInfoMapper.getById(postId);
        return R.success(postDto);
    }

    @Override
    public R<Long> addPost(@Validated PostAddDto postAddDto) {
        Long id = IdWorker.getId();
        PostInfo postInfo = BeanUtil.copyProperties(postAddDto, PostInfo.class);
        postInfo.setId(id);
        postInfo.setUserId(BaseContext.getUserId());
        this.save(postInfo);
        return R.success(id);
    }

    @Override
    @Transactional
    public R<String> completePost(PostCompleteDto postCompleteDto) {
        PostInfo postInfo = BeanUtil.copyProperties(postCompleteDto, PostInfo.class);
        boolean b = this.updateById(postInfo);
        if (!b) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);

        if (StrUtil.isBlank(postCompleteDto.getPostCategoryId())) return R.success("帖子成功!");

        for (String postCategoryId : postCompleteDto.getPostCategoryId().split(","))
            postCategoriesRelationMapper.addRelation(postInfo.getId(), postCategoryId);

        return R.success("帖子成功!");
    }

    @Override
    public R<String> deletePost(String postIds) {
        if (StrUtil.isBlank(postIds)) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        String[] postList = postIds.split(",");
        postInfoMapper.deleteBatchIds(Arrays.asList(postList));

        return R.success("删除成功!");
    }
}
