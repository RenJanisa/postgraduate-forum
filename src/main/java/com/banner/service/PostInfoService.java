package com.banner.service;

import com.banner.dto.PostAddDto;
import com.banner.dto.PostCompleteDto;
import com.banner.dto.PostDto;
import com.banner.po.PostInfo;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

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

    R<Long> addPost(PostAddDto postAddDto);

    R<String> completePost(PostCompleteDto postCompleteDto);

    R<String> deletePost(String postId);
}
