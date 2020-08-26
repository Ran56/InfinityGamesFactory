package com.infinity.gamesFactory.service;

import com.infinity.gamesFactory.model.FileInfo;
import com.infinity.gamesFactory.model.User;
import com.infinity.gamesFactory.repository.FileInfoDao;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.List;

@Service
public class FileInfoService {

    @Autowired
    private FileInfoDao fileInfoDao;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;

    public FileInfo save(FileInfo fileInfo){return fileInfoDao.save(fileInfo);}
    public List<FileInfo> getFileInfos(){return fileInfoDao.getFileInfos();}
    public FileInfo getBy(Long id){return fileInfoDao.getBy(id);}
    public boolean delete(FileInfo fileInfo){return fileInfoDao.delete(fileInfo);}

    public FileInfo update(FileInfo fileInfo){return fileInfoDao.update(fileInfo);}
    public FileInfo getFileInfoEagerById(Long id){return fileInfoDao.getFileInfoEagerById(id);}
    public List<FileInfo> getFileInfoByName(String originalS3Key,User user){return fileInfoDao.getFileInfoByName(originalS3Key,user);}
    public FileInfo getFileInfoByUser(User user,String uuidOrOriginalName){return fileInfoDao.getFileInfoByUser(user,uuidOrOriginalName);}






}
