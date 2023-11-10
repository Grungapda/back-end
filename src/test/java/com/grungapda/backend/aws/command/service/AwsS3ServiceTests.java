package com.grungapda.backend.aws.command.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;

@SpringBootTest
public class AwsS3ServiceTests {

    @Autowired
    private AwsS3Service awsS3Service;

    @DisplayName("Aws S3 이미지 업로드 테스트")
    @Test
    void uploadImageTest(){
        final String fileName = "testImage";
        final String contentType = "png";

        MockMultipartFile imgFile = new MockMultipartFile(
                "images",
                fileName + "." + contentType,
                contentType,
                "images".getBytes(StandardCharsets.UTF_8)
        );
        String imgUrl = awsS3Service.uploadFile(imgFile);
        System.out.println("imgUrl = " + imgUrl);
        Assertions.assertNotNull(imgUrl);

        String[] imgLocation = imgUrl.split("/");


    }

}
