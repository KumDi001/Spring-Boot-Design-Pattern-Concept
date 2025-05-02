package com.sample.test.service;

import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService {
	
	@Autowired
	OrderService orderserv;
	public String getOrderEventsFromOrderService() 
	{
		return orderserv.orderconfirm(getOrderEventsFromOrderService());
	}
	public void processPayment()
	{
		getOrderEventsFromOrderService();
		System.out.println();
	}
}
