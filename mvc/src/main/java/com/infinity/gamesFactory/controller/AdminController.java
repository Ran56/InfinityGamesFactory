package com.infinity.gamesFactory.controller;

import com.infinity.gamesFactory.model.Role;
import com.infinity.gamesFactory.model.User;
import com.infinity.gamesFactory.service.RoleService;
import com.infinity.gamesFactory.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "",method = RequestMethod.DELETE,params = {"userName"})
    public ResponseEntity<String> deleteUser(@RequestParam(name = "userName") String name)
    {
        try{
            User user = userService.getUserByName(name);
            userService.delete(user);
            return new ResponseEntity("succeed to delete user",HttpStatus.OK);
        }
        catch (Exception e)
        {return new ResponseEntity<>("fail to delete user", HttpStatus.BAD_REQUEST);}
    }

    @RequestMapping(value = "",method = RequestMethod.DELETE,params = {"roleName"})
    //there are two RequestMethod.DELETE which needs to differentiate them by using params={}
    public void deleteRole(@RequestParam(name = "roleName") String name)
    {
        Role role = roleService.getRoleByName(name);
        roleService.delete(role);
    }


    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<Role> create(@RequestBody Role role)
    {
        return new ResponseEntity<>(roleService.save(role), HttpStatus.OK);
    }


    @RequestMapping(value = "",method = RequestMethod.PATCH)
    public ResponseEntity<User> promoteOrDemote(@RequestParam("userName") String name, @RequestParam("roleName") String roleName)
    {
        User user = userService.getUserByName(name);
        if(user==null)
        {
            logger.error("Cannot find user");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Role role1 = roleService.getRoleByName(roleName);
        if(role1==null)
        {
            logger.error("Cannot find role");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        user.getRoles().clear();
        // avoid to one environment: when demote user'role, if there is no
        // user.getRoles().clear(), this user will still has previous role
        user.addRole(role1);
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }


}
