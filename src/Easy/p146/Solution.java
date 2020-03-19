package Easy.p146;

import java.util.Map;
import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and put.
 * @author rahul
 *
 */

public class Solution {
	
	public static void main(String[] args) {
		System.out.println(" LRU design ");
		LRUCache cache = new LRUCache( 2 /* capacity */ );
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.put(3, 3);    					// evicts key 2
		System.out.println(cache.get(2));       // returns -1 (not found)
		cache.put(4, 4);    					// evicts key 1
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4));       // returns 4
	}
}

class LRUCache {
    
    class DLinkedNode {
        int key, val;
        DLinkedNode prev;
        DLinkedNode next;
        DLinkedNode() {}
        DLinkedNode(int key, int val) {
            this.key = key; this.val = val; this.prev = null; this.next = null;
        }
    }
    
    // addNode after Head
    private void addNode(DLinkedNode node) {
        DLinkedNode tmp = head.next;
        node.prev = head;
        node.next = tmp;
        head.next = node;
        tmp.prev = node;
        
        size++;
    }
    
    // removeNode before Tail
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null; 
        node.next = null;
        
        size--;
    }
    
    // When accessed, make it recently used node
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }
    
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;       // current size of DLL
    private int capacity;   // Cache capacity
    private DLinkedNode head, tail;
    
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (cache.containsKey(key)) {
            DLinkedNode retNode = cache.get(key);
            moveToHead(retNode);
            return retNode.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (!cache.containsKey(key)) {
            DLinkedNode newNode = new DLinkedNode(key, value);
            // Main LRU strategy: 
            if (size >= capacity) {
                DLinkedNode leastUsed = tail.prev;
                removeNode(leastUsed);
                cache.remove(leastUsed.key);
            } 
                addNode(newNode);
                cache.put(key, newNode);
        }
    }
}