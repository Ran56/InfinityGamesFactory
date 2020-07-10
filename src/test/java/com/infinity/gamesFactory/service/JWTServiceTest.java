package com.infinity.gamesFactory.service;


import com.infinity.gamesFactory.ApplicationBootstrap;
import com.infinity.gamesFactory.model.User;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class JWTServiceTest {
    @Autowired
    private  JWTService jwtService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Test
    public void generateTokenTest()
    {
        User u = new User();
        u.setId(10);
        u.setName("RyanZ");
        String token = jwtService.generateToken(u);
        String[] arr = token.split("\\.");
        Assert.assertNotNull(token);
        Assert.assertEquals(3,arr.length);//验证两点三段式
    }

    @Test
    public void decryptJwtTokenTest()
    {
        User u = new User();
        u.setId(10);
        u.setName("RyanZ");
        String token = jwtService.generateToken(u);
        Claims claims = jwtService.decryptJwtToken(token);
        Assert.assertNotNull(claims);
        Assert.assertEquals(claims.getSubject(),"RyanZ");
    }
}
