package com.lihainuo.bubulog.file.controller;


import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.domain.vo.FileVO;
import com.lihainuo.bubulog.file.factory.FileServiceFactory;
import com.lihainuo.bubulog.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-28
 */

@Slf4j
@RestController
@RequestMapping("/admin/file")
public class FileController {

    @Autowired
    private FileServiceFactory fileServiceFactory;

    @PostMapping("/test")
    public Result test() {
        FileService fileService = fileServiceFactory.getFileService("qiniu");
        return fileService.test();
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public FileVO upload(MultipartFile file) {
        /**
         * 所实现的服务类为 七牛云服务类
         * 后续可以改成从配置类获取从而达成所需服务类
         */
        log.info("File:{}", file.getOriginalFilename());
        FileService fileService = fileServiceFactory.getFileService("qiniu");
        return fileService.upload(file);
    }



}
