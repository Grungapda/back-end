package com.grungapda.backend.aws.command.controller;

import com.grungapda.backend.aws.command.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class testController {

    private final AwsS3Service awsS3Service;

    @Autowired
    public testController(AwsS3Service awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    @PostMapping("/test/image")
    public String test(@RequestBody MultipartFile multipartFile){
        String uri = awsS3Service.uploadFile(multipartFile);
        System.out.println("uri = " + uri);
        return uri;
    }
}
