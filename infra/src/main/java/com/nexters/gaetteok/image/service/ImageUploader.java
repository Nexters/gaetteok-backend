package com.nexters.gaetteok.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nexters.gaetteok.image.config.BucketConfig;
import com.nexters.gaetteok.image.model.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageUploader {

    private final AmazonS3Client amazonS3Client;
    private final BucketConfig bucketConfig;

    public String getUuidFileName(String fileName) {
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return UUID.randomUUID() + "." + ext;
    }

    public List<File> uploadFiles(List<MultipartFile> multipartFiles, String filePath)
        throws IOException {
        List<File> uploadTargetFiles = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {

            String originalFileName = multipartFile.getOriginalFilename();
            if (originalFileName == null) {
                throw new NullPointerException("originalFileName should not be null");
            }

            String uploadFileName = getUuidFileName(originalFileName);
            String uploadFileUrl;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getSize());
            objectMetadata.setContentType(multipartFile.getContentType());

            try (InputStream inputStream = multipartFile.getInputStream()) {

                String keyName = filePath + "/" + uploadFileName;

                amazonS3Client.putObject(
                    new PutObjectRequest(
                        bucketConfig.getBucketName(), keyName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

                uploadFileUrl =
                    bucketConfig.getEndPoint() + "/" + bucketConfig.getBucketName() + "/" + keyName;

            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }

            uploadTargetFiles.add(
                File.builder()
                    .originalFileName(originalFileName)
                    .uploadFileName(uploadFileName)
                    .uploadFilePath(filePath)
                    .uploadFileUrl(uploadFileUrl)
                    .build());
        }

        return uploadTargetFiles;
    }
}
