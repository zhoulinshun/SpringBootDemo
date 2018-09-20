package cn.miss.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/18.
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/upload")
    public Object upload(@RequestParam MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            final String contentType = multipartFile.getContentType();
            logger.info("文件格式:{}", contentType);
            final String fileName = multipartFile.getName();
            logger.info("文件名:{}", fileName);
            final String originalFilename = multipartFile.getOriginalFilename();
            logger.info("文件原名:{}", originalFilename);
            final long size = multipartFile.getSize();
            logger.info("文件大小:{}", size);
            try {
                multipartFile.transferTo(new File("/Users/" + fileName + "." + contentType));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "upload success";
    }

}
