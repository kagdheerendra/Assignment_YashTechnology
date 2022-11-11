package com.yash.ecommerce;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

import com.yash.ecommerce.service.SendMailService;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		
		ApplicationContext app = SpringApplication.run(EcommerceApplication.class, args);
	}
}
