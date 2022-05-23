package Design.RateLimiter.TokenBucket;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import Design.RateLimiter.RateLimiter;

public class TokenBucket implements RateLimiter {
	// configuration
	private int bucketCapacity;
	private long refillRate;
	// current tokens
	private AtomicInteger currCapacity;
	private AtomicLong lastUpdateTime;
	
	public TokenBucket(int bucketCapacity, int refillRate) {
		this.bucketCapacity = bucketCapacity;
		this.refillRate = refillRate;

		this.currCapacity = new AtomicInteger(bucketCapacity);
		this.lastUpdateTime = new AtomicLong(System.currentTimeMillis());
	}

	@Override
	public boolean allowRequest() {
		refill();

		if (currCapacity.get() > 0) {
			currCapacity.decrementAndGet();
			return true;
		}
		return false;
	}
	
	private void refill() {
		long currTime = System.currentTimeMillis();
		int additionalToken = (int) ((currTime - lastUpdateTime.get())/1000 * refillRate);
		int newCapacity = Math.min(bucketCapacity, currCapacity.get() + additionalToken);
		currCapacity.getAndSet(newCapacity);
		lastUpdateTime.getAndSet(currTime);
	}

}
