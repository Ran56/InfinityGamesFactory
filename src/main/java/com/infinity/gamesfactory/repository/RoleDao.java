package com.infinity.gamesfactory.repository;


import com.infinity.gamesfactory.model.Role;

import java.util.List;

public interface RoleDao {

    Role save(Role role);
    void delete(Role role);
    Role update(Role role);
    Role getRoleByName(String name);
    List<Role> getAllRoles();
    Role getById(Long id);

}
