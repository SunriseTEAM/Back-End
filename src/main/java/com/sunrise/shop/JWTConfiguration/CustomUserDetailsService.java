package com.sunrise.shop.JWTConfiguration;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sunrise.shop.model.User;
import com.sunrise.shop.service.UserServices.UserService;


@Service
//class CustomUserDetailsService tùy chỉnh
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String mobile)
            throws UsernameNotFoundException {
    	try {
			// Kiểm tra xem user có tồn tại trong database không?
            User user = userService.findByMobile(mobile);   
            return UserPrincipal.create(user);
    	}catch(Exception e) {
	   		throw new UsernameNotFoundException("User not found with Mobile : " + mobile);
	   	}
    }

	//Phương pháp này được sử dụng bởi JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
    	try {
   		 User user = userService.getUserDetailById(id);
   	        return UserPrincipal.create(user);
	   	}catch(Exception e) {
	   		throw new UsernameNotFoundException("User not found with id : " + id);
	   	}   
        //return UserPrincipal.create(user);
    }
   
//	@Override
//	public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
