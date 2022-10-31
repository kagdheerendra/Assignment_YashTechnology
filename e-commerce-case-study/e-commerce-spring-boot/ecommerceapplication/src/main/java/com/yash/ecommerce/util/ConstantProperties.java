package com.yash.ecommerce.util;

public class ConstantProperties {

	// Response
	public static final String SUCCESS_CODE = "200";
	public static final String SUCCESS_MESSAGE = "SUCCESS";

	public static final String FAILURE_CODE = "500";
	public static final String FAILURE_MESSAGE = "INTERNAL SERVER ERROR";

	public static final String BAD_REQUEST_CODE = "400";
	public static final String BAD_REQUEST_MESSAGE = "BAD_REQUEST";
    
	public static final String CONFLICT = "409";
	
	public static final String ADD_SUCCESS_MESSAGE = "ADD_PRO";
	public static final String UPD_SUCCESS_MESSAGE = "UPD_PRO";
	public static final String DEL_SUCCESS_MESSAGE = "DEL_PRO";
	public static final String VIEW_SUCCESS_MESSAGE = "VW_ORD";
	public static final String UPD_ORD_SUCCESS_MESSAGE = "UPD_ORD";
	public static final String LIST_SUCCESS_MESSAGE = "LIST_PRO";

	// CUSTOMER
	public static final String CUST_REG = "REGISTERED";
	public static final String CUST_ADR_ADD = "ADR_UPD";

	public static final String CART_UPD_MESSAGE_CODE = "CART_UPD";
	public static final String VW_CART_MESSAGE = "LIST_CART";

	public static final String UPD_CART_MESSAGE = "UPD_CART";

	public static final String DEL_CART_SUCCESS_MESSAGE = "DEL_CART";

	// Order
	public static final String ORD_STATUS_CODE = "PENDING";
	public static final String ORD_SUCCESS_MESSAGE = "PLA_ORD";

	// Missing Information
	public static final String MISSING_FAIL_MSG = "INFO_MISSING";
	public static final String INVALID_EMAIL_FAIL_MSG = "INVALID_EMAIL";
	public static final String INVALID_PASSWORD_FAIL_MSG = "INVALID_PASSWORD";
	public static final String USER_EMAIL_ALREADY_EXISTS = "USER_EMAIL_ALREADY_EXISTS";
	
	public static final String USER_EMAIL = "email";
	public static final String USER_PASSWORD = "password";
	public static final String USER_ADMIN_ROLE = "admin";
	public static final String USER_CUST_ROLE = "customer";

	// Parameters
	public static final String USER_AUTH_TOKEN = "AUTH_TOKEN";
	public static final String PROD_FILE = "file";
	public static final String PROD_DESC = "description";
	public static final String PROD_NAME = "productname";
	public static final String PROD_PRICE = "price";
	public static final String PROD_QUANITY = "quantity";
	public static final String PROD_ID = "productId";

	public static final String ORD_STATUS = "orderStatus";
	public static final String ORD_ID = "orderId";

	// Address
	public static final String ADR_NAME = "address";
	public static final String ADR_CITY = "city";
	public static final String ADR_STATE = "state";
	public static final String ADR_COUNTRY = "country";
	public static final String ADR_ZP = "zipcode";
	public static final String PHONE = "phonenumber";

	// BUFCART
	public static final String BUF_ID = "bufcartid";
	public static final String BUF_QUANTITY = "quantity";

	// Domains
	public static final String ALLOWED_URL = "http://localhost:4200";
}
