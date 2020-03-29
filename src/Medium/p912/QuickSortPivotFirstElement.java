package Medium.p912;

import java.util.Arrays;

// It is one of the fastest algorithms with average time complexity O(nlogn).
// It is also use divide and conquer strategy to sort as like merge sort.
// It has taken all advantages of merge sort and overcome the disadvantage of 
// using auxiliary space also. But worst case could be O(n^2).
// Example of worst case:
// If the input array is already sorted in same or reverse order or all elements
// are same. i.e. if the input array is such that the max or min element is
// always chose as pivot then we could hit worst case.
public class QuickSortPivotFirstElement {

    private static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    
    private static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            // dividing array with the pivot.
            int index = partition(nums, left, right);
            quickSort(nums, left, index - 1);           // sort left-half
            quickSort(nums, index + 1, right);          // sort right-half
        }
    }
    
    // Alternate, pivot could be last element
    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];

        // ith index trace the smallest elements than pivot.
        // at last we will swap element at ith index with pivot.
        // means before ith index all elements should be less than pivot.
        int i = left;
        for (int j = left + 1; j <= right; j++) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i, left);

        return i;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public static void main(String[] args) {
        int[] nums = {4, 6, 2, 1, 3, 5, 100, 50, 25};
        sort(nums);
        System.out.print(Arrays.toString(nums));
    }
}
