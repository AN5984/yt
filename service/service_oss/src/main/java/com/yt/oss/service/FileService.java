package com.yt.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zjd
 * @create 2021-08-22 23:12
 */
public interface FileService {
    String upload(MultipartFile file);
}
