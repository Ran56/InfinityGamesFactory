package com.infinity.gamesFactory.controller;


import com.amazonaws.services.s3.AmazonS3;
import com.infinity.gamesFactory.model.FileInfo;
import com.infinity.gamesFactory.model.User;
import com.infinity.gamesFactory.service.FileInfoService;
import com.infinity.gamesFactory.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private FileInfoService fileInfoService;
    @Autowired
    private AmazonS3 amazonS3;



    private Logger logger = LoggerFactory.getLogger(getClass());
    private String bucketName = System.getProperty("bucketName");

    @RequestMapping(value = "",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        logger.debug("The file name is: "+multipartFile.getOriginalFilename());
        String name = fileService.uploadFileUUID(bucketName,multipartFile);
        return name;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public URL getFileUrl(@RequestParam("bucketName") String bucketName, @RequestParam("s3Key") String s3Key)
    {
        return fileService.getFileURL(bucketName,s3Key);
    }


    @RequestMapping(value = "/fileInfo",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileAndSaveFileInfo(HttpServletRequest request,@RequestParam("file") MultipartFile multipartFile) throws IOException {
        logger.debug("The file name is: "+multipartFile.getOriginalFilename());

        User user = (User) request.getAttribute("userInfo");
        String originalName = multipartFile.getOriginalFilename();
        FileInfo fileInfo = fileInfoService.getFileInfoByUser(user,originalName);
        if(fileInfo==null) return null;

        fileService.deleteFileInfo(user,originalName);
        amazonS3.deleteObject(bucketName,fileInfo.getUuidS3key());
                //not delete instead update

        return fileService.uploadFileUUIDAndSaveFileInfo(user,bucketName,multipartFile);

    }

    @RequestMapping(value = "/fileInfo",method = RequestMethod.GET)
    public ResponseEntity<URL> getFileUrlByFileInfo(HttpServletRequest request,@RequestParam("uuidOrOriginalName") String uuidOrOriginalName)
    {
        User user = (User) request.getAttribute("userInfo");
        //find current user information
        // request.getHeader("userId")
        // findFileInfoByUserId
        // if(null) create new fileInfo
        //else update fileInfo
        FileInfo fileInfo = fileInfoService.getFileInfoByUser(user,uuidOrOriginalName);
        if(fileInfo == null) return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        String s3Key = fileInfo.getUuidS3key();
        String bucketNameOfFileInfo = fileInfo.getBucketName();
        return new ResponseEntity<>(fileService.getFileURL(bucketNameOfFileInfo,s3Key),HttpStatus.OK);
    }


    @RequestMapping(value = "/fileInfo",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteFileInfo(HttpServletRequest request,@RequestParam("uuidOrOriginalName") String uuidOrOriginalName)
    {
        User user = (User) request.getAttribute("userInfo");
        Boolean result = fileService.deleteFileInfo(user,uuidOrOriginalName);
        if(result==false)return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(true,HttpStatus.OK);

    }




}
