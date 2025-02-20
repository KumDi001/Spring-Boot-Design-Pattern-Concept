package com.sample.test.entity;

public class Block  {

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
