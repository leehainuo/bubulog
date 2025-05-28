package com.lihainuo.bubulog.file.service;

import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.domain.vo.FileVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-28
 */

public interface FileService {

    /**
     * 测试
     * @return
     */
    Result test();

    /**
     * 上传文件
     * @param file
     * @return
     */
    FileVO upload(MultipartFile file);
}
