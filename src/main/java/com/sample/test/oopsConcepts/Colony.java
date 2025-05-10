package com.sample.test.oopsConcepts;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class Colony {
	int C_Id;
	String colonyNumber;
	
	public void testme() {
		System.out.println("Colony Method");
	}
}
