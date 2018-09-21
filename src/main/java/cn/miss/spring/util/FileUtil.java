package cn.miss.spring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/21.
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void save(MultipartFile multipartFile) {
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
                multipartFile.transferTo(new File("/Users/zhoulinshun/" + originalFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
