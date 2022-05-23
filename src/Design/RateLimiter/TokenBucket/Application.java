package Design.RateLimiter.TokenBucket;

import java.util.HashMap;
import java.util.Map;

public class Application {
	
	 private Map<Integer, TokenBucket> userTokens;
	 
	 public Application() {
		 userTokens = new HashMap<>();
	 }

	 public synchronized void request(int userId) {
		 if (!userTokens.containsKey(userId)) {
			 createNewUser(userId);
		 }
		 if (userTokens.get(userId).allowRequest()) {
			 System.out.println(Thread.currentThread().getName() + " Allowed:  " + userId);
		 } else {
			 System.out.println(Thread.currentThread().getName() + " Blocked:  " + userId);
		 }
	 }
	 
	 private void createNewUser(int userId) {
		 System.out.println(Thread.currentThread().getName() + " New bucket assigned for " + userId);
		 TokenBucket t = new TokenBucket(5, 5);
		 userTokens.putIfAbsent(userId, t);
	 }
}
