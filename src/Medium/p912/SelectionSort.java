package Medium.p912;

import java.util.Arrays;

public class SelectionSort {
	
	// Select a element and place it to its right index
	// Time complexity: O(n^2)
	// Space complexity: O(1)
	private static void sort(int[] nums) {
		int N = nums.length;

		for (int i = 0; i < N; i++) {
			// Find minimum element and capture its index
			int minIdx = i;
			for (int j = i + 1; j < N; j++) {
				if (nums[minIdx] > nums[j]) {
					minIdx = j;
				}
			}
			// swap nums[i] with minimum element index
			int t = nums[i];
			nums[i] = nums[minIdx];
			nums[minIdx] = t;
		}
	}

	public static void main(String[] args) {
		int[] nums = {64,25,12,22,11}; 
		sort(nums);
		System.out.print(Arrays.toString(nums));
	}
}
