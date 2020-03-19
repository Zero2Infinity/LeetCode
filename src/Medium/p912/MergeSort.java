package Medium.p912;

import java.util.Arrays;

public class MergeSort {

	// Merge sort is a Divide and Conquer algorithm.
	// Time complexity: O(n logn)
	// Space complexity: O(n)
	private static void sort(int[] nums) {
		mergeSort(nums, 0, nums.length - 1);
	}
	
	private static void mergeSort(int[] nums, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(nums, left, mid);
			mergeSort(nums, mid + 1, right);
			merge(nums,left, mid, right);
		}
	}
	
	private static void merge(int[] nums, int l, int m, int r) {
		// Calculate L and R size
		int n1 = m - l + 1;
		int n2 = r - m;
		int[] L = new int[n1];
		int[] R = new int[n2];
		
		// Copy data from nums[] to L[] and R[]
		for (int i = 0; i < n1; i++) {
			L[i] = nums[l+i];
		}

		for (int i = 0; i < n2; i++) {
			R[i] = nums[m + 1 + i];
		}
	
		int i = 0, j = 0, k = l; 
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				nums[k++] = L[i++];
			} else {
				nums[k++] = R[j++];
			}
		}
		
		// Copy remaining elements from L[], if there are any
		while (i < n1) {
			nums[k++] = L[i++];
		}
		
		// Copy remaining elements from R[], if there are any
		while (j < n2) {
			nums[k++] = R[j++];
		}
	}

	public static void main(String[] args) {
		int[] nums = {4, 6, 2, 1, 3, 5, 100, 50, 25};
		sort(nums);
		System.out.print(Arrays.toString(nums));
	}
}
