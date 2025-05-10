package com.sample.test.oopsConcepts;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OopConcepts {

	FunctionalInterfaceTest funcTest;
	@Autowired
	Area area;
	@Autowired
	Address address;

	@Autowired
	Colony colony;
	
	@Autowired
	public OopConcepts(@Qualifier("address") FunctionalInterfaceTest functionalInterfaceTest) {
		this.funcTest = functionalInterfaceTest;

	}

	public static void main(String[] args) throws IOException {
		Colony a= new Colony();
		a.testme();
		System.out.println(a);
	
	}
}
