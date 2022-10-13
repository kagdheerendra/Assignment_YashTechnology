package com.yash.springaop.services;

public class PaymentServiceImpl implements IPaymentService{

	public void makePayment(int amount) {
		System.out.println(amount+"Amount Debited..!");
		System.out.println(amount+"Amount Credited..!");
	}

}
