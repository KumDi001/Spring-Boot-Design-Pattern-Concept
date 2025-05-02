package com.sample.test.oopsConcepts;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component("area")
public class Area  implements Block {

	public int testme1() throws IOException {
		return 0;
		// TODO Auto-generated method stub
	}
	public void testme2(int i)
	{
		
	}
	@Override
	public void funcTest() {
		// TODO Auto-generated method stub
		System.out.println("area Class /// implementing class of Functional interface");
		
	}
	@Override
	public int funcTestBlock() throws Exception {
		return 0;
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * @Override protected void testme2() {
	 * 
	 * }
	 */
}
