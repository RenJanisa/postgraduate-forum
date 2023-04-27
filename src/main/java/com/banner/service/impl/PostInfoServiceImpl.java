package com.banner.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.banner.dto.*;
import com.banner.mapper.PostCategoriesRelationMapper;
import com.banner.mapper.PostInfoMapper;
import com.banner.po.PostInfo;
import com.banner.service.PostInfoService;
import com.banner.utils.BaseContext;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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
    public R<Long> addPost(PostAUDto postAUDto) {
        Long id = IdWorker.getId();
        PostInfo postInfo = BeanUtil.copyProperties(postAUDto, PostInfo.class);
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

        return R.success("帖子发布成功!");
    }

    @Override
    public R<String> deletePost(String postIds) {
        if (StrUtil.isBlank(postIds)) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        String[] postList = postIds.split(",");
        postInfoMapper.deleteBatchIds(Arrays.asList(postList));

        return R.success("删除成功!");
    }

    @Override
    public R<PageDto<PostRecommendDto>> recommendPost(Integer page, Integer pageSize) {

        if (page == null || pageSize == null) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        List<PostRecommendDto> postRecommendDtoList = postInfoMapper.getRecommendPost((page - 1) * pageSize, pageSize);
        PageDto<PostRecommendDto> pageDto = new PageDto<>(page,pageSize, postRecommendDtoList);
        return R.success(pageDto);
    }

    @Override
    public R<String> updatePost(PostAUDto postAUDto) {

        if (StrUtil.isBlank(postAUDto.getId())) PostgraduateForumException.error(CommonError.PARAMS_ERROR);

        PostInfo postInfo = BeanUtil.copyProperties(postAUDto, PostInfo.class);
        boolean update = this.update(postInfo, new LambdaQueryWrapper<PostInfo>().eq(PostInfo::getId, postInfo.getId()));

        if (!update) PostgraduateForumException.error(CommonError.UNKOWN_ERROR);
        return R.success("部分更新成功");
    }

    @Override
    public R<List<PostUserDto>> getUserPost(Integer status) {

        if (status == null) status = 0;

        Long userId = BaseContext.getUserId();
        List<PostUserDto> postDtoList = postInfoMapper.getUserPost(userId,status);
        return R.success(postDtoList);
    }

    @Override
    public R<PageDto<PostUserDto>> getPostPage(PageGetDto pageGetDto) {

        Integer page = pageGetDto.getPage();
        Integer pageSize = pageGetDto.getPageSize();
        String postName = pageGetDto.getName();

        List<PostUserDto> postUserDtos;
        if (StrUtil.isNotBlank(postName)) postUserDtos = postInfoMapper.getPostPageWithName((page - 1) * pageSize, pageSize, "%" + postName + "%");
        else postUserDtos = postInfoMapper.getPostPage((page - 1) * pageSize, pageSize);

        PageDto<PostUserDto> postPageDto = new PageDto<>(page, pageSize, postUserDtos);
        return R.success(postPageDto);
    }

}
