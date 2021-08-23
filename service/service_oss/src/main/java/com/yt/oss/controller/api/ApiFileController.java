package com.yt.oss.controller.api;

import com.yt.Result.Result;
import com.yt.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zjd
 * @create 2021-08-22 23:13
 */
@Api(tags="文件上传")
@RestController
@RequestMapping("/api/oss/file")
public class ApiFileController {
    @Autowired
    private FileService fileService;
    @ApiOperation("上传文件到阿里云")
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file){  //通过MultipartFile可以得到上传文件
        //获取上传文件
        String url = fileService.upload(file);  //上传后得到路径
        return Result.ok(url);
    }
}