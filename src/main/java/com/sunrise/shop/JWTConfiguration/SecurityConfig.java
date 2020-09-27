package com.sunrise.shop.JWTConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 	@Autowired
	    CustomUserDetailsService customUserDetailsService;
//	 	@Autowired
//		BCryptPasswordEncoder eEncrypt;
	    @Autowired
	    private JwtAuthenticationEntryPoint unauthorizedHandler;
	    
	    @Bean
	    public JwtAuthenticationFilter jwtAuthenticationFilter() {
	        return new JwtAuthenticationFilter();
	    }

	    @Override
	    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder
	                .userDetailsService(customUserDetailsService)// Cung cáp userservice cho spring security
	                .passwordEncoder(passwordEncoder());// cung cấp password encoder
	    }
	    @Bean(BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	    	// Get AuthenticationManager bean
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	// Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .cors()
	                    .and()
	                .csrf()
	                    .disable()
	                .exceptionHandling()
	                    .authenticationEntryPoint(unauthorizedHandler)
	                    .and()
	                .sessionManagement()
	                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                    .and()
	                .authorizeRequests()
	                    .antMatchers("/",
	                        "/favicon.ico",
	                        "/**/*.png",
	                        "/**/*.gif",
	                        "/**/*.svg",
	                        "/**/*.jpg",
	                        "/**/*.html",
	                        "/**/*.css",
	                        "/**/*.js")
	                        .permitAll()
	                    .antMatchers("/api/status/**","/api/login/**","/api/signup/**")// Cho phép tất cả mọi người truy cập vào địa chỉ này
	                        .permitAll()
//	                    .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
//	                        .permitAll().antMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**")
	                        .antMatchers(HttpMethod.GET, "/api/status/**")
	                        .permitAll();
//	                    .anyRequest()
//	                        .authenticated();// Tất cả các request khác đều cần phải xác thực mới được truy cập

	        // Thêm một lớp Filter kiểm tra jwt
	        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	    }
}
