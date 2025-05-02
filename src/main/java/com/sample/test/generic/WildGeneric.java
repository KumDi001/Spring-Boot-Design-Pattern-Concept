package com.sample.test.generic;

import java.util.List;

public class WildGeneric<T> {

	T value;

	public void printList(List<? extends Number> intList) { // intList is bounded to subclass of Number
		for (Number number : intList) {
			System.out.println(number);
		}
	}

	public void printListIt(List<? super Integer> intList) { // intList is bounded to subclass of Number
		for (Object number : intList) {
			System.out.println(number);
		}
	}

	public void printListIt1(List<?> intList) { // intList is bounded to subclass of Number
		for (Object number : intList) {
			System.out.println(number);
		}
	}

	public void printListIt1(T value) {
		System.out.println(value);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}