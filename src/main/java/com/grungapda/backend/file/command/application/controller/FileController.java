package com.grungapda.backend.file.command.application.controller;

import com.grungapda.backend.aws.command.service.AwsS3Service;
import com.grungapda.backend.common.ResponseMessage;
import com.grungapda.backend.file.command.application.DTO.CreateFile;
import com.grungapda.backend.file.command.application.DTO.FileRequest;
import com.grungapda.backend.file.command.application.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "music file upload API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = {"클라이언트 IP","*"})
@Slf4j
public class FileController {

    private final AwsS3Service awsS3Service;
    private final FileService fileService;

    @ApiOperation(value = "음악 저장")
    @PostMapping(value = "/fileUpload"
            )
    public ResponseEntity<ResponseMessage> fileUpload(
            @RequestPart FileRequest fileRequest,
            @RequestPart List<MultipartFile> fileList

            ){
        try {

            log.info("fileRequest :: {}", fileRequest);
            log.info("fileList :: {}", fileList);
            String imgUrl = awsS3Service.uploadFile(fileList.get(0));
            String musicUrl = awsS3Service.uploadFile(fileList.get(1));
            String midUrl = awsS3Service.uploadFile(fileList.get(2));

            CreateFile createFile = new CreateFile(
                    fileRequest.getSongTitle(),
                    fileRequest.getSongArtist(),
                    fileRequest.getNeedSession(),
                    imgUrl,
                    musicUrl,
                    midUrl,
                    fileRequest.getParticipantList()
            );

            fileService.createFile(createFile);


            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseMessage(HttpStatus.CREATED.value(), "음악 업로드 완료", null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

}
