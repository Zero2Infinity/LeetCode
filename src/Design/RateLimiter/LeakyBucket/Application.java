/**
 * 
 */
package Design.RateLimiter.LeakyBucket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author rahul
 *
 */
public class Application {
	 private Map<Integer, LeakyBucket> userTokens;

	 public Application() {
		 userTokens = new HashMap<>();
		 ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
		 scheduled.scheduleAtFixedRate(() -> processRequest(), 1, 3, TimeUnit.SECONDS);
	 }

	 // No synchronization needed at method level
	 public void request(int userId) {
		 if (!userTokens.containsKey(userId)) {
			 createNewUser(userId);
		 }
		 if (userTokens.get(userId).allowRequest()) {
			 System.out.println(Thread.currentThread().getName() + " Added :  " + userId);
		 } else {
			 System.out.println(Thread.currentThread().getName() + " Dropped/Blocked :  " + userId);
		 }
	 }
	 
	 private void createNewUser(int userId) {
		 System.out.println(Thread.currentThread().getName() + " New queue assigned for " + userId);
		 LeakyBucket b = new LeakyBucket(5);
		 userTokens.putIfAbsent(userId, b);
	 }
	 
	 // process each user one request in round robin as opposed to bust request processing in TokenBucket
	 private void processRequest() {
		 Set<Integer> userIds = userTokens.keySet();
		 for (int userId : userIds) {
			 System.out.println("Processed request for " + userId);
			 userTokens.get(userId).remove();
		 }
	 }
}
