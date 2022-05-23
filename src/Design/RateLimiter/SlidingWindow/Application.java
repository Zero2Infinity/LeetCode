package Design.RateLimiter.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class Application {

 private Map<Integer, SlidingWindow> userTokens;
	 
	 public Application() {
		 userTokens = new HashMap<>();
	 }

	 public void request(int userId) {
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
		 SlidingWindow t = new SlidingWindow(1, 10);		// 1 bucket every 10 seconds
		 userTokens.putIfAbsent(userId, t);
	 }

}
