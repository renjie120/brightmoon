package brightmoon.util.concurrent;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	private static ExecutorService service2;
	private static ExecutorService service;
	private static CompletionService<String> completionService;

	public static ExecutorService cachedThreadPool() {
		if (service == null) {
			service = Executors.newFixedThreadPool(10);
		} 
		return service;
	}

	public static CompletionService<String> completionPool() {
		if (completionService == null) {
			service2 = Executors.newCachedThreadPool();
			completionService = new ExecutorCompletionService<String>(service2);
		}
		return completionService;
	}

	public static void shutdown() {
		if (service != null) {
			service.shutdown();
		}
		if(service2!=null){
			service2.shutdown();
		}
	}
}
