package com.banner.service;

import com.banner.dto.*;
import com.banner.po.PostInfo;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 帖子信息表 服务类
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
public interface PostInfoService extends IService<PostInfo> {

    R<PostDto> getPost(String postId);

    R<Long> addPost(PostAUDto postAUDto);

    R<String> completePost(PostCompleteDto postCompleteDto);

    R<String> deletePost(String postId);

    R<PageDto<PostRecommendDto>> recommendPost(Integer page, Integer pageSize);

    R<String> updatePost(PostAUDto postAUDto);

    R<List<PostUserDto>> getUserPost(Integer status);

    R<PageDto<PostUserDto>> getPostPage(PageGetDto pageGetDto);
}
