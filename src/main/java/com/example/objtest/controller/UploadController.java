package com.example.objtest.controller;

import com.example.objtest.entity.Image;
import com.example.objtest.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Slf4j
public class UploadController {

    private ImageService imageService;

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveImage(HttpServletRequest request,
                            @RequestParam("uploadFile")MultipartFile uploadFile,
                            Image image) throws IOException {
        log.info("컨트롤러 레이어에서 파일 저장하기");
        log.info("업로드파일 내용물: "+uploadFile);
        log.info("엔터티객체 내용물: "+image);
        imageService.uploadImage(uploadFile, image);
        return image.getImageUrl();
    }
}
