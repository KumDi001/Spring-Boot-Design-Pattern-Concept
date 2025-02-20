package com.sample.test.entity;

import com.sample.test.service.Colony;

public class Address implements Colony {
	@Override
	public void testme() {
		System.out.println("Address Method");
	}
	public static int counter = 0;
	    public Address() {
	       counter = 20;
	   }
	   Address(int x){
	       counter = x;
	   }

}
