package cn.miss.spring.controller;

import cn.miss.spring.util.FileUtil;
import cn.miss.spring.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/18.
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/upload")
    public Object upload(@RequestParam MultipartFile multipartFile) {
        FileUtil.save(multipartFile);
        return ResponseUtil.success("upload success");
    }

    @PostMapping("/multiUpload")
    public Object upload(@RequestParam List<MultipartFile> multipartFiles) {
        multipartFiles.forEach(FileUtil::save);
        return ResponseUtil.success("upload success");
    }


    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        final File file = ResourceUtils.getFile("classpath:lib/classmexer.jar");
        response.setHeader("Content-Disposition", "attachment; filename=" + "classmexer.jar");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        Files.copy(file.toPath(), response.getOutputStream());
    }

}
