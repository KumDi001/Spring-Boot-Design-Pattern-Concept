package com.sample.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sample.test.entity.Address;
import com.sample.test.entity.Employees;
import com.sample.test.factory.AndroidDeveloperFactory;
import com.sample.test.factory.EmployeeFactory;
import com.sample.test.factory.WebDeveloperFactory;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Log4j2
public class TestApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);

		Address y = new Address();
		System.out.println(y.counter); // L5.
		//Factory call from the client 
		Employees emp =  EmployeeFactory.getEmployee("Android Developer");
		emp.getSalary();
		Employees emp1 =  EmployeeFactory.getEmployee("Web Developer");
		emp1.getSalary();
		//AbstractFactory Client;
		Employees e3 = EmployeeFactory.getEmployees(new AndroidDeveloperFactory());
		e3.getSalary();

		Employees e4 = EmployeeFactory.getEmployees(new WebDeveloperFactory());
		e4.getSalary();

		/*
		 * final AtomicInteger counter = new AtomicInteger(0); System.out.println(
		 * "**************** START: Total Bean Objects:  ******************" +
		 * context.getBeanDefinitionCount());
		 * 
		 * Arrays.asList(context.getBeanDefinitionNames()).forEach(beanName -> {
		 * System.out.println("{} Bean Name: {} " + counter.incrementAndGet() + " " +
		 * beanName); });
		 * 
		 * StreamTest streamService = (StreamTest) context.getBean("streamTest");
		 * streamService.streamKT();
		 * 
		 * int[] arrQuick = { 6, 2, 8, 9, 3 }; streamService.quickSort(arrQuick, 0,
		 * arrQuick.length - 1);
		 * 
		 * int power = streamService.powerSumRec(2, 10); System.out.println("Power" +
		 * power);
		 */
		String ransomNote = "aa";

		String magazine = "aab";
		Map<Character, Integer> ransomNoteMap = new HashMap<Character, Integer>();
		for (int i = 0; i < ransomNote.length(); i++) {
			if (!ransomNoteMap.containsKey(ransomNote.charAt(i)))
				ransomNoteMap.put(ransomNote.charAt(i), 1);
			else
				ransomNoteMap.put(ransomNote.charAt(i), ransomNoteMap.get(ransomNote.charAt(i)) + 1);
		}

		Map<Character, Integer> magazineMap = new HashMap<Character, Integer>();
		for (int i = 0; i < magazine.length(); i++) {
			if (!magazineMap.containsKey(magazine.charAt(i)))
				magazineMap.put(magazine.charAt(i), 1);
			else
				magazineMap.put(magazine.charAt(i), magazineMap.get(magazine.charAt(i)) + 1);
		}
		int count = 0;
		for (Map.Entry<Character, Integer> magazine1 : magazineMap.entrySet()) {
			char key = magazine1.getKey();
			int value = magazine1.getValue();
			if (ransomNoteMap.containsKey(key) &&
					ransomNoteMap.get(key)<=value) {
				count++;
				System.out.println("True");
			}
		}

		if (count <= ransomNoteMap.size())
			System.out.println("String is a ransome String");
		else
			System.out.println("String is not a ransome String");

		int[] nums1 = { 1, 2, 3, 0, 0, 0 };
		int m = 3;
		int[] nums2 = { 2, 5, 6 };
		int n = 3;
		// nums[m+n]//1,2,5,4,0,6

		int[] temp = new int[m + n];

		for (int i = 0; i < m; i++) {
			temp[i] = nums1[i];
		}
		for (int i = m, j = 0; i < temp.length; i++, j++) {
			temp[i] = nums2[j];
		}
		for (int i = 0; i < m + n; i++) {
			System.out.println(temp[i]);
		}

		for (int i = 0; i < m + n; i++) {
			for (int j = i + 1; j < m + n; j++) {
				if (temp[i] > temp[j]) {
					int tem = temp[i];
					temp[i] = temp[j];
					temp[j] = tem;
				}
			}
		}

		for (int i = 0; i < m + n; i++) {
			System.out.println(temp[i]);
		}

		int[] prices = { 7, 6, 4, 3, 1 };

		int profit = 0;

		List<Integer> profitList = new ArrayList<>();
		for (int i = 0; i < prices.length; i++) {
			for (int j = i + 1; j < prices.length; j++) {

				if (prices[j] > prices[i]) {
					profitList.add(prices[j] - prices[i]);
				}

			}

		}
		if (!profitList.isEmpty())
			profit = Collections.max(profitList);
		System.out.println(profit);
		String s = "[{}]{}[{]";
		boolean isValid = isValidBrackets(s);
		System.out.println("String status:" + isValid);
		/*
		 * String check = "pwddkew"; String maxSubString = getLongestSubString(check);
		 * System.out.println(maxSubString);
		 * 
		 * // Volatile Service new ChangeListener().start(); new ChangeMaker().start();
		 * 
		 * Runnable thread2 = () -> { MY_INT++; System.out.println(MY_INT +
		 * "entering Thread2"); }; thread2.run();
		 * 
		 * Runnable thread1 = () -> System.out.println(++MY_INT + "entering Thread1");
		 * thread1.run(); int[] nms = { 3, 2, 4 }; int[] indexs = twoSum(nms, 6);
		 * System.out.println(indexs[0] + "\t" + indexs[1]); int[] nums = { 3, 3, 4 };
		 * int out = removeDuplicates(nums); System.out.println(out);
		 * 
		 * majorityElement(nums);
		 * 
		 * int[] nums1 = { 1, -1, 4, 5, 2, 5, 2, 8 }; // Input array int combinationSize
		 * = 4; // Desired combination size int target = 14;
		 * 
		 * List<List<Integer>> result = new ArrayList<>(); generateCombinations(nums1,
		 * combinationSize, 0, new ArrayList<>(), result);
		 * 
		 * // Print the combinations System.out.println("Combinations of size " +
		 * combinationSize + ":"); Set<List<Integer>> targetCombination = new
		 * LinkedHashSet<>(); for (List<Integer> combination : result) {
		 * System.out.println(combination); if (combination.get(0) + combination.get(1)
		 * + combination.get(2) + combination.get(3) == target) {
		 * targetCombination.add(combination); }
		 * 
		 * } System.out.println("targetCombination" + targetCombination);
		 * 
		 * int[] nums2 = { 4, 5, 6, 7, 0, 1, 2 }; findRotateIndex(nums2, 0);
		 * System.out.println(getPermutation(5, 2));
		 * 
		 * findPermutation(12);
		 */

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

	public static boolean isValidBrackets(String s) {
		Stack<Character> st = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {

			// Check if the character is an opening bracket
			if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
				st.push(s.charAt(i));
			} else {

				// If it's a closing bracket, check if the stack is non-empty
				// and if the top of the stack is a matching opening bracket
				if (!st.empty() &&
						((st.peek() == '(' && s.charAt(i) == ')') ||
								(st.peek() == '{' && s.charAt(i) == '}') ||
								(st.peek() == '[' && s.charAt(i) == ']'))) {
					st.pop();
				} else {

					// Unmatched closing bracket
					return false;
				}
			}

		}
		return st.empty();
	}

	static void findPermutation(int number) {
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
	static boolean findsNextpermutation(int[] p) {// 3
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

	static int findMin(int[] arr) {
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

	static int findRotateIndex(int[] arr, int target) {

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

	public static void generateCombinations(int[] nums, int combinationSize, int start, List<Integer> current,
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

	public static int majorityElement(int[] nums) {
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
	public static String getLongestSubString(String str) {
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

	public static int[] twoSum(int[] nums, int target) {
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

	public static int removeDuplicates(int[] nums) {
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