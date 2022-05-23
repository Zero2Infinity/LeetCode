package Design.RateLimiter;

import java.util.Random;
// import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// import Design.RateLimiter.TokenBucket.Application;
// import Design.RateLimiter.LeakyBucket.Application;
import Design.RateLimiter.SlidingWindow.Application;

public class Solution {

	public static void main(String[] args) {
		
		Application app = new Application();
		// app.request(1);
		/* ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 20; i++) {
			Random rand = new Random();
			executor.execute(() -> app.request(rand.nextInt(3)));
		}
		executor.shutdown();
		*/
		
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
		executorService.scheduleAtFixedRate((() -> new RandomRequestor(app)), 1, 6, TimeUnit.SECONDS);
	}
	
	static class RandomRequestor {
		RandomRequestor(Application app) {
			System.out.println("new requestor batch");
			for (int i = 0 ; i < 20; i++) {
				Random rand = new Random();
				app.request(rand.nextInt(3));
			}
		}
	}
}
