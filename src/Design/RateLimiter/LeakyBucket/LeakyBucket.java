/**
 * 
 */
package Design.RateLimiter.LeakyBucket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Design.RateLimiter.RateLimiter;

/**
 * @author rahul
 *
 */
public class LeakyBucket implements RateLimiter {

	// NOTE We are not using full potential of BlockingQueue 
	// since put/take block requester thread.
	// Here this is as simple as ordinary Queue
	private BlockingQueue<Integer> queue;
	
	public LeakyBucket(int capacity) {
		queue = new LinkedBlockingQueue<>(capacity);
	}

	@Override
	public boolean allowRequest() {
		if (queue.remainingCapacity() > 0) {
			queue.add(1);					// put() keep thread waiting
			return true;
		}
		
		return false;
	}

	public void remove() {
		queue.poll();			// take() keep thread waiting
	}

}
