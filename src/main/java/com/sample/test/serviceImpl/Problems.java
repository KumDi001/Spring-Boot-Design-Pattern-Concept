package com.sample.test.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.sample.test.service.ProducerService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class Problems {

	private final ProducerService producerService;

	Problems(ProducerService producerService) {
		this.producerService = producerService;
	}

	public static void main(String[] args) {
		String str = "leet**cod*e";

		char[] cList = str.toCharArray();
		Stack<Character> stk = new Stack<>();

		for (char c : cList) {

			if (c == '*')
				stk.pop();
			else
				stk.push(c);
		}
		System.out.println(stk.stream().map(st -> st.toString()).collect(Collectors.joining("")));

		int[] asteroids = { 10, 2, -5 };

		Stack<Integer> stack = new Stack<>();

		for (int asteroid : asteroids) {
			while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
				int top = stack.peek();

				if (Math.abs(top) > Math.abs(asteroid)) {
					// Right-moving asteroid survives, left asteroid is destroyed
					asteroid = 0;
				} else if (Math.abs(top) < Math.abs(asteroid)) {
					// Left-moving asteroid survives, pop right asteroid
					stack.pop();
				} else {
					// Both asteroids have the same size and destroy each other
					stack.pop();
					asteroid = 0;
				}
			}

			// If asteroid is not destroyed, add it to the stack
			if (asteroid != 0) {
				stack.push(asteroid);
			}
			stack.stream().mapToInt(Integer::intValue).toArray(); // (Collectors.joining(""));
		}
		int[] nums = { 5, 2, 7, 1, 4 };
		minimumPairRemoval(nums);

		// int [] original= {1,2,3,4,5,6,7,8,9,12,34,56};
		int[] nums1 = { 4, 5, 6, 7, 8, 9, 10, 13, 12, 34, 56, 1, 2, 3 }; // mid =6/2=3 rotated array on 4
		System.out.println(nums1[rotatedArraySearch(nums1)]);

		System.out.println(recursiveBinarySearch(nums1, 0, nums1.length - 1, 34));

		bubbleSort(nums);
		selectionSort(nums);
		insertionSort(nums1);

	}

	static void bubbleSort(int[] arr) {

		System.out.println("Before bubbleSort");
		for (int i : arr) {
			System.out.print(i);
		}
		System.out.println();
		int swap;
		for (int i = 0; i < arr.length; i++) {
			swap = 0;
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					// Swap
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swap++;
				}
			}
			for (int x : arr) {
				System.out.print(x);
			}
			System.out.println("Swap::" + swap);
		}
		System.out.println("After bubbleSort");
		for (int i : arr) {
			System.out.print(i);
		}

	}

	static void selectionSort(int[] arr) {

		// int[] nums1 = { 4, 5, 6, 7, 8, 9, 12, 34, 56, 1, 2, 3 };
		System.out.println();
		System.out.println("Before selectionSort");
		for (int i : arr) {
			System.out.print(i);
		}
		System.out.println();
		int swap = 0;
		int minIndex = -1;
		for (int i = 0; i < arr.length; i++) {
			minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[minIndex] > arr[j]) {
					minIndex = j;
				}
			}

			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
			swap++;
			for (int x : arr) {
				System.out.print(x);
			}
			System.out.println("Swap::" + swap);
		}
		System.out.println("After selectionSort");
		for (int i : arr) {
			System.out.print(i);
		}

	}

	static void insertionSort(int[] arr) {

		// int[] nums1 = { 4, 5, 6, 10, 11, 7, 8, 9, 12, 34, 56, 1, 2, 3 };
		System.out.println();
		System.out.println("Before insertionSort");
		for (int i : arr) {
			System.out.print(i);
		}
		System.out.println();
		int j;
		int key;
		for (int i = 1; i < arr.length; i++) {
			j = i - 1;
			key = arr[i];
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;

		}
		System.out.println("After insertionSort");
		for (int i : arr) {
			System.out.print(i);
		}

	}

	public static int rotatedArraySearch(int[] nums1) {

		int index = getPivot(nums1, 0, nums1.length - 1);
		int found = binarySearch(nums1, 0, index, 9);
		if (found == -1)
			found = binarySearch(nums1, index, nums1.length - 1, 9);
		return found;
	}

	public static int getPivot(int[] arr, int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;
			if (arr[mid] > arr[mid + 1])
				return mid;
			else if (arr[mid] < arr[mid - 1])
				return mid - 1;
			else if (arr[mid] > arr[left])
				left = mid + 1;
			else
				right = mid - 1;

		}
		return right;
	}

	public static int binarySearch(int[] arr, int left, int right, int key) {

		// 10,4,9,7,2,5,6, mid =6/2=3
		// key 9
		while (left <= right) {
			int mid = (left + right) / 2;
			if (key > arr[mid])
				left = mid + 1;
			else if (arr[mid] == key)
				return mid;
			else
				right = mid - 1;

		}

		return -1;

	}

	public static int recursiveBinarySearch(int[] arr, int left, int right, int key) {

		// 10,4,9,7,2,5,6, mid =6/2=3
		// key 9
		if (left <= right) {
			int mid = (left + right) / 2;
			if (key > arr[mid])
				return recursiveBinarySearch(arr, mid + 1, right, key);
			else if (arr[mid] == key)
				return mid;
			else
				return recursiveBinarySearch(arr, left, mid - 1, key);
		}
		return -1;
	}

	public static int minimumPairRemoval(int[] nums) {
		// [5 2 7 1]
		List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

		int count = 0;
		while (!isIncreasing(list)) {

			int minSum = Integer.MAX_VALUE;
			int indexToMerge = -1;

			for (int i = 0; i < list.size() - 1; i++) {
				int sum = list.get(i) + list.get(i + 1);
				if (sum < minSum) {
					minSum = sum;
					indexToMerge = i;
				}
			}
			int toMergeValue = list.get(indexToMerge) + list.get(indexToMerge + 1);
			list.remove(indexToMerge + 1);
			list.set(indexToMerge, toMergeValue);
			count++;
		}

		return count;
	}

	private static boolean isIncreasing(List<Integer> nums) {
		for (int i = 0; i < nums.size() - 1; i++) {
			if (nums.get(i) > nums.get(i + 1))
				return false;
		}
		return true;
	}

	public int compress(char[] chars) {

		List<Character> result = new ArrayList<>();
		result.add(chars[0]);
		int count = 1;
		for (int i = 0; i < chars.length - 1; i++) {
			if (chars[i] == chars[i + 1]) {
				count++;
			} else {
				if (count >= 10) {
					String numberString = Integer.toString(count);
					for (int j = 0; j < numberString.length(); j++) {
						result.add(numberString.charAt(j));
					}
				} else
					result.add(String.valueOf(count).toCharArray()[0]);

				count = 1;
				result.add(chars[i + 1]);
			}
			if ((chars[i] == chars[i + 1]) && (i + 1 == chars.length - 1)) {
				if (count >= 10) {
					String numberString = Integer.toString(count);
					for (int j = 0; j < numberString.length(); j++) {
						result.add(numberString.charAt(j));
					}
				} else
					result.add(String.valueOf(count).toCharArray()[0]);
			}
		}
		return result.size();
	}

	public String reverseVowels(String s) {
		char[] cList = s.toCharArray();
		int left = 0;
		int right = s.length() - 1;
		boolean flag = false;
		boolean flag1 = false;
		for (int x = 0; x < s.length(); x++) {
			for (int i = left; i <= right; i++) {
				if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o'
						|| s.charAt(i) == 'u' || s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I'
						|| s.charAt(i) == 'O' || s.charAt(i) == 'U') {
					flag = true;
					break;
				} else
					left++;
			}

			for (int i = right; i > left; i--)
				if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o'
						|| s.charAt(i) == 'u' || s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I'
						|| s.charAt(i) == 'O' || s.charAt(i) == 'U') {
					flag1 = true;
					break;
				} else
					right--;
			if (left < right && flag && flag1) {
				char temp = cList[left];
				cList[left] = cList[right];
				cList[right] = temp;
				left++;
				right--;
				flag = false;
			}
		}
		return String.valueOf(cList);

	}

	public boolean uniqueOccurrences(int[] arr) {
		Map<Integer, Long> countMap = Arrays.stream(arr).boxed()
				.collect(Collectors.groupingBy(n -> n, Collectors.counting()));

		// Check if all counts are equal
		boolean allEqual = countMap.values().stream().distinct().count() == 1;

		// Check if all counts are unique
		boolean allUnique = countMap.values().stream().distinct().count() == countMap.values().size();
		return allUnique;
	}

	public String mergeAlternately(String word1, String word2) {
		char[] cList1 = word1.toCharArray();
		char[] cList2 = word2.toCharArray();
		int n1 = cList1.length;
		int n2 = cList2.length;
		char[] result = new char[n1 + n2];
		int i = 0;
		int j = 0;
		int x = 0;
		while (x < n1 + n2) {
			if (i < n1 && j < n2) {
				result[x] = cList1[i];
				x++;
				result[x] = cList2[j];
				x++;
				i++;
				j++;
			} else if (n1 < n2) {
				while (j < n2) {
					result[x] = cList2[j];
					x++;
					j++;
				}
			} else {
				while (i < n1) {
					result[x] = cList1[i];
					x++;
					i++;
				}
			}
		}
		return String.valueOf(result);
	}

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
		for (Map.Entry<Integer, Long> key : map.entrySet()) {
			if (key.getValue() >= 2) {
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
