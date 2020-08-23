package com.infinity.gamesFactory.service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;


@Service
public class AWSS3Service {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private String awsAccessKeyId = System.getProperty("aws.accessKeyId");
    private String awsSecretKey = System.getProperty("aws.secretKey");
    private AmazonS3 amazonS3;

    public AWSS3Service (@Autowired AmazonS3 amazonS3)
    {
//        amazonS3 = getS3ClientWithSuppliedCredentials();
        this.amazonS3 = amazonS3;
    }


    public AmazonS3 getS3ClientWithSuppliedCredentials()
    {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKeyId,awsSecretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        return s3Client;
    }

    public boolean isBucketExist(String bucketName)
    {
        boolean isExist = amazonS3.doesBucketExistV2(bucketName);
        return isExist;
    }

    public Bucket createBucket(String bucketName)
    {
        Bucket bucket = null;
        if(!amazonS3.doesBucketExistV2(bucketName))
            bucket = amazonS3.createBucket(bucketName);
        else
            logger.debug("bucket name: " + bucketName+" is not available, try again with a different bucket name");
        return bucket;
    }


    public Boolean deleteBucket(String bucketName)
    {
        try {
            amazonS3.deleteBucket(bucketName);
            logger.debug("bucket name: " + bucketName+" is successfully deleted");
            return true;
        }
        catch (AmazonServiceException e){
            logger.debug("bucket name: " + bucketName + " failed to delete",e);
            return false;
        }

    }



    public String generatePresignedURLForUploading(String bucketName,
                                                   String objectKey) {
        return generatePresignedURL(bucketName, objectKey, "PUT");
    }

    public String generatePresignedURLForDownloading(String bucketName,
                                                     String objectKey) {
        return generatePresignedURL(bucketName, objectKey, "GET");
    }


    public String generatePresignedURL(String bucketName,
                                       String fileName, String httpMethodString) {
        // Set the pre-signed URL to expire after one hour.
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);


        // Generate the pre-signed URL.
        logger.info("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileName)
                        .withMethod(HttpMethod.valueOf(httpMethodString))
                        .withExpiration(expiration);
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);



        return url.toString();
    }




}
