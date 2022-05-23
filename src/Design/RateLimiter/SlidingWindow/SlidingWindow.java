/**
 * 
 */
package Design.RateLimiter.SlidingWindow;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import Design.RateLimiter.RateLimiter;

/**
 * @author rahul
 *
 */
public class SlidingWindow implements RateLimiter {
	private Queue<Long> slidingWindow;
	private final int bucketCapacity;
	private final int timeWindowInSec;

	public SlidingWindow(int bucketCapacity, int timeWindowInSec) {
		this.bucketCapacity = bucketCapacity;
		this.timeWindowInSec = timeWindowInSec;
		slidingWindow = new ConcurrentLinkedQueue<>();			// unbounded compared to blocking queue
	}

	@Override
	public boolean allowRequest() {
		long currTime = System.currentTimeMillis();

		checkAndUpdateQueue(currTime);

		if (slidingWindow.size() < bucketCapacity) {
			slidingWindow.offer(currTime);
			return true;
		}
		return false;
	}
	
	private void checkAndUpdateQueue(long currTime) {
		if (slidingWindow.isEmpty()) return;
		
		long calculateTime = (currTime - slidingWindow.peek())/1000;
		while (calculateTime >= timeWindowInSec) {
			slidingWindow.poll();
			if (slidingWindow.isEmpty()) break;
			calculateTime = (currTime - slidingWindow.peek())/1000;
		}
	}
}
