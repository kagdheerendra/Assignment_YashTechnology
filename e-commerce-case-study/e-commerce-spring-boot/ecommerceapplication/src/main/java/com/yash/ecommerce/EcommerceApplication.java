package com.yash.ecommerce;

import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.yash.ecommerce.config.SendEmail;
import com.yash.ecommerce.controller.HomeController;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		
		ApplicationContext app = SpringApplication.run(EcommerceApplication.class, args);
//		SendEmail sendEmail = app.getBean(SendEmail.class);
//		sendEmail.sendEMail("Hello this is my first java email service example", "E-Commerce", "deepikasarode7@gmail.com", "dheerendra12kag@gmail.com", "(Akku@12)");
	}
}
