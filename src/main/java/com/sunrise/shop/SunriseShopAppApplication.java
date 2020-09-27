package com.sunrise.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class SunriseShopAppApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SunriseShopAppApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SunriseShopAppApplication.class);
	}

}
