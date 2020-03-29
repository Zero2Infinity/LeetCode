package Medium.p912;

import java.util.Arrays;

// Insertion sort that works the way we sort cards in our hands.
// Pick element arr[i] and insert it into sorted sequence arr[0…i-1]
public class InsertionSort {

	// Time complexity: O(n^2)
	// Space complexity: O(1)
	private static void sort(int[] nums) {
		int N = nums.length;
		
		for (int i = 1; i < N; i++) {
			int key = nums[i];
			int j = i - 1;
			
			// j = i-1 to 0
			// Move elements one position ahead of their current position then insert key at right position
			while (j >= 0 && nums[j] > key) {
				nums[j + 1] = nums[j];
				j--;
			}
			nums[j + 1] = key;
		}
		
	}

	public static void main(String[] args) {
		int[] nums = {4, 6, 2, 1, 3, 5, 100, 50, 25};
		sort(nums);
		System.out.print(Arrays.toString(nums));
	}
}

