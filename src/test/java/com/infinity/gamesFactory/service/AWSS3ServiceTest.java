package com.infinity.gamesFactory.service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.infinity.gamesFactory.ApplicationBootstrap;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class AWSS3ServiceTest {
    @Autowired
    private AWSS3Service awsS3Service;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Before
    public void setUp(){}

    @After
    public void tearDown(){}

    @Test
    public void testCreateBucket()
    {
        String bucketName = "infinity-s3-practice";
        Bucket bucket = awsS3Service.createBucket(bucketName);
        Assert.assertNotNull(bucket);
    }

    @Test
    public void testDeleteBucket()
    {
        String bucketName = "infinity-s3-practice";
        Boolean isDeleteBucket = awsS3Service.deleteBucket(bucketName);
        Assert.assertEquals(true,isDeleteBucket);
    }

}
