package com.sunrise.shop.service.UserServices.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunrise.shop.Repository.UserRepository;
import com.sunrise.shop.model.User;
import com.sunrise.shop.service.UserServices.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public User findByMobile(String mobile) throws Exception {
		return userRepo.findByMobile(mobile).orElseThrow(() -> new Exception("User Not found.."));
	}

	@Override
	public User getUserDetailById(long userId) throws Exception {

		return userRepo.findById(userId).orElseThrow(() -> new Exception("User Not found.."));
	}

	@Override
	public User signUpUser(HashMap<String, String> signupRequest) throws Exception {
		try {
			if (userRepo.findByMobile(signupRequest.get("mobile")).isPresent()) {
				throw new Exception("User is already registered with Mobile No.");
			}
			User user = new User();
			user.setName(signupRequest.get("name"));
			user.setEmail(signupRequest.get("email"));
			user.setMobile(signupRequest.get("mobile"));
			user.setPassword(signupRequest.get("password"));
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			user.setCreated_at(ts.toString());
			userRepo.save(user);
			return user;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
}
