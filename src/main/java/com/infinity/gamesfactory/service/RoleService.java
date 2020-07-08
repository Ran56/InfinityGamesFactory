package com.infinity.gamesfactory.service;

import com.infinity.gamesfactory.model.Role;
import com.infinity.gamesfactory.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role save(Role role) { return roleDao.save(role);}
    public void delete(Role role){ roleDao.delete(role);}
    public Role update(Role role) { return roleDao.update(role);}
    public Role getRoleByName(String name){ return roleDao.getRoleByName(name);}
    public List<Role> getAllRoles(){ return roleDao.getAllRoles();}
    public Role getById(Long id){ return roleDao.getById(id);}



}
