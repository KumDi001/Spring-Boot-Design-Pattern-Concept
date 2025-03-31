package com.sample.test.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class Problems {

	public int maxProduct(int[] nums) {

		int sum = nums[0];
		int currSum = nums[0];

		for (int j = 1; j < nums.length; j++) {
			if (currSum * nums[j] > nums[j])
				currSum = currSum * nums[j];
			else
				currSum = nums[j];
			if (sum < currSum)
				sum = currSum;

		}
		return sum;

	}

	public boolean containsDuplicate(int[] nums) {

		Map<Integer, Long> map = Arrays.stream(nums).boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		for(Map.Entry<Integer,Long> key :map.entrySet())
		{
			if(key.getValue()>=2)
			{
				return true;
			}
		}
		return false;
	}

	static int getPermutation(int n, int r) {

		int f1 = n;
		int f2 = n - r;
		for (int i = f1 - 1; i >= 1; i--) {
			f1 = f1 * i;
		}
		for (int i = f2 - 1; i >= 1; i--) {
			f2 = f2 * i;
		}
		int perm = f1 / f2;
		return perm;
	}

	public boolean isValidBrackets(String s) {
		Stack<Character> st = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {

			// Check if the character is an opening bracket
			if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
				st.push(s.charAt(i));
			} else {

				// If it's a closing bracket, check if the stack is non-empty
				// and if the top of the stack is a matching opening bracket
				if (!st.empty() && ((st.peek() == '(' && s.charAt(i) == ')') || (st.peek() == '{' && s.charAt(i) == '}')
						|| (st.peek() == '[' && s.charAt(i) == ']'))) {
					st.pop();
				} else {

					// Unmatched closing bracket
					return false;
				}
			}

		}
		return st.empty();
	}

	void findPermutation(int number) {
		int temp = number, count = 0;
		// iteration over the specified digit
		while (temp > 0) {
			// increments the count variable by 1 i the above condition returns true
			count++;
			// divides the variable temp by 10
			temp = temp / 10;
		}
		// using vector to print the permutation of N
		int[] num = new int[count];
		// Store digits of N
		// in the vector num
		while (number > 0) {
			// finds the remainder and store the digit in vector num
			num[count-- - 1] = number % 10;
			number = number / 10;
		}
		// iterate over each permutation and find the permutations that are greater than
		// N
		while (findsNextpermutation(num)) {

			for (int i = 0; i < num.length; i++)
				// print all the permutations of N
				System.out.print(num[i]);
			// throw the cursor to the new line
			System.out.print("\n");
		}
	}

	// the user-defined function finds all the permutation greater than the number
	// itself
	boolean findsNextpermutation(int[] p) {// 3
		for (int a = p.length - 2; a >= 0; --a)
			if (p[a] < p[a + 1])
				for (int b = p.length - 1;; --b)
					if (p[b] > p[a]) {
						// swapping logic
						int t = p[a];
						p[a] = p[b];
						p[b] = t;
						for (++a, b = p.length - 1; a < b; ++a, --b) {
							// swapping logic
							t = p[a];
							p[a] = p[b];
							p[b] = t;
						}
						return true;
					}
		return false;
	}

	int findMin(int[] arr) {
		int res = 0;
		int min = arr[0];
		// Traverse over arr[] to find minimum element
		for (int i = 0; i < arr.length - 1; i++) {
			// res = Math.min(res, arr[i]);
			if (arr[i] < min) {
				min = arr[i];
				res = i;
			}
		}
		return res;

	}

	int findRotateIndex(int[] arr, int target) {

		int minIndex = findMin(arr);// 3
		// {1,6,7,2,3,4,6,5,9}
		// start- minIdex, minIndex_, high
		// int minFirst = arr[0];
		for (int i = 0; i < minIndex; i++) {
			for (int j = i + 1; j < minIndex; j++) {

				if (arr[j] < arr[i]) {
					int tem = arr[i];
					arr[i] = arr[j];
					arr[j] = tem;
				}
			}
		} // {1,6,7,2,3,4,6,5,9}
		for (int i = minIndex; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {

				if (arr[j] < arr[i]) {
					int tem = arr[i];
					arr[i] = arr[j];
					arr[j] = tem;
				}
			}
		}
		int count = 0;
		int value = -1;
		int[] arrTemp = new int[arr.length];

		for (int i = minIndex; i < arr.length; i++, count++) {
			arrTemp[count] = arr[i];
			if (arrTemp[count] == target)
				value = count;

		}
		for (int i = 0; i < minIndex; i++, count++) {
			arrTemp[count] = arr[i];
			if (arrTemp[count] == target)
				value = count;
		}
		System.out.println("value" + value);
		return value;
	}

	public void generateCombinations(int[] nums, int combinationSize, int start, List<Integer> current,
			List<List<Integer>> result) {
		// Base case: if the current combination size matches the desired size
		if (current.size() == combinationSize) {
			result.add(new ArrayList<>(current));
			return;
		}

		// Recursive case: iterate through the numbers and generate combinations
		for (int i = start; i < nums.length; i++) {
			current.add(nums[i]);
			generateCombinations(nums, combinationSize, i + 1, current, result);
			current.remove(current.size() - 1); // Backtrack
		}
	}

	public int majorityElement(int[] nums) {
		/// [0, 1, 2, 3, 4, 2, 2, 3, 3, 4]
		Map<Integer, Integer> mapCount = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (mapCount.containsKey(nums[i])) {
				mapCount.put(nums[i], mapCount.get(nums[i]) + 1);
			} else
				mapCount.put(nums[i], 1);
		}
		int major = 0;
		if (mapCount != null) {
			for (Map.Entry<Integer, Integer> key : mapCount.entrySet()) {
				if (key.getValue() >= (nums.length / 2) + 1) {
					major = key.getKey();
					System.out.println("Majority Number" + major);
				} else
					major = 0;
			}
		} else
			return 0;
		return major;
	}

	// pwddkew
	public String getLongestSubString(String str) {
		Map<Character, Integer> charIndexMap = new HashMap<>();
		int start = 0;
		int maxLength = 0;
		String maxSbString = null;
		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= start) {
				start = charIndexMap.get(currentChar) + 1;
			}

			charIndexMap.put(currentChar, i);
			int currentMaxLength = i - start + 1;

			if (currentMaxLength > maxLength) {
				maxLength = currentMaxLength;
				maxSbString = str.substring(start, i + 1);
			}
		}

		return maxSbString;

	}

	public int[] twoSum(int[] nums, int target) {
		int[] indexs = new int[2];
		for (int i = 0; i <= nums.length - 1; i++) {
			for (int j = i + 1; j <= nums.length - 1; j++) {
				if (nums[i] + nums[j] == target) {
					indexs[0] = i;
					indexs[1] = j;
				}
			}
		}
		return indexs;
	}

	public int removeDuplicates(int[] nums) {
		int[] arr = new int[nums.length];
		int j = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] != nums[i + 1]) {
				arr[j++] = nums[i];
			}
		}
		arr[j++] = nums[nums.length - 1];
		for (int i = 0; i < j; i++) {
			nums[i] = arr[i];
		}
		return j;
	}

	private static int MY_INT = 0;

	static class ChangeListener extends Thread {
		@Override
		public void run() {
			int local_value = MY_INT;
			while (local_value < 5) {
				if (local_value != MY_INT) {
					log.info("Got Change for MY_INT : {0} {}", MY_INT);
					local_value = MY_INT;
				}
			}
		}
	}

	static class ChangeMaker extends Thread {
		@Override
		public void run() {
			int local_value = MY_INT;
			while (MY_INT < 5) {
				log.info("Incrementing MY_INT to {0} {}", local_value + 1);
				MY_INT = ++local_value;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
