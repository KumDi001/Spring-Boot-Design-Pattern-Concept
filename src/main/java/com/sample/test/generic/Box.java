package com.sample.test.generic;

public class Box<T>  extends WildGeneric<T>{// Generic type
	
	T container;

	public T getContainer() {
		return container;
	}

	public void setContainer(T container) {
		this.container = container;
	}
	
	@Override
	public void printListIt1(T container)
	{
		this.container= container;
		System.out.println("In Boxed" + container);
	}

}
