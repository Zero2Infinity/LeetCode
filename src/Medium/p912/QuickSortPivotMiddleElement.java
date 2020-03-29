package Medium.p912;

import java.util.Arrays;

public class QuickSortPivotMiddleElement {

    private static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    
    private static void quickSort(int[] nums, int left, int right) {
       if (left >= right) {
    	   return;
       } 
       int pivot = nums[(left + right) / 2];
       int index = partition(nums, left, right, pivot);
       quickSort(nums, left, index - 1);
       quickSort(nums, index, right);
    }
    
    // Partition: puts all smaller elements than pivot on left and greater on right side
    // return left which is partition point
    private static int partition(int[] nums, int left, int right, int pivot) {
    	while (left <= right) {
    		while (nums[left] < pivot) left++;
    		while (nums[right] > pivot) right--;
    		if (left <= right) {
    			swap (nums, left, right);
    			left++; right--;
    		}
    	}
    	return left;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public static void main(String[] args) {
        // int[] nums = {4, 6, 2, 1, 3, 5, 100, 50, 25};
    	int[] nums = {50,25,12};
        sort(nums);
        System.out.print(Arrays.toString(nums));
    }
}
