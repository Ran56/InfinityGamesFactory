package com.infinity.gamesFactory.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {

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



}
