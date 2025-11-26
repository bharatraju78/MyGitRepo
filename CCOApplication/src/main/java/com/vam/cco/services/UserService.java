package com.vam.cco.services;

import com.vam.cco.dao.entity.User;
import com.vam.cco.model.AdminUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Long id);
    List<AdminUser> findAll();
    User update(User user);
    void deleteById(Long id);
    AdminUser findByUserId(Long id);
}
