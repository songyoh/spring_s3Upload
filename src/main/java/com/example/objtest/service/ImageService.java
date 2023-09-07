package com.example.objtest.service;

import com.example.objtest.entity.Image;
import com.example.objtest.repository.ImageRepository;
import com.example.objtest.util.S3Uploader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
public class ImageService {

    private ImageRepository imageRepository; // 파일 경로는 DB에 저장

    private S3Uploader s3Uploader; // 실제 파일은 오브젝트 스토리지에 올려야 함

    // 파일이 들어오면, DB에도 저장하고 오브젝트 스토리지에도 업로드하도록 처리
    // 그리고 저장된 이미지번호 리턴
    @Transactional
    public Long uploadImage(MultipartFile uploadFile, Image image) throws IOException {
        log.info("파일 업로드 실행");
        if(!uploadFile.isEmpty()) { // 저장하려는 파일이 존재한다면
            String fileName = s3Uploader.upload(uploadFile, ""); // 두 번째 파라미터는 저장할 폴더명
            image.setImageUrl(fileName);
        }
        Image savedImage = imageRepository.save(image);
        return savedImage.getImageId();
    }
}
