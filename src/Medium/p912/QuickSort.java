package Medium.p912;

import java.util.Arrays;

public class QuickSort {


	private static void sort(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
	}
	
	private static void quickSort(int[] nums, int left, int right) {
		if (left < right) {
			int pivotIdx = (left + right) / 2;
			int index = partition(nums, left, right, pivotIdx);
			quickSort(nums, left, index - 1);
			quickSort(nums, index + 1, right);
		}
	}
	
	private static int partition(int[] nums, int left, int right, int pivotIdx) {
		while (left < right) {
			while (nums[left] < nums[pivotIdx]) left++;
			while (nums[right] > nums[pivotIdx]) right--;
			if (left < right) {
				int t = nums[left];
				nums[left] = nums[right];
				nums[right] = t;
				left++; right--;
			}
		}
		int t = nums[left];
		nums[left] = nums[pivotIdx];
		nums[pivotIdx] = t;

		return left;
	}


	public static void main(String[] args) {
		int[] nums = {4, 6, 2, 1, 3, 5, 100, 50, 25};
		sort(nums);
		System.out.print(Arrays.toString(nums));
	}
}
