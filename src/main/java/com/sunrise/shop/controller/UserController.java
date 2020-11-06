package com.sunrise.shop.controller;

import com.sunrise.shop.Repository.UserRepository;
import com.sunrise.shop.model.Category;
import com.sunrise.shop.model.Products;
import com.sunrise.shop.model.User;
import com.sunrise.shop.service.UserServices.UserService;
import com.sunrise.shop.service.UserServices.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserDetailsService userDetailsService;
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

    @PostMapping("/create")
    public ResponseEntity<?> addNewCustomer(@RequestBody User user) {
        try {
            User returnedUser = userServiceimpl.saveUser(user);

            return new ResponseEntity<>(Arrays.asList(returnedUser,""),HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<HttpStatus> updateUserById(@RequestBody User user, @PathVariable long id) {
        try {
            if(userServiceimpl.checkExistedUser(id)) {
                User user1 = userServiceimpl.getUserDetailById(id);

                //set new values for user
//                user1.setName(user.getName());
//                user1.setEmail(user.getEmail());
//                user1.setAddress(user.getAddress());
//                user1.setMobile(user.getMobile());
                user.setPassword(user1.getPassword());
//                user.setAddress(user1.getAddress());
                // save the change to database
                userServiceimpl.updateUser(user);

                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>( HttpStatus.NOT_FOUND);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
