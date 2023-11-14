package com.dandelyon.watchMeBackend.service;

import com.dandelyon.watchMeBackend.model.User;

public interface UserService {

    User getUserByUsername(String username);

    User getUserById(Long id);

    User saveUser(User user);

    void deleteUserById(Long id);
}
