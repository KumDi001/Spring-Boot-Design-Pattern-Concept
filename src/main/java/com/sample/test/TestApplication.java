package com.sample.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

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
import com.sample.test.serviceImpl.Problems;
import com.sample.test.util.StreamTest;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Log4j2
public class TestApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);
		Problems prob = (Problems) context.getBean("problems");

		int[] nums = { 0, 1, 0, 3, 12 };
		int count1 = 0;
		for (int i = 0; i < nums.length - 1 - count1; i++) {
			if (nums[i] == 0) {
				int temp = nums[i];
				nums[i] = nums[nums.length - 1 - count1];
				nums[nums.length - 1 - count1] = temp;
				count1++;
			}
		}
		int newLength = nums.length - count1;
		for (int i = 0; i <= newLength - 1; i++) {
			for (int j = i + 1; j <= newLength - 1; j++) {
				if (nums[i] > nums[j]) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}

		Address y = new Address();
		System.out.println(y.counter); // L5.
		// Factory call from the client
		Employees emp = EmployeeFactory.getEmployee("Android Developer");
		emp.getSalary();
		Employees emp1 = EmployeeFactory.getEmployee("Web Developer");
		emp1.getSalary();
		// AbstractFactory Client;
		Employees e3 = EmployeeFactory.getEmployees(new AndroidDeveloperFactory());
		e3.getSalary();

		Employees e4 = EmployeeFactory.getEmployees(new WebDeveloperFactory());
		e4.getSalary();
		int[] prod = { -2, 3, -4 };
		prob.maxProduct(prod);

		int[] numsb2 = { 4, 4, 1, 3, 1, 3, 2, 2, 5, 5, 1, 5, 2, 1, 2, 3, 5, 4 };
		int nx = numsb2.length;
		int index = 0;
		int result = 0;
		while (index < nx) {
			for (int i = index + 1; i < nx; i++) {
				if(numsb2[i]!=0 &&  numsb2[index]!=0)
					if (numsb2[i] + numsb2[index] == 2) {
						result++;
						numsb2[i] = 0;
						numsb2[index] = 0;
					}
			}
			index++;
		}
		 String s="the  sky is   blue  ";
		 String [] str = s.trim().replaceAll("\\s+", " ").split(" ");
		 List<String> stList = Arrays.asList(str);
		 //stList.stream().filter();
		 String [] revrsStr = new String[str.length];
	        for(int i=str.length-1; i>=0;i--)
	        {
	            revrsStr[i]=str[str.length-1-i];
	        }
	        String reverse="";
	        for(String s1 :  revrsStr)
	        {
	        	reverse= reverse +"\s"+s1;	
	        }
	        List<Integer> list= Arrays.stream(nums).boxed().toList();     

	        int [] numbers= {4,0,4,3,3};
	        double avgMax= IntStream.range(0, numbers.length - 4 + 1)
	            .mapToDouble(i -> Arrays.stream(Arrays.copyOfRange(numbers, i, i + 4))
	            		.average()
	            		.orElse(0))
	            	.max()
	            	.orElse(0); 
	        
	        double maxAvg=0;
	        double avg=0;
	        for(int i=0;i<numbers.length;i++)
	        {
	        	int check=0;;
	        	double total=0;
	            for(int j=i;j<i+5;j++)
	            {
	                total= total+numbers[j];
	                check= Math.abs(i-(numbers.length-1))+1;
	                if (check==5)
	                break;
	            }
	                avg=total/5;
	                maxAvg= Math.max(avg,maxAvg);
	             if (check==5)
		               break;
	        }
	        
		final AtomicInteger counter = new AtomicInteger(0);
		System.out.println(
				"**************** START: Total Bean Objects:  ******************" + context.getBeanDefinitionCount());

		Arrays.asList(context.getBeanDefinitionNames()).forEach(beanName -> {
			System.out.println("{} Bean Name: {} " + counter.incrementAndGet() + " " + beanName);
		});

		StreamTest streamService = (StreamTest) context.getBean("streamTest");
		streamService.streamKT();

		int[] arrQuick = { 6, 2, 8, 9, 3 };
		streamService.quickSort(arrQuick, 0, arrQuick.length - 1);

		int power = streamService.powerSumRec(2, 10);
		System.out.println("Power" + power);

		streamService.streamPart1();
///////////////////////////////////////////////////////////////////////////////////////////////////////
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
			if (ransomNoteMap.containsKey(key) && ransomNoteMap.get(key) <= value) {
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
		String st = "[{}]{}[{]";
		boolean isValid = prob.isValidBrackets(st);
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

	public int maxArea(int[] height) {
		int n = height.length;
		int maxVolume = 0;
		int vol = 1;
		int min = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				min = Math.min(height[i], height[j]);
				vol = min * Math.abs(i - j);
				if (vol > maxVolume)
					maxVolume = vol;
			}
		}
		return maxVolume;
	}

}