package com.sample.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.Vector;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.util.comparator.Comparators;

import com.sample.test.entity.Department;
import com.sample.test.entity.Employee;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class StreamTest {

	public static void main(String[] args) {

		// 1.Question: Given a list of integers, use the Stream API to find the sum of
		// all even numbers in the list.
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.summarizingInt(a -> a)).getSum();

//		System.out.println(numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.summarizingInt(a -> a)).getAverage());
		// Find the longest string from a list of strings using the Stream API.
		List<String> words = Arrays.asList("apple", "banana", "cherry", "blueberry", "apple");
		// System.out.println(words.stream().collect(Collectors.groupingBy(s->s.length())));
		// System.out.println(words.stream().collect(Collectors.toMap(s ->
		// s.toUpperCase(), v -> 1, (x, y) -> x + y)));
		// System.out.println(words.stream().collect(Collectors.toMap(s->s,v->1,(x,y)->x+y)));

		words.stream().mapToInt(s -> s.length()).max();
		// System.out.println(words.stream().max(Comparator.comparingInt(s->s.length())).get());

		// Question: Given a list of integers, count how many numbers are divisible by
		// 3.

		List<Integer> numbers1 = Arrays.asList(1, 3, 5, 6, 9, 12, 15);
		// System.out.println(numbers1.stream().filter(n->n%3==0).count());

		// Question: Use the reduce operation to calculate the product of all numbers in
		// a list.

		List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);
		System.out.println((numbers2.stream().reduce(0, (a, b) -> a * b)));

		String str = "blueberry";
		char[] chars = str.toCharArray();
		Stream<Character> streamChars = str.chars().mapToObj(o -> (char) o);

		Stream<Character> charSteam = IntStream.range(0, chars.length).mapToObj(obj -> chars[obj]).sorted();

		System.out.println(charSteam);
		Map<Character, Long> mapCount = streamChars.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		System.out.println(mapCount);

		List<Character> charsLIst = mapCount.entrySet().stream().mapToInt(i -> i.getKey()).mapToObj(c -> (char) c)
				.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(charsLIst);

		String reversed = new StringBuilder(str).reverse().toString();
		
		final ArrayList<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("World");

		/// Given a list of Employee objects, group them by their age and calculate the
		/// average salary for each age group.
		///
		///
		///
		Employee emp = new Employee.EmployeeBuilder().setAge(22).setSalary(40000).setEmpName("Dilip").build();
		Employee emp1 = new Employee.EmployeeBuilder().setAge(21).setSalary(50000).setEmpName("Sandeep").build();
		Employee emp2 = new Employee.EmployeeBuilder().setAge(30).setSalary(60000).setEmpName("Shankar").build();
		Employee emp3 = new Employee.EmployeeBuilder().setAge(24).setSalary(80000).setEmpName("Neeraj").build();

		List<Employee> empList = new ArrayList<>();
		empList.add(emp1);
		empList.add(emp);
		empList.add(emp2);
		empList.add(emp3);
		System.out.println(empList.stream()
				.collect(Collectors.groupingBy(Employee::getAge, Collectors.averagingDouble(Employee::getSalary))));

		/*
		 * System.out.println(empList.stream().sorted((a, b) ->
		 * Integer.compare(b.getAge(), a.getAge())) .collect(Collectors.toList()));
		 */

		Map<Integer, Employee> mapList = empList.stream().collect(Collectors.toMap(e -> e.getAge(), e -> e));
		System.out.println("Sorted List");
		System.out.println(mapList.entrySet().stream()
				.sorted((a, b) -> Integer.compare(b.getValue().getSalary(), a.getValue().getSalary()))
				.collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue())));

		streamProblems();
		List<Integer> shiftOne = Arrays.asList(1, 2, 3, 4, 1, 5, 6, 1, 7, 8, 1, 9);

		System.out.println(shiftOne.stream().sorted((a, b) -> Integer.compare(b, a)).collect(Collectors.toList()));

		long countof1 = shiftOne.stream().filter(num -> num == 1).count();
		List<Integer> list2 = new ArrayList<>();
		for (int i = 0; i < countof1; i++)
			list2.add(1);
		List<Integer> notOne = shiftOne.stream().filter(num -> num != 1).collect(Collectors.toList());
		Stream.concat(list2.stream(), notOne.stream()).collect(Collectors.toList()).forEach(e -> System.out.print(e));
		
		List<String> sList = Arrays.asList("1", "2", "3", "4", "5");

		// Using Stream flatMapToInt(Function mapper) 
		System.out.println("Using Map");
		sList.stream().map(i->Integer.parseInt(i)).forEach(i->System.out.println(i));
		
		System.out.println("Using FlatMap");
		sList.stream()
		.flatMapToInt(num -> IntStream.of(Integer.parseInt(num)))
		.forEach(System.out::println);
		
		streamKT();
	}

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

	public static boolean isPrime(int number) {
		if (number <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	public void streamPart1() {
		Employee emp1 = new Employee();
		emp1.setEmpId(1l);
		emp1.setEmpName("Dilip Kumar");
		emp1.setAge(30);
		emp1.setPhone_no("9892154");

		Department dep1 = new Department();
		dep1.setDepartmentId(11l);
		dep1.setDepartmentName("Computer Science");
		dep1.setDepartmentAddress("Kolkata Gitanjali SEZ");

		Department dep2 = new Department();
		dep2.setDepartmentId(12l);
		dep2.setDepartmentName("Social Science");
		dep2.setDepartmentAddress("Kolkata Ecospace SEZ");

		Department dep3 = new Department();
		dep3.setDepartmentId(13l);
		dep3.setDepartmentName("Information Technology");
		dep3.setDepartmentAddress("Kolkata SEZ");

		emp1.setDepartment(dep1);

		Employee emp2 = new Employee();
		emp2.setEmpId(2l);
		emp2.setEmpName("Ram Kumar");
		emp2.setAge(26);
		emp2.setPhone_no("9865223");

		emp2.setDepartment(dep2);

		Employee emp3 = new Employee();
		emp3.setEmpId(3l);
		emp3.setEmpName("Ratan Kumar");
		emp3.setAge(29);
		emp3.setPhone_no("8965622");

		emp3.setDepartment(dep3);

		Employee emp4 = new Employee();
		emp4.setEmpId(4l);
		emp4.setEmpName("Rajesh Kumar");
		emp4.setAge(25);
		emp4.setPhone_no("89656256");

		emp4.setDepartment(dep3);

		List<Employee> emList = new ArrayList<>();
		emList.add(emp1);
		emList.add(emp2);
		emList.add(emp3);
		emList.add(emp4);
		// Average Age
		double averageAge = emList.stream().mapToInt(Employee::getAge).average().orElse(0);
		log.info("Average Age of Employees {}", averageAge);

		emList.stream().mapToInt(Employee::getAge).anyMatch(StreamTest::isPrime);
		List<Employee> emList1 = emList.stream().peek(e -> System.out.println(e))
				.filter(emp -> this.isPrime(emp.getAge())).collect(Collectors.toList());

	}

	public static void streamKT() {
		Vector<Integer> v = new Vector();
		Hashtable<Integer, String> h = new Hashtable(); // synchronized
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
		Set<String> flt = new LinkedHashSet<>();
		long count = students.stream().map(e -> e.toUpperCase()).count();
		System.out.println("Stream Opp" + count);

		// longest String;
		Optional<String> longestLength = students.stream().max((a,b)->Integer.compare(a.length(), b.length()));
		log.info("Longest String {}", longestLength.get());

		// 5 ways to create Stream.
		List<Integer> num = Arrays.asList(100, 220, 330, 400, 500);
		List<Integer> filterList = num.stream().filter((Integer i) -> i < 400).collect(Collectors.toList());

		int[] salary = { 30000, 40000, 30000, 50000 };
		Stream<Integer> salaryStream = Arrays.stream(salary).boxed().sorted().skip(1).limit(2);
		salaryStream.collect(Collectors.toList()).forEach(e -> System.out.println("nd and third max" + e));
		double avg = Arrays.stream(salary).boxed()
				.mapToInt(x -> x)
				.sum();

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
		Stream<Integer> streamSortedDesc = salaryStream1.distinct().sorted(( str1,  str2) -> str1 - str2);

		// peek()

		Stream<Integer> peekStream = test.stream().filter(i -> i > 200)
				.peek(i-> System.out.println(i));
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
		Integer reducedList = sortedList.stream().filter((Integer i) -> i < 200)
				.reduce(0,(Integer i, Integer j) -> i + j);

		System.out.println("Reduced Value " + reducedList);

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

	public static void streamProblems() {

		// Q. Find the longest string in a list of strings using Java streams:

		List<String> strings = Arrays.asList("apple", "bananajkkbhjb", "dating", "date", "grapefruit", "apply",
				"dancing");
		Map<Object, Object> strMap = strings.stream().collect(Collectors.toMap(s -> s, s -> s.length()));

		Optional<String> compared = strings.stream()
				.sorted(Comparator.comparingInt(key -> ((String) key).length()).reversed()).skip(1).findFirst();

		System.out.println(compared.get());
		System.out.println(strMap);

		Optional<Entry<Object, Object>> max = strMap.entrySet().stream()
				.sorted(Map.Entry.comparingByKey(Comparator.comparingInt(key -> key.toString().length())).reversed())
				.skip(1).findFirst();

		System.out.println(max.get().getKey());
		int maxLen = 0;
		String maxLenStr1 = null;
		for (Map.Entry<Object, Object> map : strMap.entrySet()) {

			if (((Integer) map.getValue()).compareTo(maxLen) > 0) {
				maxLen = (int) map.getValue();
				String manxLenStr1 = (String) map.getKey();
			}
		}
		System.out.println("maxLenStr1" + maxLenStr1);

		List<String> strings1 = Arrays.asList("apple", "banana", "date", "date", "grapefruit", "apple", "dancing");
		Map<String, Long> wordFrequency = strings1.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		wordFrequency.entrySet().forEach(str -> System.out.println("Strings" + str));

		Optional<String> maxLenStr = strings.stream().max(Comparator.comparingLong(s -> s.length()));
		// maxLenStr = Optional.ofNullable(null);
		System.out.println("maxLenStr" + maxLenStr.orElse("An Empty List of String"));

		//////////////////////////////////////////////
		///
		///
		/// Q. Calculate the average age of a list of Person objects using Java streams:
		Employee employee = new Employee.EmployeeBuilder().setPhone_no("1").setEmpName("John Doe").setAge(30)
				.setSalary(80000).setEmpAddress("Bokaro").build();
		Employee employee0 = new Employee.EmployeeBuilder().setPhone_no("2").setEmpName("John Doe").setAge(40)
				.setSalary(90000).setEmpAddress("Dhanbad").build();
		Employee employee1 = new Employee.EmployeeBuilder().setPhone_no("3").setEmpName("Alice Doe").setAge(25)
				.setSalary(50000).setEmpAddress("Kolkata").build();
		Employee employee2 = new Employee.EmployeeBuilder().setPhone_no("4").setEmpName("Bob Doe").setAge(45)
				.setSalary(60000).setEmpAddress("Pune").build();
		Employee employee3 = new Employee.EmployeeBuilder().setPhone_no("5").setEmpName("Bob Doe").setAge(80)
				.setSalary(70000).setEmpAddress("Hyderabad").build();

		List<Employee> persons = Arrays.asList(employee, employee0, employee1, employee2, employee3);

		Map<String, Employee> empMap = persons.stream()
				.collect(Collectors.toMap(e -> e.getPhone_no(), emp -> emp));

		Map<String, Employee> empMapSorted = empMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue((a,b)->Integer.compare(a.getAge(), b.getAge())))
				.collect(Collectors.toMap(e -> e.getKey(), v -> v.getValue(), (a, b) -> a, LinkedHashMap::new));

		System.out.println("Sorted List By Age" + empMapSorted);

		double averageAge = persons.stream().mapToDouble(e -> e.getAge()).average().orElse(0);
		System.out.println("AverageAge" + averageAge);

		Map<String, List<Employee>> mapList = persons.stream()
				.collect(Collectors.groupingBy(Employee::getEmpName,
						Collectors.collectingAndThen(Collectors.toList(),
								list -> list.stream().sorted(Comparator.comparingInt(Employee::getAge).reversed())
										.collect(Collectors.toList()))));
		mapList.entrySet().stream().forEach(emp -> {
			System.out.print("Key" + emp.getKey() + ":");
			System.out.println(emp.getValue());
		});

		// Q. Check if a list of integers contains a prime number using Java streams:
		List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 11, 12, 13, 14, 15);
		List<Integer> primeNumbers = numbers.stream().peek(n -> System.out.println(n)).filter(num -> isPrime(num))
				.collect(Collectors.toList());

		boolean havePrimeNum = numbers.stream().anyMatch(n->isPrime1(n));
		System.out.println(primeNumbers + "\t" + havePrimeNum);

		///////////////////////////////
		/// Q. Merge two sorted lists into a single sorted list using Java streams:

		List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9);
		List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10);
		Integer mergedList = Stream.concat(list1.stream(), list2.stream()).distinct().reduce(1, (a, b) -> a * b); // identiy=1
																													// and
																													// accumulator
																													// is
																													// multiplication
		// .reduce(0,(a,b) -> a+b); //identiy=0 and accumulator is addition
		// .reduce((a,b) -> a>b?a:b);// to accumulate the max
		System.out.println(mergedList);

		///// 4 Write a Java program to calculate the sum of all even, odd numbers in a
		///// list using streams.///
		Stream.concat(list1.stream(), list2.stream()).filter(num -> num % 2 == 0).forEach(n -> System.out.println(n));
		;
		Stream.concat(list1.stream(), list2.stream()).filter(num -> num % 2 != 0).forEach(n -> System.out.println(n));

		/// 5. Write a Java program to count the number of strings in a list that start
		/// with a specific letter using streams.
		///

		long startWithA = strings.stream().filter(s -> s.startsWith("a")).count();
		System.out.println("total String that startWithA" + startWithA);

		// 6. Write a Java program to sort a list of strings in alphabetical order,
		// ascending and descending using streams.

		List<String> ascendingorder = strings.stream().sorted().collect(Collectors.toList());// Ascending order;
		ascendingorder.forEach(str -> System.out.println(str));

		List<String> descendingorder = strings.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());// Descending
																														// order;
		descendingorder.forEach(str -> System.out.println(str));

		// 7. Write a Java program to find the maximum and minimum values in a list of
		// integers using streams.
		Stream.concat(list1.stream(), list2.stream()).min((n1, n2) -> Integer.compare(n1, n2))
				.ifPresent(n -> System.out.print(n));
		Stream.concat(list1.stream(), list2.stream()).max((n1, n2) -> Integer.compare(n1, n2))
				.ifPresent(n -> System.out.print(n));

		// 8. Write a Java program to find the second smallest and largest elements in a
		// list of integers using streams.
		Stream.concat(list1.stream(), list2.stream()).sorted().skip(1).findFirst()
				.ifPresent(n -> System.out.print("second Minimum" + n));
		Stream.concat(list1.stream(), list2.stream()).sorted(Comparator.reverseOrder()).skip(1).findFirst()
				.ifPresent(n -> System.out.print("second largest" + n));

		List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Map<Boolean, List<Integer>> partitioned = numbers2.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));

		partitioned.get(true).forEach(even -> System.out.print("\t" + even));
		partitioned.get(false).forEach(odd -> System.out.print("\t" + odd));

		String question = "Hellohhwhho hhrldhgh";
		String trim = question.trim().replaceAll("", "").replaceAll("\\s+", "").toLowerCase();
		Map<Character, Long> countChars = trim.chars().mapToObj(x -> (char) x)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Map<Character, Long> counts = trim.chars().mapToObj(x -> (char) x)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		counts.entrySet().forEach(chars -> System.out.println(chars.getKey() + ": " + chars.getValue()));

		// System.out.println(persons.stream().collect(Collectors.toMap(emp ->
		// emp.getDept_id(), emp -> emp)));

		System.out.println("Person" + persons);

		Map<Integer, Employee> map = persons.stream()
					.sorted(Comparator.comparingInt(p->p.getSalary()))
					//.sorted((a, b) -> Integer.compare(b.getSalary(), a.getSalary()))
					.collect(Collectors.toMap(emp -> emp.getAge(), // keys()
						emp -> emp, // values()
						(old, new1) -> old, // avoid duplicates()
						() -> new LinkedHashMap<>())// preserve the order;
				);
					
		HashMap<Object, Object> salarySorted= map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.comparingInt(Employee::getSalary).reversed()))
				.collect(Collectors.toMap(key->key.getKey(), // keys()
						val -> val.getValue(), // values()
						(old, new1) -> old, // avoid duplicates()
						() -> new LinkedHashMap<>()));
		 System.out.println("salarySorted :::"); 
		 System.out.println(salarySorted);
		
		System.out.println("MapToWorkSortedOnAddress");
		System.out.println(map);

		Map<Long, Employee> maptoWork = persons.stream()
				.collect(Collectors.toMap(emp -> emp.getEmpId(), emp -> emp, (a, b) -> b, LinkedHashMap::new));

		maptoWork.entrySet().stream().sorted().collect(Collectors.toMap(emp -> emp.getKey(), emp -> emp.getValue(),
				(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		System.out.println("MapToWorkSortedOnSalaray");
		System.out.println(maptoWork);

		Map<Integer, List<Employee>> empList1 = persons.stream()
				.collect(Collectors.groupingBy(p -> p.getAge(), Collectors.collectingAndThen(Collectors.toList(),
						list -> list.stream().sorted(Comparator.comparingInt(Employee::getAge).reversed()).toList())));

		Map<String, List<Employee>> mapList1 = persons.stream()
				.collect(Collectors.groupingBy(Employee::getEmpName,
						Collectors.collectingAndThen(Collectors.toList(),
								list -> list.stream().sorted(Comparator.comparingInt(Employee::getAge).reversed())
										.collect(Collectors.toList()))));
		mapList.entrySet().stream().forEach(emp -> {
			System.out.print("Key" + emp.getKey() + ":");
			System.out.println(emp.getValue());
		});

	}

	public static boolean isPrime1(int num) {
		if (num == 1 || num == 0)
			return false;
		else {
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if (num % i == 0)
					return false;
			}
		}
		return true;
	}
}
