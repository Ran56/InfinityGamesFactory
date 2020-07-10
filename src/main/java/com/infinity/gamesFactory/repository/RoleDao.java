package com.infinity.gamesFactory.repository;


import com.infinity.gamesFactory.model.Role;

import java.util.List;

public interface RoleDao {

    Role save(Role role);
    void delete(Role role);
    Role update(Role role);
    Role getRoleByName(String name);
    List<Role> getAllRoles();
    Role getById(Long id);

}
