package com.sample.test.util;

import java.util.Arrays;

public class NagarroTest {
	public static void main(String[] args) {

		String str = "this_is_a_variable";
		System.out.println(convertStr(str));

		String str2 = "thisIsAVariable";
		System.out.println(convertStr(str2));

		int[][] allList = { { 75, 76, 65, 87, 87 }, { 78, 76, 68, 56, 89 }, { 67, 87, 78, 77, 65 }

		};

		SortStudentmarks(3, 5, allList);

		System.out.println(anagramCheck("abc", "dca"));
		String ascii ="796115110113721110141108";
		PasswordASCII(ascii);
		
	}

	static String convertStr(String str) {
		// thisIsAVariable;
		// this_is_a_variable;
		String result = "";
		if (str.contains("_")) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '_') {
					i++;
					result = result + (char) (str.charAt(i) - 32);
				} else
					result = result + str.charAt(i);
			}
		} else {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
					result = result + "_";
					result = result + (char) (str.charAt(i) + 32);
				} else
					result = result + str.charAt(i);
			}
		}

		return result;

	}

	public static int[] SortStudentmarks(int student, int marks, int[][] all) {

		int[][] list = new int[student][marks];
		int min = all[0][1] + all[1][0] + all[2][0];
		int indexToDelete = 0;
		for (int i = 1; i < marks; i++) {
			int sum = 0;
			for (int j = 0; j < student; j++) {
				sum = sum + all[j][i];
			}
			if (sum < min)
				indexToDelete = i;
			min = Math.min(min, sum);

		}
		for (int i = 0; i < student; i++) {
			all[i][indexToDelete] = 0;
		}
		int[] result = new int[student];
		for (int i = 0; i < student; i++) {
			int sum = 0;
			for (int j = 0; j < marks; j++) {
				sum = sum + all[i][j];
			}

			result[i] = sum;
		}
		System.out.print(result);

		return result;

	}

	static boolean anagramCheck(String s1, String s2) {
		char[] s1List = s1.toCharArray();
		char[] s2List = s2.toCharArray();
		Arrays.sort(s1List);
		Arrays.sort(s2List);

		if (s1.length() == s2.length()) {
			for (int i = 0; i < s1.length(); i++) {
				if (!(s1List[i] == (s2List[i])))
					return false;
			}
		}
		return true;
	}

	static String PasswordASCII(String ascii) {

		// 796115110113721110141108

		StringBuilder strBuild = new StringBuilder(ascii);
		String reverse = strBuild.reverse().toString();
		StringBuilder result = new StringBuilder("");
		for (int i = 0; i < reverse.length();) {
			for (int j = i + 2 ; j < reverse.length()+2; j++) {
				if (Integer.valueOf(reverse.substring(i, j)) == 32
						|| (Integer.valueOf(reverse.substring(i, j)) >= 65
								&& Integer.valueOf(reverse.substring(i, j)) <= 90)
						|| (Integer.valueOf(reverse.substring(i, j)) >= 97
								&& Integer.valueOf(reverse.substring(i, j)) <= 99)) {
					result.append(String.valueOf( (char)Integer.parseInt(reverse.substring(i, j))));
					i = i+2;
					break;
				} else {
					j++;
					if (Integer.valueOf(reverse.substring(i, j)) >= 100
							&& Integer.valueOf(reverse.substring(i, j)) <= 122) {
						result.append(String.valueOf( (char)Integer.parseInt(reverse.substring(i, j))));
						i = i + 3;
						break;
					}

				}
			}
		}

		return ascii;

	}
}
