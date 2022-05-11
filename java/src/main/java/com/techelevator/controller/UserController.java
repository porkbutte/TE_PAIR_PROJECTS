package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import com.techelevator.model.UserAlreadyExistsException;
import com.techelevator.model.UserNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/users")
    List<User> findAll()  throws UserNotFoundException, UserAlreadyExistsException {
        return userDao.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/user/{id}")
    User getUserById(@PathVariable Long user_id) {
        return userDao.getUserById(user_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/users/{username}")
    User findByUsername(@PathVariable String username) {
        return userDao.findByUsername(username);
    }



    @PostMapping(path = "/user")
    private void create(@RequestBody User user) {

        String username = user.getUsername();
        String password = user.getPassword();
        boolean activated = user.isActivated();

    };


}
