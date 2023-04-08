package com.banner.service;

import com.banner.utils.R;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author rjj
 * @date 2023/3/26 - 8:31
 */
public interface CommonService {
    R<String> upload(MultipartFile multipartFile, String flag) throws IOException;
}
