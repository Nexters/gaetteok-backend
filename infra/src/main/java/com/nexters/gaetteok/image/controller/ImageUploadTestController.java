package com.nexters.gaetteok.image.controller;

import com.nexters.gaetteok.image.service.ImageUploader;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ImageUploadTestController {

    private final ImageUploader imageUploader;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFilesSample(
        @RequestPart(value = "files") List<MultipartFile> multipartFiles) throws IOException {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(imageUploader.uploadFiles(multipartFiles, "image"));
    }

}
