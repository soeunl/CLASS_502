package org.choongang.file.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {

    @Value("${file.upload.path}") // 파일이 업로드 되는 경로 설정
    private String uploadDir;

    @GetMapping("/upload")
    public String upload() {

        return "file/upload";
    }

    @ResponseBody
    @PostMapping("/upload")
    public void uploadPs(@RequestPart("file") MultipartFile file) { // 사용자가 업로드한 파일 정보를 Multipart를 통해 가지고 옴
        String name = file.getOriginalFilename(); // 사용자가 업로드한 파일의 원래 이름을 가져옴
        log.info("파일명 : {}", name); // 업로드된 파일명을 로그에 기록

        File uploadPath = new File(uploadDir + name); // 변수에 저장된 기본 경로와 사용자 파일 이름을 연결하여 업로드할 파일의 전체 경로를 생성

        try{
        file.transferTo(uploadPath); // 사용자로부터 업로드 받은 파일을 uploadPath 경로에 저장
        } catch (IOException e) {
            log.error("파일 업로드에 실패했습니다.", e);
        } // 오류가 발생하는 경우
    }
}
