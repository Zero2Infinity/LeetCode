package Design.HashMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


interface MyHashMap {
	// Define basic operations
	void put(int key, int value);
	int get(int key);
	void remove (int key);
}

class BetterHashMap implements MyHashMap {
	private static final int BUCKETS = 100;
	
	static class ListNode {
		int k, v;
		ListNode next;
		ListNode(int k, int v) { this.k = k; this.v = v; next = null; }
		ListNode(int k, int v, ListNode next) { this.k = k; this.v = v; this.next = next; }
	}
	
	ListNode[] nodes;
	
    public BetterHashMap() {
    	nodes = new ListNode[BUCKETS]; 
    }
    
    private int getIndex(int key) {
    	return key % BUCKETS;
    }
   
    private ListNode find(ListNode head, int key) {
    	if (head == null) return null;
    	if (head.k == key) return head;
    	return find(head.next, key);
    }
    
    // Choice: Append or prepend a new node - Choose Prepend
	public void put(int key, int value) {
		int index = getIndex(key);
		ListNode node = find(nodes[index], key);
		if (node != null) {
			System.out.println("update node val: " + node.v + " to " + value);
			node.v = value;							// Update w/ new value
		} else {
			System.out.println("prepend new node");
			nodes[index] = new ListNode(key, value, nodes[index]);
		}
	}
	
	public int get(int key) {
		int index = getIndex(key);
		ListNode n = find(nodes[index], key);
		if (n != null) {
			System.out.println("found node");
			return n.v;
		} else { 
			System.out.println("not found");
			return -1;
		}
	}

	// Since I choose Prepend then I need to find prev. node for removal
	public void remove(int key) {
		int index = getIndex(key);
		nodes[index] = remove(nodes[index], key);
	}
	
	private ListNode remove(ListNode head, int k) {
		if (head == null) return null;
		if (head.k == k)  {
			System.out.println("removed key: " + head.k);
			return head.next;
		}
		head.next = remove(head.next, k);
		return head;
	}
}


class MyVersionHashMap implements MyHashMap {

	// Static class helps me demonstrate that I know about Inner Class and encapsulation
	static class Node {
		int key;
		int val;
		Node(int k, int v) { key = k; val = v; }
	}
	
	private static final int MAX_INPUT_SIZE = 10000;
	private static final int BUCKETS = 100;
	
	private List<List<Node>> parentList;
	
    public MyVersionHashMap() {
    	parentList = new ArrayList<>(BUCKETS);
    	while (parentList.size() != BUCKETS) parentList.add(null);
    }
    
    // Thought: The moment I realized that finding key going to manual implementation then I should think about LL<Node>
    
    public void put(int key, int value) {
       int index = key % BUCKETS;
       List<Node> childList = parentList.get(index);
       if (childList == null) {
    	   childList = new LinkedList<Node>();
    	   childList.add(new Node(key, value));						// first Node
    	   parentList.set(index, childList);
    	   System.out.println("first node");
       } else {
    	   int childIndex = firstIndexOf(childList, key);
    	   if (childIndex != -1) {
    		   System.out.println("update node with new value");
    		   childList.set(childIndex, new Node(key, value));		// Update Node w/ new value
    	   } else {
    		   childList.add(new Node(key, value));					// Append Node
			   System.out.println("append node");
    	   }
       }
    }
    
    // Thought: finding index is not effective since any follow up operation needs additional traversal.
    // Needs custom iterator or LinkedList<K,V>
    private int firstIndexOf(List<Node> childList, int key) {
    	Iterator<Node> it = childList.iterator();
    	int i = 0;
    	while (it.hasNext()) {
    		Node n = it.next();
    		if (n.key == key) {
    			return i;
    		}
    		i ++;
    	}
    	return -1;
    }
    
    public int get(int key) {
        int index = key % BUCKETS;
        List<Node> childList = parentList.get(index);
        if (childList != null) {
        	int childIndex = firstIndexOf(childList, key);
        	if (childIndex != -1) {
        		System.out.println("found key and val: " + childList.get(childIndex).val);
        		return childList.get(childIndex).val;
        	}
        }
        System.out.println("not found!");
        return -1;
    }
    
    public void remove(int key) {
    	int index = key % BUCKETS;
        List<Node> childList = parentList.get(index);
        if (childList != null) {
        	int childIndex = firstIndexOf(childList, key);
        	if (childIndex != -1) {
        		System.out.println("removed at childIndex: " + childIndex);
        		childList.remove(childIndex);
        	}
        }
    }
}

public class Solution {
	
	public static void main(String[] args) {
		MyHashMap myHashMap = new BetterHashMap();
		myHashMap.put(1, 1); // The map is now [[1,1]]
		myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
		myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
		myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
		myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
		myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
		myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
		myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
	}
}
