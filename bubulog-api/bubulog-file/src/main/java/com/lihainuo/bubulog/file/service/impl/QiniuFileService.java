package com.lihainuo.bubulog.file.service.impl;


import com.google.gson.Gson;
import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.enums.ResultEnum;
import com.lihainuo.bubulog.common.exception.BusinessException;
import com.lihainuo.bubulog.domain.vo.FileVO;
import com.lihainuo.bubulog.file.service.FileService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-28
 */

@Slf4j
@Service
@ConditionalOnProperty(name = "storage.type", havingValue = "qiniu")
public class QiniuFileService implements FileService {

    @Value("${storage.qiniu.accessKey}")
    private String accessKey;

    @Value("${storage.qiniu.secretKey}")
    private String secretKey;

    @Value("${storage.qiniu.bucket}")
    private String bucket;

    @Value("${storage.qiniu.prefixUrl}")
    private String prefixUrl;

    private UploadManager uploadManager;
    private Auth auth;
    private FileVO fileVO;

    /**
     * 表明init()方法会在当前Bean的依赖注入完成后自动执行，用于完成初始化操作。
     */
    @PostConstruct
    public void init() {
        auth = Auth.create(accessKey, secretKey);
        // 构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.huadong());
        uploadManager = new UploadManager(cfg);
    }

    /**
     * 测试
     * @return
     */
    @Override
    public Result test() {
        log.info("accessKey:{},secretKey:{}", accessKey, secretKey);
        return Result.success("File test, ok!");
    }

    @Override
    public Result upload(MultipartFile file) {
        try {
            // 获取文件地址 以及 文件扩展名
            String originalFilename = file.getOriginalFilename();
            String extendFilename = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 默认不指定 key 的情况下，以文件内容的hash值作为文件名 时间日期前缀
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/");
            // 构建新的文件名
            String key = currentDate.format(formatter)
                    + UUID.randomUUID().toString().replace("-", "")
                    + extendFilename;
            log.info("file:{}", key);

            // 从 MultipartFile 直接获取输入流 避免先保存本地再上传
            InputStream inputStream = file.getInputStream();

            String upToken = auth.uploadToken(bucket);
            log.info("upToken:{}", upToken);

            // 开始上传
            try {
                Response response = uploadManager.put(
                        inputStream, key, upToken,
                        null, null
                );
                // 解析上传成功结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                log.info("key:{}, hash:{}", putRet.key, putRet.hash);
                // 返回访问图片的URL
                String url = prefixUrl + putRet.key;
                log.info("访问上传成功图片的URL:{}", url);
                fileVO = FileVO.builder().url(url).build();
            } catch (QiniuException e) {
                e.printStackTrace();
                if (e.response != null) {
                    log.error(e.response.toString());
                    try {
                        String body = e.response.bodyString();
                        log.info("body:{}", body);
                    } catch (Exception ignore) {}
                }
            }

        } catch (Exception e) {
            log.error("上传文件失败",e);
            throw new BusinessException(ResultEnum.FILE_UPLOAD_ERROR);
        }

        return Result.success(fileVO);
    }
}
