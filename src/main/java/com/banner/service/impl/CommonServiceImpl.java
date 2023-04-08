package com.banner.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.banner.service.CommonService;
import com.banner.utils.BaseContext;
import com.banner.utils.CommonError;
import com.banner.utils.PostgraduateForumException;
import com.banner.utils.R;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.Size;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author rjj
 * @date 2023/3/26 - 8:31
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Value("${minio.bucket.files}")
    private String bucket_Files;

    @Resource
    private MinioClient minioClient;


    @Override
    public R<String> upload(MultipartFile file, String flag) throws IOException {

        String fileMd5 = DigestUtil.md5Hex(file.getBytes());
        String folder = BaseContext.getUserId() + "/" + flag + "/";
        String filename = fileMd5 + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = folder + filename;
        try {

            //TODO 上传到minio
            addMediaFilesToMinIO(file, bucket_Files, url);
            R<String> r = new R<String>();
            r.setCode(200);
            r.setData(url);
            return r;

        } catch (Exception e) {
            e.printStackTrace();
        }
        PostgraduateForumException.error(CommonError.UNKOWN_ERROR);
        return null;
    }

    public void addMediaFilesToMinIO(MultipartFile file, String bucket, String objectName) throws IOException {
        //  将文件字节输入到内存流中
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file.getBytes());
        //获取文件类型
        String contentType = file.getContentType();
        try {
            PutObjectArgs putObjectArgs =
                    PutObjectArgs.builder().bucket(bucket).object(objectName)
                            //-1 表示文件分片按 5M(不小于 5M,不大于 5T),分片数量最大10000
                            .stream(byteArrayInputStream, byteArrayInputStream.available(), -1)
                            .contentType(contentType)
                            .build();
            minioClient.putObject(putObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
