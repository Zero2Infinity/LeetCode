package Medium.p912;

import java.util.Arrays;

public class BubbleSort {

	// Bubble sort is simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in wrong order.
    // Time complexity: O(n^2)
	// Space complexity: O(1)
	private static int[] sort(int[] nums) {
		int last = nums.length - 1;
		for (int i = 0; i < last; i++) {
			for (int j = 0; j < last - i; j++) {
				if (nums[j] > nums[j+1]) {		// Condition to bubble up smaller or bigger element
					int t = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = t;
				}
			}
			// Each inner for biggest element goes towards end and smaller elements bubble up
		}

		return nums;
	}

	public static void main(String[] args) {
		int[] nums = {4, 6, 2, 1, 3, 5, 100, 50, 25};
		sort(nums);
		System.out.print(Arrays.toString(nums));
	}
}
