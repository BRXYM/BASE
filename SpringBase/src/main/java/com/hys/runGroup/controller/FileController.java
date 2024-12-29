package com.hys.runGroup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files")
public class FileController {

    // 获取桌面路径
    private String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "uploads";

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("文件名称：" + file.getOriginalFilename());
        if (file.isEmpty()) {
            return new ResponseEntity<>("文件为空", HttpStatus.BAD_REQUEST);
        }

        try {
            // 创建保存文件的目录（如果不存在）
            Path directoryPath = Paths.get(desktopPath);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // 构建文件保存路径
            Path targetFilePath = directoryPath.resolve(file.getOriginalFilename());

            // 保存文件
            file.transferTo(targetFilePath.toFile());
            System.out.println("文件保存路径: " + targetFilePath.toString());

            return new ResponseEntity<>("文件上传成功", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("文件上传失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
