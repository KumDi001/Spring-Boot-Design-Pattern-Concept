package com.sample.test.oopsConcepts;

import java.io.IOException;

public interface Block  extends FunctionalInterfaceTest {
	static final int blockNumber = 0;
	int roadNumber = 0;
	void funcTest();
	abstract int funcTestBlock() throws Exception;

	default void testme() throws IOException {
		System.out.println("Block Default Method");
		testme2();
	}
	default void blockTest3() throws IOException
	{
		System.out.println("in Default Method of Block Class;");
	}
	default void testme2() throws IOException{
		System.out.println("Test2 Private Block Method");
	}
	
	 default void testme2(String str) throws IOException{
		System.out.println("Test2  Default Block Method");
	}

	 static void testme2(int t) {
		System.out.println("Test2 Static Block Method");
	}


	/*
	 * public static int i = 1111;
	 * 
	 * static{ i = i-- - --i; //L1 }
	 * 
	 * { i = i++ + ++i; //L2 }
	 */
}
