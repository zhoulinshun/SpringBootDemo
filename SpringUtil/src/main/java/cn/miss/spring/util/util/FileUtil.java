package cn.miss.spring.util.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
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

    private static final String UNKNOWN_PREFIX = "unknown";

    public static void save(MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            final String fileName = multipartFile.getName();
            logger.info("文件名:{}", fileName);
            final String originalFilename = multipartFile.getOriginalFilename();
            logger.info("文件原名:{}", originalFilename);
            final long size = multipartFile.getSize();
            logger.info("文件大小:{}", size);
            try {
                String name;
                String prefix;
                if (StringUtils.isEmpty(originalFilename)) {
                    name = multipartFile.hashCode() + "";
                    prefix = UNKNOWN_PREFIX;
                } else {
                    final int i = originalFilename.lastIndexOf(".");
                    if (i > 0) {
                        name = originalFilename.substring(0, 1);
                        prefix = originalFilename.substring(1);
                    } else {
                        name = originalFilename;
                        prefix = UNKNOWN_PREFIX;
                    }
                }

                File file = ResourceUtils.getFile("classpath:upload/" + name + "." + prefix);
                int temp = 1;
                while (file.exists()) {
                    file = ResourceUtils.getFile("classpath:upload/" + name + temp++ + "." + prefix);
                }
                logger.info("保存文件名：{}", file.getName());
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
