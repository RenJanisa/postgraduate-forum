package com.banner.controller;

import com.banner.service.CommonService;
import com.banner.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author rjj
 * @date 2023/3/26 - 8:24
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Resource
    private CommonService commonService;


    @RequestMapping("/upload")
    public R<String> upload(@RequestPart("file") MultipartFile multipartFile,
                            @RequestParam("flag") String flag) throws IOException {
        return commonService.upload(multipartFile, flag);
    }
}
