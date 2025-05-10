package com.sample.test.entity;

import java.util.List;

import lombok.Data;

@Data
public class Address  {
	
	List<Block> block;
	int address_id;
	public static int counter = 0;
	    public Address() {
	       counter = 20;
	   }
	   Address(int x){
	       counter = x;
	   }

}
