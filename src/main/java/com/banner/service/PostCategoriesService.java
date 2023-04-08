package com.banner.service;

import com.banner.po.PostCategory;
import com.banner.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author rjj
 * @date 2023/3/25 - 9:26
 */
public interface PostCategoriesService extends IService<PostCategory> {
    R<List<String>> getPostCategories(String postId);
}
