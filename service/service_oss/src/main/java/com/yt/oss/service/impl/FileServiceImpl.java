package com.yt.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.yt.oss.service.FileService;
import com.yt.oss.utils.ConstantOssPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Zjd
 * @create 2021-08-22 23:12
 */
@Service
public class FileServiceImpl implements FileService {
    //获取上传文件
    @Override
    public String upload(MultipartFile file) {
        String endpoint = ConstantOssPropertiesUtils.EDNPOINT;
        String accessKeyId = ConstantOssPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantOssPropertiesUtils.SECRECT;
        String bucketName = ConstantOssPropertiesUtils.BUCKET;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取文件流
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            //为防止文件名重复造成文件覆盖，生成随机值，添加到文件中
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filename = uuid + filename;
            //按照当前日期创建文件夹，上传到当前文件夹里面  /2021/02/01/01.jpg
            String timeUrl = new DateTime().toString("yyyy/MM/dd");
            filename = timeUrl + "/" + filename;

            // 调用方法，实现上传  参数2为上传文件（路径+）名称
            ossClient.putObject(bucketName, filename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // 返回上传后文件路径
            // 例：https://yygh-atguigu-li.oss-cn-beijing.aliyuncs.com/qq.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + filename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
