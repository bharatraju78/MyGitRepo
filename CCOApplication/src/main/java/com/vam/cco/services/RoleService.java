package com.vam.cco.services;

import java.util.List;

import com.vam.cco.dao.entity.Role;

public interface RoleService {
    Role save(Role role);
    Role findById(Long id);
    List<Role> findAll();
    Role update(Role role);
    void deleteById(Long id);
}
