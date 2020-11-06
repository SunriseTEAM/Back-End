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
    public ResponseEntity<?> updateUserById(@RequestBody User _user, @PathVariable long id) {
        try {

           User user = userServiceimpl.getUserDetailById(id);

            //set new values for customer
            user.setName(_user.getName());
            user.setEmail(_user.getEmail());
            user.setPassword(_user.getPassword());
            user.setCreated_at(_user.getCreated_at());
            user.setLogin_token(_user.getLogin_token());
            user.setType(_user.getType());
            user.setAddress(_user.getAddress());
            user.setIs_email_verified(_user.getIs_email_verified());
            user.setMobile(_user.getMobile());

            // save the change to database
            userServiceimpl.updateUser(user);

            return new ResponseEntity<>(Arrays.asList(user,""),HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity(new String ("lỗi"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
