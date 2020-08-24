package com.infinity.gamesFactory.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.infinity.gamesFactory.ApplicationBootstrap;
import org.apache.http.entity.ContentType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class FileServiceTest {
    @Autowired
    private FileService fileService;
    @Autowired
    private AmazonS3 amazonS3;
//    private String url = System.getProperty("URL");
    private String bucketName = System.getProperty("bucketName");
    private File sampleInputFile;

    @Before
    public void setUp(){

            URL url1 = Thread.currentThread().getContextClassLoader().getResource("testdata/testfile.txt");
            sampleInputFile = new File(url1.getPath());

    }
    @After
    public void tearDown(){
        reset(amazonS3);
    }

    @Test
    public void uploadFileTest()
    {
        fileService.uploadFile(sampleInputFile);
        verify(amazonS3,times(1)).putObject(any(PutObjectRequest.class));
    }


    @Test
    public void uploadFileTestUUID() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(sampleInputFile);
        MultipartFile multipartFile = new MockMultipartFile(
                sampleInputFile.getName(), sampleInputFile.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        String name = fileService.uploadFileUUID(bucketName,multipartFile);
        verify(amazonS3,times(1)).putObject(any(PutObjectRequest.class));

    }



}
