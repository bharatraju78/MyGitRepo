package com.vam.cco.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vam.cco.dao.entity.User;
import com.vam.cco.dao.repository.UserRepository;
import com.vam.cco.model.AdminUser;
import com.vam.cco.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service

public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User save(User user) {
        logger.info("UserServiceImpl::save:start:: user: {}", user.getUsername());
        User savedUser = userRepository.save(user);
        logger.info("UserServiceImpl::save:end::User saved with id: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        logger.info("UserServiceImpl::findById:start id: {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            logger.info("User found: {}", user.get().getUsername());
        } else {
            logger.warn("User not found for id: {}", id);
        }
        logger.info("UserServiceImpl::findById:end");
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminUser> findAll() {
        logger.info("UserServiceImpl::findAll:start");
        List<AdminUser> users = userRepository.findAllUser();
        logger.info("Total users found: {}", users.size());
        logger.info("UserServiceImpl::findAll:end");
        return users;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User update(User user) {
    	logger.info("UserServiceImpl::update:start:: user: {}", user.getUsername());
        User updatedUser = userRepository.save(user);
        logger.info("User updated with id: {}", updatedUser.getId());
        logger.info("UserServiceImpl::update:end");
        return updatedUser;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
    	logger.info("UserServiceImpl::deleteById:start id: {}", id);
        logger.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
        logger.info("User deleted with id: {}", id);
        logger.info("UserServiceImpl::deleteById:end");
    }
    
    @Override
    @Transactional(readOnly = true)
    public AdminUser findByUserId(Long id) {
    	logger.info("UserServiceImpl::findByUserId:start id: {}", id);
        AdminUser adminUser = userRepository.findByUserId(id);
        if (adminUser != null) {
            logger.info("AdminUser found for id: {}", id);
        } else {
            logger.warn("AdminUser not found for id: {}", id);
        }
        logger.info("UserServiceImpl::findByUserId:end");
        return adminUser;
    }
}