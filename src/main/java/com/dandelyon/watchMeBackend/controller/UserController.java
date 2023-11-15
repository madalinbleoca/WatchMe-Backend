package com.dandelyon.watchMeBackend.controller;

import com.dandelyon.watchMeBackend.model.User;
import com.dandelyon.watchMeBackend.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username) {
        User userFromDB = userService.getUserByUsername(username);

        return new ResponseEntity<>(userFromDB, HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User userFromDB = userService.getUserById(id);

    return new ResponseEntity<>(userFromDB, HttpStatus.OK);
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        User userFromDB = userService.getUserById(id);
        userService.deleteUserById(id);

        return new ResponseEntity<>("Customer with id " + id + " Was deleted!", HttpStatus.OK);
    }


}
