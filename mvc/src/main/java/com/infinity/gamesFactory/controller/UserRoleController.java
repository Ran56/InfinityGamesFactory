package com.infinity.gamesFactory.controller;

import com.infinity.gamesFactory.model.Role;
import com.infinity.gamesFactory.model.User;
import com.infinity.gamesFactory.service.RoleService;
import com.infinity.gamesFactory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> getAllRoles()
    {
        return roleService.getAllRoles();
    }



}
