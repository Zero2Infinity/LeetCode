/**
 * 
 */
package Design.HashSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rahul
 *
 */

/*
 * Find out what kind of values we are planning to add? - Range: 0 to 10^6 i.e. 1,000,000
 * What's the approximate size that HashSet could grow upto? - Size: 10^4 = 10,000
 * 
 * Simple idea would be to add Boolean Array where index represents key
 */
interface MyHashSet {
	// Decided which basic operations we need to support
	void add(int key);
	void remove(int key);
	boolean contains(int key);
	
	// My take
	int hashCode(int key);
	int size();
}

/*
 * We have big linear memory that could store all possible key coming from the user.
 * This is unrealistic in real-world; Time complexity: O(1) with space complexity: O(N)
 */
class SimpleHashSet implements MyHashSet {
	private static final int MAX_SIZE = 10000;
	private static int count;
	private boolean[] set;
	
    public SimpleHashSet() {
    	set = new boolean[MAX_SIZE];
    	count = 0;
    }
    
    public void add(int key) {
    	if (!set[key]) {
    		System.out.println("added: " + key);
    		set[key] = true;
    		count++;
    	} else { 
    		System.out.println("key already exists");
    	}
    }
    
    public void remove(int key) {
    	set[key] = false;
    	count--;
    	System.out.println("removed:" + key);
    }
    
    public boolean contains(int key) {
    	System.out.println("contains: " + set[key]);
    	return set[key];
    }
    
    public int hashCode(int key) {
    	return key;
    }
    
    public int size() {
    	return count;
    }
}

// Better Space Complexity Approach
class RealHashSet implements MyHashSet {
	private static final int MAX_INPUT_SIZE = 10000;
	private static final int BUCKETS = 100;
	private static int count = 0;
	private List<List<Integer>> parentList;
	
    public RealHashSet() {
    	parentList = new ArrayList<>(BUCKETS);
    	System.out.println("parentList initialized to NULL");
    	for (int i = 0; i < BUCKETS; i++) 
    		parentList.add(null);
    }
    
    public void add(int key) {
    	int hash = hashCode(key);
    	List<Integer> childList = parentList.get(hash);
    	if (childList == null) {
    		childList = new LinkedList<Integer>();
    		childList.add(key);
    		count ++;
    		parentList.set(hash, childList);
      		System.out.println("added: " + key);
    	} else {
    		if (!childList.contains(key)) {
    			childList.add(key);
    			count ++;
    			System.out.println("added: " + key);
    		} else {
    			System.out.println("duplicate: " + key);
    		}
    	}
    }
    
    public void remove(int key) {
    	int hash = hashCode(key);
    	if (contains(key)) {
    		parentList.get(hash).remove(Integer.valueOf(key));
    		System.out.println("removed: " + key);
    		count --;
    	} else {
    		System.out.println(key + " not exist!");
    	}
    }
    
    public boolean contains(int key) {
    	int hash = hashCode(key);
    	List<Integer> childList = parentList.get(hash);
    	if (childList != null && childList.contains(key)) {
    		System.out.println("contains: true");
    		return true;
    	}
    	System.out.println("contains: false");
		return false;		
    }
    
    public int hashCode(int key) {
    	return key % BUCKETS;
    }
    
    public int size() {
    	return count;
    }
}

public class Solution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyHashSet myHashSet = new RealHashSet();
		myHashSet.add(1);      // set = [1]
		myHashSet.add(2);      // set = [1, 2]
		myHashSet.contains(1); // return true
		myHashSet.contains(3); // return false
		myHashSet.add(2);      // set = [1, 2]
		myHashSet.contains(2); // return True
		myHashSet.remove(2);   // set = [1]
		myHashSet.contains(2); // return False, (already removed)
	}

}
