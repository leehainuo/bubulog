package com.lihainuo.bubulog.file.factory;

import com.lihainuo.bubulog.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-28
 */

@Component
public class FileServiceFactory {

    @Autowired
    private Map<String, FileService> fileServiceMap;

    @Autowired
    public FileServiceFactory(List<FileService> fileServiceList) {
        fileServiceMap = fileServiceList.stream()
                .collect(Collectors.toMap(
                        service -> service.getClass().getSimpleName(),
                        Function.identity()
                ));
    }

    public FileService getFileService(String serviceName) {
        return fileServiceMap.get(serviceName + "FileService");
    }

}
