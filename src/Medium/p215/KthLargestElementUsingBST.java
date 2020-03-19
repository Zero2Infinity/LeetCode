package Medium.p215;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementUsingBST {

	class TreeNode {
		int val, count = 1;
		TreeNode left, right;
		public TreeNode(int val) { this.val = val; }
	}
	
	TreeNode root;
	int k;
	int kthLargest = Integer.MIN_VALUE;
	
	public KthLargestElementUsingBST(int k, int[] nums) {
		this.k = k;
		for (int n : nums)
			this.root = addNode(this.root, n);
	}
	
	public int add(int val) {
		if (this.kthLargest > val) {
			return this.kthLargest;
		} else {
			this.root = addNode(this.root, val);
			this.kthLargest = findKthLargestElement();
			return this.kthLargest;
		}
	}
	
	private TreeNode addNode(TreeNode root, int val) {
		if (root == null)  return new TreeNode(val);
		
		root.count++;
		if (val < root.val)
			root.left = addNode(root.left, val);
		else 
			root.right = addNode(root.right, val);
		
		return root;
	}
	
	private int findKthLargestElement() {
		int count = k;
		TreeNode node = this.root;
		
		while (count > 0) {
			int pos = 1 + (node.right != null ? node.right.count : 0);
			if (count == pos) break;
			if (count < pos) {
				node = node.right;
			} else if (count > pos) {
				count = count - pos;
				node = node.left;
			}
		}
		
		return node.val;
	}

	// Using priority Queue (min heap)
    // Time complexity: O(nlogk)
    // Space complexity: (k)
    public int __findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);
            // Try to retain k nodes
            if (minHeap.size() > k) 
                minHeap.poll();
        }
        return minHeap.peek();
    }
    
    // Simple approach: 
    // Time complexity: O(nlogn): sorting
    public int _findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }	

	public static void main(String[] args) {
		int[] nums = {4, 5, 8, 2};
		int k = 3;
		KthLargestElementUsingBST s = new KthLargestElementUsingBST(k, nums);
		System.out.println(s.add(3));
		System.out.println(s.add(5));
		System.out.println(s.add(10));
		System.out.println(s.add(9));
		System.out.println(s.add(4));
	}
}
