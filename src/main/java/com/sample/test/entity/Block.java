package com.sample.test.entity;

import lombok.Data;

@Data
public class Block  {

	int block_id;
	public Block(int id) {
		// TODO Auto-generated constructor stub
		this.block_id=id;
	}

	void testme() {
		System.out.println("Block Method");
	}
	  public static int i = 1111;

	   static{
	       i = i-- - --i;    //L1
	   }

	   {
	       i = i++ + ++i;    //L2
	   }
}
