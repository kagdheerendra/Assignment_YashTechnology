package com.yash.ecommerce.config;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class SendEmail {

	// this is responsible to send mail
	public void sendEMail(String message, String subject, String to, String from, String password) {
		
		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("Properties = "+properties);
		
		//set properties
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.debug", "true");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true"); 
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		
		//create the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, password);
			}
		});
		session.setDebug(true);
		
		//compose the test message
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(new InternetAddress(from));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);
			
			Transport transport = session.getTransport("smtps");
			//using transport send the message
			transport.connect("smtp.gmail.com", Integer.valueOf("465"), from, password);
			Transport.send(mimeMessage);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			
			System.out.println("send successfully");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
