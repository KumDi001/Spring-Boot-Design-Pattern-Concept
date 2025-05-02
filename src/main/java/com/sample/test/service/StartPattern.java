package com.sample.test.service;

public class StartPattern {

	public static void main(String[] args) {
		/*
		 * 1.
		 *
		 **
		 ***
		 ****
		 */
		System.out.println("normal pattern");

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		/*
		 * 2. reverse normal pattern
		 *** 
		 **
		 *
		 */
		System.out.println("reverse normal pattern");

		for (int i = 4; i > 0; i--) {
			for (int j = i; j > 0; j--) {
				System.out.print("*");
			}
			System.out.println();
		}

		/*
		 * 3. reverse normal pattern
		 **** 
		 ***
		 **
		 *
		 */
		System.out.println("reverse normal1 pattern");

		for (int i = 0; i < 4; i++) {
			for (int j = i; j < 4; j++) {
				System.out.print("*");
			}
			System.out.println();
			for (int x = 0; x <= i; x++) {
				System.out.print(" ");
			}
		}

		System.out.println("reverse normal2 pattern");

		for (int i = 0; i < 4; i++) {
			for (int x = 0; x < 4 - i; x++) {
				System.out.print(" ");
			}
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();

		}

		System.out.println("Pyramid + Diamond  pattern");

		for (int i = 0; i < 4; i++) {

			for (int j = i; j < 4; j++) {
				System.out.print(" ");
			}
			for (int x = 0; x <= i; x++) {
				System.out.print("* ");
			}
			System.out.println();

		}
		for (int i = 0; i < 4; i++) {
			for (int x = 0; x <= i; x++) {
				System.out.print(" ");
			}
			for (int j = i; j < 4; j++) {
				System.out.print("* ");
			}
			System.out.println();

		}

		System.out.println("Cross diagonal pattern");

		for (int i = 0; i < 5; i++) {

			for (int x = 0; x < i; x++) {
				System.out.print(" ");
			}

			System.out.print("*");
			if (i < 5 / 2) {
				for (int x = i + 1; x < 5 - i; x++) {
					System.out.print(" ");
				}
				System.out.print("*");// 1
			} else {
				for (int x = 5/2; x >i; x--) {
					System.out.print("*");
				}
				System.out.print(" ");
			}
			System.out.println();
		}

		/*
		 * 4. square pattern ***
		 ***
		 ***
		 */
		System.out.println("square pattern ");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
