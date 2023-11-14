package com.dandelyon.watchMeBackend.service.implementation;

import com.dandelyon.watchMeBackend.exception.ResourceNotFoundException;
import com.dandelyon.watchMeBackend.model.User;
import com.dandelyon.watchMeBackend.repository.UserRepository;
import com.dandelyon.watchMeBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with user name "
                        + username + " doesn't exist in DB"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id "
                        + id + " doesn't exist in DB"));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
