package com.infinity.gamesFactory.service;


import com.infinity.gamesFactory.ApplicationBootstrap;
import com.infinity.gamesFactory.model.Role;
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
    @Autowired
    private RoleService roleService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Test
    public void generateTokenTest()
    {
        Role role = roleService.getById(Long.valueOf(3));
        User u = new User();
        u.setId(10);
        u.setName("RyanZ");
        u.addRole(role);
        String token = jwtService.generateToken(u);
        logger.info(token);
        String[] arr = token.split("\\.");
        Assert.assertNotNull(token);
        Assert.assertEquals(3,arr.length);
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
