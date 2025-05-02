package com.sample.test.multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;

@Component
public class ExecuterServiceTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		runThread();
	}

	static void runThread() throws InterruptedException, ExecutionException, TimeoutException {
		long startTime = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(5);
		Optional<Future<?>> future1 = Optional.ofNullable(executor.submit(() -> {
			System.out.println("Thread2");
			// return "dilip";
		}, "succesfully Completed"));

		Future<String> future2 = null;
		for (int i = 0; i < 100; i++) {
			int test = i;
			future2 = executor.submit(() -> {
				System.out.println(test);
				return "dilip";
			});
			System.out.println(Optional.ofNullable(null).orElse(future1.get()));
		}
		Optional.ofNullable(future2);

		executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
		executor.shutdown();
		Thread.sleep(1000);
		System.out.println(executor.isTerminated());
		System.out.println("Total time taken" + (System.currentTimeMillis() - startTime));

		Callable<Integer> callable1 = () -> {
			Thread.sleep(1000);
			System.out.println("Call 1");
			return 1;
		};
		Callable<Integer> callable2 = () -> {
			Thread.sleep(1000);
			System.out.println("Call 2");
			return 2;
		};
		Callable<Integer> callable3 = () -> {
			Thread.sleep(1000);
			System.out.println("Call 3");
			return 3;
		};
		Callable<Integer> callable4 = () -> {
			System.out.println("Call 4");
			return 4;
		};

		ExecutorService executorService = Executors.newFixedThreadPool(1);
		List<Callable<Integer>> list = Arrays.asList(callable1, callable2, callable3, callable4);
		List<Future<Integer>> result = executorService.invokeAll(list, 2000, TimeUnit.MILLISECONDS);
		List<Future<Integer>> res = result.stream().filter (e -> e.isDone()).toList();
		/*
		 * result.stream().filter(e->!e.isCancelled()).forEach(e->{ try {
		 * System.out.println(e.get()); } catch (InterruptedException |
		 * ExecutionException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } });
		 */

		res.forEach(System.out::print);
		Integer result1 = executorService.invokeAny(list, 2000, TimeUnit.MILLISECONDS);
		System.out.println(result1);

		ExecutorService executorService1 = Executors.newFixedThreadPool(1);
		future2 = executorService1.submit(() -> {
			Thread.sleep(6000);
			System.out.println("DilipTest");
			return "dilip";
		});
		Thread.sleep(6000);
		future2.cancel(true);
		System.out.println(future2.isCancelled());
		System.out.println(future2.isDone());

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		 ScheduledFuture<?> scheduledFuture= scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("task in every 5 minutes"), 5, 5,
				TimeUnit.SECONDS);
		 scheduledFuture.get();

		Thread.sleep(6000);
		scheduledExecutorService.schedule(() -> {
			System.out.println("shutdown has been initiated...");
			scheduledExecutorService.shutdown();
		}, 20, TimeUnit.SECONDS);

	}
}
