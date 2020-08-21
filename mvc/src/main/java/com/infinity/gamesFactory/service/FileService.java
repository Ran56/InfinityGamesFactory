package com.infinity.gamesFactory.service;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.infinity.gamesFactory.model.FileInfo;
import com.infinity.gamesFactory.model.User;
import com.infinity.gamesFactory.repository.FileInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileInfoService fileInfoService;
    @Autowired
    private FileInfoDao fileInfoDao;
    private AmazonS3 amazonS3;

    private String bucketName = System.getProperty("bucketName");

    public FileService(@Autowired AmazonS3 amazonS3)
    {
        this.amazonS3 = amazonS3;
    }

    public void uploadFile(File file)
    {
        PutObjectRequest request = new PutObjectRequest(bucketName,file.getName(),file);
        amazonS3.putObject(request);
    }

    public String uploadFileUUID(String bucketName, MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();

        String[] nameContents = fileName.split("\\.");
        String newName = nameContents[0]+ "-" + uuid + "." + nameContents[nameContents.length-1];

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        PutObjectRequest request = new PutObjectRequest(bucketName, newName, file.getInputStream(),objectMetadata);
        amazonS3.putObject(request);
        return newName;

    }

    public URL getFileURL(String bucketName, String s3Key)
    {
        LocalDateTime expiration = LocalDateTime.now().plusDays(1);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,s3Key);
        generatePresignedUrlRequest.withMethod(HttpMethod.GET);
        generatePresignedUrlRequest.withExpiration(Date.from(expiration.toInstant(ZoneOffset.UTC)));
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return url;
    }


    public String uploadFileUUIDAndSaveFileInfo(String bucketName,MultipartFile multipartFile) throws IOException {

        String uuid = UUID.randomUUID().toString();
        String fileName = multipartFile.getOriginalFilename();

        String[] nameContents = fileName.split("\\.");
        String uuidName = nameContents[0]+ "-" + uuid + "." + nameContents[nameContents.length-1];

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        PutObjectRequest request = new PutObjectRequest(bucketName, uuidName, multipartFile.getInputStream(),objectMetadata);
        amazonS3.putObject(request);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalS3Key(fileName);
        fileInfo.setBucketName(bucketName);
        fileInfo.setUuidS3key(uuidName);
        fileInfo.setUser(fileInfoService.userInside);
        fileInfoDao.save(fileInfo);
        fileInfoService.userInside = null;

        return uuidName;

    }


    public Boolean deleteFileInfo(String uuidOrOriginalName)
    {
        User user = fileInfoService.userInside;
        List<FileInfo> fileInfoList = fileInfoService.getFileInfoByName(uuidOrOriginalName,user);
        if(fileInfoList == null) return false;

        FileInfo fileInfoResult = new FileInfo();
        for(FileInfo fileInfo: fileInfoList)
            if(fileInfo.getUser().getId()==user.getId())
            {
                fileInfoResult = fileInfo;
                break;
            }
        if(fileInfoResult==null) return false;
        String s = new String();
        return fileInfoService.delete(fileInfoResult);

    }


















}
