package com.infinity.gamesFactory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infinity.gamesFactory.model.Role;
import com.infinity.gamesFactory.model.User;
import com.infinity.gamesFactory.service.JWTService;
import com.infinity.gamesFactory.service.RoleService;
import com.infinity.gamesFactory.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private RoleService roleService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authentication(@RequestBody User user)
    {
        logger.debug("username is "+user.getName() +" password is "+user.getPassword());
        try
        {
            User user1 = userService.getUserByCredentials(user.getName(),user.getPassword());
            String token = jwtService.generateToken(user1);
            Map<String,String> map = new HashMap<>();
            map.put("token",token);
            String json = new ObjectMapper().writeValueAsString(map);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("UNAUTHORIZED",e);
            return new ResponseEntity<>("Your username or password is incorrect", HttpStatus.UNAUTHORIZED);
        }

    }

    @RequestMapping(value = "/",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> signUp(@RequestBody User user)
    {
        logger.debug("Creating user ");
        Role role = roleService.getRoleByName("user");
        user.addRole(role);
        User user1 = userService.save(user);
        return new ResponseEntity(user1,HttpStatus.OK);

    }

}
