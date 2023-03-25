package com.banner.service;

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
}
