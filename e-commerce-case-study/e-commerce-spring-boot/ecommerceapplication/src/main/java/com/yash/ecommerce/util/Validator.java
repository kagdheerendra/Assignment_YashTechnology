package com.yash.ecommerce.util;

import javax.servlet.http.HttpServletRequest;

import com.yash.ecommerce.entity.Address;
import com.yash.ecommerce.entity.User;

/**
 * validate the user property.
 * @author dheerendra.kag
 *
 */
public class Validator {
	public static boolean isUserEmpty(User user) {
		if (user.getPassword() == null || user.getPassword() == "") {
			return true;
		}
		if (user.getEmail() == null || user.getEmail() == "") {
			return true;
		}
		if (user.getUserName() == null || user.getUserName() == "") {
			return true;
		}
		if(user.getUserType() == null || user.getUserType() == "") {
			return true;
		}
		return false;
	}
	
	public static boolean isValidEmail(String input) {
		if (input != null && input != "") {
			if (input.matches("^[a-zA-Z0-9._]*@[a-zA-Z0-9.-]*$")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidPassword(String input) {
		if (input != null && input != "") {
			if (input.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*#?&^_-]).{8,}")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isStringEmpty(String input) {
		if (input == null || input.equals("")) {
			return true;
		}
		return false;
	}
	
	public static boolean isAddressEmpty(Address address) {
		if (address.getAddress() == null || address.getAddress() == "") {
			return true;
		}
		if (address.getCity() == null || address.getCity() == "") {
			return true;
		}
		if (address.getState() == null || address.getState() == "") {
			return true;
		}
		if (address.getCountry() == null || address.getCountry() == "") {
			return true;
		}
		if (address.getPhonenumber() == null || address.getPhonenumber() == "") {
			return true;
		}
		if (address.getZipcode() == 0) {
			return true;
		}
		return false;
	}
	
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
}
