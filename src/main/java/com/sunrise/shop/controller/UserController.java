package com.sunrise.shop.controller;

import com.sunrise.shop.Repository.UserRepository;
import com.sunrise.shop.model.Products;
import com.sunrise.shop.model.User;
import com.sunrise.shop.service.UserServices.UserService;
import com.sunrise.shop.service.UserServices.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserDetailsService userDetailservice;
    @Autowired
    UserService userService;
    @Autowired
    UserServiceImpl userServiceimpl;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("getAll")
    public List<User> getAllUSer() {
        return userServiceimpl.getAllUser();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
