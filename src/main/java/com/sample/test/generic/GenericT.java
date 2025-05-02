package com.sample.test.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericT {

	public static void main(String[] args) {
		try {
			List list = new ArrayList<>();
			list.add(1); // exception due to the type, to solve it we need to use Generic Type, error
							// doesn't throw in Compile time;
			list.add("Dilip");
			System.out.println((String) list.get(0)); // type cast issue
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> list1 = new ArrayList<>();// defined with type Parameters.
		list1.add("Kumar"); // exception due to the type, it should only be in String values in this generic
							// type Class of List
		list1.add("Dilip");
		System.out.println(list1); // type check at compile time

		Box box = new Box();// without the generic type;; like Box<T> Class
		box.setContainer("Dilip");
		String str = (String) box.getContainer(); // we need to cast the values here every time
		System.out.println(str);
		box.setContainer(2);
		Integer i = (Integer) box.getContainer(); // we need to cast the values here
		System.out.println(i);

		///////////////////// Generic Type ///////////////////

		Box<String> genericBox = new Box<>();// with the generic type;; like Box<T> Class
		genericBox.setContainer("Dilip"); // not
		// genericBox.setContainer(2);// exception of type at compile time only,

		System.out.println(genericBox.getContainer());

		///////////////////////////////////
		WildGeneric<String> wild = new WildGeneric<>();
		List<Integer> intList = Arrays.asList(1, 2, 3);
		List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
		wild.printList(intList);
		wild.printList(doubleList);
		
		List<Object> intListIt = Arrays.asList(1, 2, 3);
		
		wild.printListIt(intListIt);
		wild.printListIt1(doubleList);
		
		Pair<Integer, String> pair = new Pair<>();
		
		pair.setKey(1);
		pair.setValue("Dilip");
		System.out.println(pair.getValue() + ": "+ pair.getKey());	
		
		Box<Integer> bo= new Box<>();
		bo.printListIt1(112);
		

	}

}
