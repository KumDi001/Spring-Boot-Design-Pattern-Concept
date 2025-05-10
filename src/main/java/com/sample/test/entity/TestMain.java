package com.sample.test.entity;

interface DiscountStrategy {
	double applyDiscount(double price);
}

class NoDiscount implements DiscountStrategy {
	public double applyDiscount(double price) {
		return price;
	}
}

class SeasonalDiscount implements DiscountStrategy {
	public double applyDiscount(double price) {
		return price * 0.9;
	}
}

class SummerDiscount implements DiscountStrategy {
	public double applyDiscount(double price) {
		return price * 0.7;
	}
}

class PriceCalculator {
	public double calculatePrice(double price, DiscountStrategy strategy) {

		/*
		 * if (strategy instanceof SeasonalDiscount) { SeasonalDiscount r =
		 * (SeasonalDiscount) strategy; return price * 0.9; } else if (strategy
		 * instanceof SummerDiscount) { SummerDiscount c = (SummerDiscount) strategy;
		 * return price * 0.7; }
		 */// OC - non compliant

		return strategy.applyDiscount(price);// compliant
	}
}

class Bird {
	public void fly() {
		System.out.println("Bird is flying");
	}

	public void eats() {
		System.out.println("Bird is Eating");
	}
}

class Sparrow extends Bird {
}

class Ostrich extends Bird {
	@Override
	public void fly() {
		throw new UnsupportedOperationException("Ostrich can't fly");
	}
}

abstract class Birds {
	void eat() {
	}
}


class Sparrows extends FlyingBirds {
	//
}

class FlyingBirds extends Birds {
	public void fly() {
		System.out.print("Flying bird is flying");
	}
}

class Ostrichs extends Birds {
	//
}

public class TestMain {
	public void testme() {
		// TODO Auto-generated method stub
		System.out.println("TestMe call");
		PriceCalculator cal = new PriceCalculator();
		cal.calculatePrice(100, new SeasonalDiscount());
	}

	Bird b = new Bird();
}