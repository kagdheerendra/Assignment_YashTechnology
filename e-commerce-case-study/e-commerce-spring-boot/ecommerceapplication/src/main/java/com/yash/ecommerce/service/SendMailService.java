package com.yash.ecommerce.service;

import javax.mail.internet.MimeMessage;
import javax.swing.text.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.util.Validator;

@Service
public class SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendSimpleEmail(String siteURL, User user) {
		
//		SimpleMailMessage message = new SimpleMailMessage();
//		
//		message.setFrom("dheerendra12kag@gmail.com");
//		message.setTo(toEmail);
//		message.setText(body);
//		message.setSubject(subject);
//		
//		this.javaMailSender.send(message);
//		
//		System.out.println("mail send..");
		
		
		   String toAddress = user.getEmail();
		   System.out.println("toAddress is = "+toAddress);
		    String fromAddress = "dheerendra12kag@gmail.com";
		    String subject = "Please verify your registration";
		    String senderName = "Ecommerce Application";
		    String content = "Dear [[name]],<br>"
		            + "Please click the link below to verify your registration:<br>"
		            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
		            + "Thank you,<br>"
		            + "Ecommerce Application Teams.";
		     
		    MimeMessage message = javaMailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(message);
		     
		    try {
			    helper.setFrom(fromAddress, senderName);
			    helper.setTo(toAddress);
			    helper.setSubject(subject);
			     
			    content = content.replace("[[name]]", user.getUserName());
			    String verifyURL = siteURL + "/home/verify?code="+ user.getVerificationCode();
			     
			    content = content.replace("[[URL]]", verifyURL);
			     
			    helper.setText(content, true);
			     
			    javaMailSender.send(message);	
			    
			    System.out.println("Mail Send...");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
	}
	
	
	public void sendNotificationToUser(String toAddress, String status) {
		    System.out.println(toAddress+"::"+status);
		    String fromAddress = "dheerendra12kag@gmail.com";
		    String senderName = "Ecommerce Application";
		    String content = "Dear Customer,<br>"
		            + "Your order has bean "+status+" by admin.<br>"
		            + "Thank you,<br>"
		            + "Ecommerce Application Teams.";
		     
		    MimeMessage message = javaMailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(message);
		     
		    try {
			    helper.setFrom(fromAddress, senderName);
			    helper.setTo(toAddress);
			    helper.setSubject("Your order has bean "+status);
			    helper.setText(content, true);
			     
			    javaMailSender.send(message);	
			    
			    System.out.println("Mail Send...");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
	}
}
