package com.grungapda.backend.aws.command.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
//import marvin.image.MarvinImage;
//import org.marvinproject.image.transform.scale.Scale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.mock.web.MockMultipartFile;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@Service
public class AwsS3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client ;

    @Autowired
    public AwsS3Service (AmazonS3Client amazonS3Client){
        this.amazonS3Client = amazonS3Client;
    }

    public String uploadFile(MultipartFile multipartFiles){
        int fileCategory = fileCategorization(multipartFiles.getOriginalFilename());
        String fileName = createFileName(multipartFiles.getOriginalFilename());

        String fileFormatName = multipartFiles.getContentType().substring(multipartFiles.getContentType().lastIndexOf("/")+1);
        String url = null;

        ObjectMetadata objectMetadata = new ObjectMetadata();
//        if (fileCategory == 1){
//        MultipartFile resizedFile = resizeFile(fileName, fileFormatName, multipartFiles, 768);
//
//        objectMetadata.setContentLength(resizedFile.getSize());
//        objectMetadata.setContentType(resizedFile.getContentType());
//
//        try(InputStream inputStream = resizedFile.getInputStream()){
//            PutObjectRequest uploadFile = new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
//                        .withCannedAcl(CannedAccessControlList.PublicRead);
//            amazonS3Client.putObject(uploadFile);
//
//            url = String.valueOf(amazonS3Client.getUrl(bucket, uploadFile.getKey()));
//
//        } catch (IOException e){
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
//                        "파일 업로드에 실패했습니다.");
//
//        }
//
//            return url;
//        }
        objectMetadata.setContentLength(multipartFiles.getSize());
        objectMetadata.setContentType(multipartFiles.getContentType());



        try(InputStream inputStream = multipartFiles.getInputStream()){

            PutObjectRequest uploadFile = new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3Client.putObject(uploadFile);

            url = String.valueOf(amazonS3Client.getUrl(bucket, uploadFile.getKey()));

        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "파일 업로드에 실패했습니다.");

        }

        return url;

    }


    private String createFileName(String fileName){
        try{
            return UUID.randomUUID().toString().concat(getFileExtension(fileName));
        }catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일 확장자(" + fileName + ")입니다.");
        }
    }

    private String getFileExtension(String fileName){
        String extension = fileName.substring(fileName.lastIndexOf("."));
        if (extension.equals(".png") || extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".mid") || extension.equals(".mp3")){
            return extension;
        }
        return null;
    }
    private int fileCategorization(String fileName){
        String file = fileName.substring(fileName.lastIndexOf("."));
        if(file.equals(".png") || file.equals(".jpg") || file.equals(".jpeg")){
            return 1;
        }
        if (file.equals(".mid") || file.equals(".mp3)")){
            return 2;
        }
        return 0;
    }

    public void deleteFile(String fileName){

        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

//    private MultipartFile resizeFile(String fileName, String fileFormatName, MultipartFile originalFile, int targetWidth){
//        try{
//            BufferedImage image = ImageIO.read(originalFile.getInputStream());
//
//            int originWidth = image.getWidth();
//            int originHeight = image.getHeight();
//
//            if (originWidth < targetWidth){
//                return originalFile;
//            }
//
//            MarvinImage imageMarvin = new MarvinImage(image);
//
//            Scale scale = new Scale();
//            scale.load();
//            scale.setAttribute("newWidth", targetWidth);
//            scale.setAttribute("newHeight", targetWidth * originHeight / originWidth);
//            scale.process(imageMarvin.clone(), imageMarvin, null, null, false);
//
//            BufferedImage imageNoAlpha = imageMarvin.getBufferedImageNoAlpha();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(imageNoAlpha, fileFormatName, baos);
//            baos.flush();
//
//          return new MockMultipartFile(fileName, baos.toByteArray());
//        }catch (IOException e){
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"파일 리사이즈에 실패했습니다.");
//        }
//    }



}
