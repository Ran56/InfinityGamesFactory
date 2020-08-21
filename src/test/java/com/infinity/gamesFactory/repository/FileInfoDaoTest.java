package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.ApplicationBootstrap;
import com.infinity.gamesFactory.model.FileInfo;
import com.infinity.gamesFactory.model.Role;
import com.infinity.gamesFactory.model.User;
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

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class FileInfoDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FileInfoDao fileInfoDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    private FileInfo fileInfo;
    private Role role;
    private User user;

    @Before
    public void setUp()
    {
        role = new Role();
        role.setName("Supporter");
        role.setAllowedResource("/");
        role.setAllowedCreate(false);
        role.setAllowedDelete(false);
        role.setAllowedRead(true);
        role.setAllowedUpdate(false);
        roleDao.save(role);
        user = new User();
        user.setName("June");
        userDao.save(user);
        user.addRole(role);


        String originalS3Key = "hello";
        fileInfo = new FileInfo();
        fileInfo.setOriginalS3Key(originalS3Key);
        String uuid = UUID.randomUUID().toString();
        String uuidS3Key = uuid+originalS3Key;
        fileInfo.setUuidS3key(uuidS3Key);
        fileInfo.setBucketName("infinity-s3-bucket-1");
        fileInfo.setUser(user);
        fileInfoDao.save(fileInfo);
    }

    @After
    public void tearDown()
    {
        fileInfoDao.delete(fileInfo);
        roleDao.delete(role);
        userDao.delete(user);

    }

    @Test
    public void getFileInfo()
    {
        List<FileInfo> fileInfos = fileInfoDao.getFileInfos();
        Assert.assertNotNull(fileInfos);
    }



}
