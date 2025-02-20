package com.sample.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class StreamTest {

	// ArrayList----- sorting

	// [6,2,8,9,3]..pivot(can be last, first, Mode and any random value from the
	// list) and partition

	public void quickSort(int arr[], int low, int high) {
		if (low < high) {
			int pivotIndex = partition(arr, low, high);
			quickSort(arr, low, pivotIndex - 1);
			quickSort(arr, pivotIndex, high);

		}
	}

	private int partition(int[] arr, int low, int high) {
		// [2, 3, 8, 9, 6] Step1-- 2, 3,| 6, 9 ,8
		// Step1-- 2, 3,| 6, 8, 9
		// Step1-- 2, 3, 6, 8,| 9
		// Step1-- 2, 3, 6, 8, 9

		// TODO Auto-generated method stub
		// pivot = last element
		int pivot = arr[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				i++;
				// swap
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		i++;
		int temp = arr[i];
		arr[i] = pivot;
		arr[high] = temp;
		// for (int x = 0; x < arr.length; x++)
		// System.out.println(arr[x]);
		return i;
	}

	// first N number sum 1, 5

	public void firstSum(int start, int end, int sum) {

		if (start == end) {
			sum = sum + start;
			return;
		}
		sum = sum + start;
		System.out.println(sum);
		firstSum(start + 1, end, sum);

	}

	public int powerSum(int base, int power) {
		if (power == 0)
			return 1;
		if (power == 1)
			return base;
		base = base * powerSum(base, power - 1);// height =n
		return base;

	}

	public int powerSumRec(int base, int power) {
		if (power == 0)
			return 1;
		if (power == 1)
			return base;
		if (power % 2 == 0)// even
		{
			return powerSumRec(base, power / 2) * powerSumRec(base, power / 2);
		} else// odd
		{
			return powerSumRec(base, power / 2) * powerSumRec(base, power / 2) * base;
		}

	}

	// selection sort;
	public void selectSort(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		for (int x = 0; x < arr.length; x++)
			System.out.println(arr[x]);
	}

	public void streamKT() {
		int arr[] = new int[] { 1, 2, 3, 4 };
		Vector<Integer> v = new Vector();
		Hashtable<Integer, String> h = new Hashtable(); // sechronized
		// Adding the elements into the
		// vector
		v.addElement(1);
		v.addElement(2);
		v.get(0);
		v.get(1);

		// Adding the element into the
		// hashtable
		h.put(1, "geeks");
		h.put(2, "4geeks");
		h.entrySet();
		// Array instance creation requires [],
		// while Vector and hastable require ()
		// Vector element insertion requires addElement(),
		// but hashtable element insertion requires put()

		// Accessing the first element of the
		// array, vector and hashtable
		/*
		 * System.out.println(arr[0]); System.out.println(v.elementAt(0));
		 * System.out.println(h.get(1));
		 */
		List<String> students = new ArrayList<>();
		students.add("Dilip");
		students.add("Sandeep");
		students.add("Sandeep");
		students.add("Sandeep");

		students.add("Xm");
		students.add("DKm");

		students.add("Shankar");
		students.add("Sandeep");

		students.add("Neeraj");
		students.add("Sandeep");
		Set<String> flt =  new LinkedHashSet<>();
		long count = students.stream().map(e->e.toUpperCase()).count();
		System.out.println("Stream Opp" + count);
		// 5 ways to create Stream.
		List<Integer> num = Arrays.asList(100, 220, 330, 400, 500);
		List<Integer> filterList = num.stream().filter((Integer i) -> i < 400).collect(Collectors.toList());

		Integer[] salary = { 30000, 40000, 30000, 50000 };
		Stream<Integer> salaryStream = Arrays.stream(salary).sorted().skip(1).limit(2);
		 salaryStream.collect(Collectors.toList()).forEach(e->System.out.println("nd and third max"+e));
		double avg= salaryStream.mapToInt(x->x).summaryStatistics().getAverage();

		// Static Method
		Stream<Integer> salaryStream1 = Stream.of(30000, 40000, 30000, 50000);

		// Stream builder
		Stream.Builder<Integer> streamBuilder = Stream.builder();
		streamBuilder.add(100).add(200).add(300).add(400);
		Stream<Integer> streamFromStreamBuilder = streamBuilder.build();

		// Stream iterator::
		Stream<Integer> stremaIterator = Stream.iterate(1000, (Integer i) -> i + 500).limit(5);

		// intermediatory
		// filter()
		List<Integer> test = Arrays.asList(100, 220, 330, 400, 500);
		List<Integer> filteredList = test.stream().filter((Integer i) -> i < 400).collect(Collectors.toList());
		// map()
		List<String> name = Arrays.asList("Dilip", "Sandeep", "Neeraj", "Shankar", "Mahto");
		Stream<String> nameStream = name.stream().map((String n) -> n.toLowerCase());
		// flatMap()
		List<List<String>> studList = Arrays.asList(Arrays.asList("Ram", "Shyam", "Rakesh"),
				Arrays.asList("Dilip", "Sandeep", "Neeraj"), Arrays.asList("Mahto", "Shankar", "Kumar"),
				Arrays.asList("Tree", "System", "Test"));
		Stream<String> streamStud = studList.stream()
				.flatMap((List<String> str) -> str.stream().map((String val) -> val.toLowerCase()));

		// distinct()
		Stream<String> streamDistinct = streamStud.distinct();

		// Sorted()
		// Stream<String> streamSorted = streamStud.distinct().sorted();
		Stream<Integer> streamSortedDesc = salaryStream1.distinct().sorted((Integer str1, Integer str2) -> str1 - str2);

		// peek()

		Stream<Integer> peekStream = test.stream().filter((Integer i) -> i > 200)
				.peek((Integer i) -> System.out.println(i));
		// List<Integer> peekList = peekStream.collect(Collectors.toList());

		// MaptoInt()
		// MaptoLong()
		// MaptoDouble()

		List<String> mapToInt = Arrays.asList("1", "2", "44", "66", "77", "77", "44", "99");
		IntStream intStream = mapToInt.stream().mapToInt((String str) -> Integer.parseInt(str));

		int[] intList = intStream.toArray();
		IntStream filterStream = Arrays.stream(intList).filter((int i) -> i > 2).distinct();
		int[] filteredIntList = filterStream.toArray();
		for (int i : filteredIntList) {
			System.out.print(" " + i);
		}

		// Why Intermediate is Lazy;

		Stream<Integer> peekStream1 = test.stream().filter((Integer i) -> i > 200)
				.peek((Integer i) -> System.out.println(i));
		// this will not print the integer 'i' after peek method call, this is because
		// the Lazy, stream should have a terminate call to have this working.
		// List<Integer> peekList = peekStream.collect(Collectors.toList());
		// Sequence of stream
		// filter, map, peek-- take the stream elements one by once 220, 330 then 400
		// it not requires the entire elements to work on , nut gor sort method will
		// reuire the
		// entire elements to sort it out.

		Stream<Integer> streamSequences = test.stream().filter((Integer i) -> i > 200)
				.peek((Integer i) -> System.out.println("After Filter" + i)).map((Integer i) -> (i * -1))
				.peek((Integer i) -> System.out.println("After Negating" + i)).sorted()
				.peek((Integer i) -> System.out.println("After Sorted" + i));
		List<Integer> sortedList = streamSequences.collect(Collectors.toList());

		for (Integer i : sortedList) {
			System.out.println("\t" + i);
		}
		// terminal operations in stream.

		// forEach()
		// toArray()
		// collect()
		// min()
		// max()
		// count()
		// anyMatch()
		// allMatch()
		// noneMatch()
		// findFirst()

		sortedList.stream().filter((Integer i) -> i < 200)
				.forEach((Integer val) -> System.out.println("ForEach()" + val));

		Integer[] sList = sortedList.stream().filter((Integer i) -> i < 200).toArray((int i) -> new Integer[4]);

		for (int it : sList) {
			System.out.println("sList toArray" + it);
		}
		// reduce()
		Optional<Integer> reducedList = sortedList.stream().filter((Integer i) -> i < 200)
				.reduce((Integer i, Integer j) -> i + j);

		System.out.println("Reduced Value " + reducedList.get());

		// collect()
		List<Integer> collectTerminate = sortedList.stream().map((Integer i) -> i * 2).collect(Collectors.toList());
		Optional<Integer> minOfList1 = collectTerminate.stream().min((Integer i, Integer j) -> i - j);
		System.out.println("Min () value of List " + minOfList1);

		Optional<Integer> minOfList2 = collectTerminate.stream().min((Integer i, Integer j) -> j - i);
		System.out.println("Min () value of List " + minOfList2);

		int[] arrT = { 1, 2, 3, 4, 5 };
		int n = arrT.length;
		int[] arr1 = new int[n];
		int j = 0;
		for (int i = 0; i < arrT.length; i++) {
			arr1[i] = arrT[n - 1 - j];

			System.out.println(arr1[i]);
			if (j == (n - 1))
				break;
			j++;
		}
		arrT = arr1;
		System.out.println(arrT);

		int[] arrTst = { 88, 9, 88, 9, 44, 33, 44, 67, 556, 887, 998, 22, 33445, 33445 };
		int size = arrTst.length;
		int temp = arrTst[0];
		arrTst[0] = arrTst[size - 1];
		if (size != 1) {
			for (int i = 0; i < size - 2; i++) {
				arrTst[size - 1 - i] = arrTst[size - 1 - (i + 1)];
			}
			arrTst[1] = temp;
		}

		int size1 = arrTst.length;
		int tem;
		List<Integer> duplicate = new ArrayList<Integer>();
		for (int i = 0; i < size1 - 1; i++) {
			tem = arrTst[i];
			for (int jt = 0; jt <= size1 - 1; jt++) {
				if (i != jt) {
					if (tem == arrTst[jt]) {
						duplicate.add(tem);
						break;
					}
				}
			}
		}
		ArrayList<Integer> newList = new ArrayList<>();

		// Traverse through the first list
		for (Integer element : duplicate) {

			// If this element is not present in newList
			// then add it
			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		String sp = "Programming";

		Map<Character, Long> countMap = sp.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));

		System.out.println("Character Occurrences:");

		countMap.forEach((Character k, Long s) -> System.out.println(k + " : " + s));

		// ##################################################################

		String inputString = "roPgramming";
		HashMap<Character, Long> charCountMap = new HashMap<Character, Long>();

		// Converting given string to char array

		char[] strArray = inputString.toCharArray();

		// checking each char of strArray
		for (char c : strArray) {
			if (charCountMap.containsKey(c)) {

				// If char is present in charCountMap,
				// incrementing it's count by 1
				charCountMap.put(c, charCountMap.get(c) + 1);
			} else {

				// If char is not present in charCountMap,
				// putting this char to charCountMap with 1 as it's value
				charCountMap.put(c, (long) 1);
			}
		}

		// Printing the charCountMap
		for (Map.Entry entry : charCountMap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		if (charCountMap.equals(countMap)) {
			System.out.println("is Anagram");
		} else
			System.out.println("is not Anagram");

		String str = "GEEKSFORGEEKS";
		int np = str.length();
		int res = 0;

		for (int i = 0; i < np; i++) {

			// Initializing all characters as not visited
			boolean[] visited = new boolean[256];

			for (int jp = i; jp < np; jp++) {

				// If current character is visited,
				// Break the loop
				if (visited[str.charAt(jp)]) {
					break;
				} else {

					// Else update the result if this
					// window is larger, and mark current
					// character as visited.
					res = Math.max(res, jp - i + 1);
					visited[str.charAt(jp)] = true;
				}
			}
		}
		List<Integer> arrtest = new ArrayList<Integer>();
		arrtest.add(1);
		arrtest.add(3);
		arrtest.add(5);
		arrtest.add(7);
		arrtest.add(9);

		arrtest.stream().filter((Integer nn) -> nn > 2).map(Long::valueOf).peek((Long np1) -> System.out.print(np1))
				.forEach((Long nss) -> System.out.println(nss));

	}
}
