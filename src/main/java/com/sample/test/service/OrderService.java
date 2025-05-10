package com.sample.test.service;

public class OrderService {

	static Colony colony;

	public OrderService(Consumer consumer) {
		colony = consumer;
	}

	public static void main(String[] args) {

		colony.testme();
	}

	public String orderconfirm(String order) {
		System.out.println("order request");
		return "order";
	}

}
