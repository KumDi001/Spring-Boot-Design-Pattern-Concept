package com.sample.test.oopsConcepts;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component("address")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Scope(scopeName = "prototype")
public class Address implements Cloneable ,FunctionalInterfaceTest{

	@Autowired
	Colony colony;
	
	int address_id;
	String address_Line1;
	String address_Line2;

	public void testme() {
		System.out.println("Address Method");
	}
	public int testme(int t) {
		System.out.println("Address Method");
		return t;
	}
	public int testmeAddress() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Address call");
		return 0;
	}
	@Override
	public void funcTest() {
		// TODO Auto-generated method stub
		
		System.out.println("Implemented by Address class");
		
	}

}
