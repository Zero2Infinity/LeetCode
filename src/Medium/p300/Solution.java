package Medium.p300;

import java.util.Arrays;

/**
 * Longest Increasing Subsequence
 * @author rahul
 *
 */
public class Solution {

	// Thing about recursive solution since there could be different subsequence that I could select
	private static int __lengthOfLIS(int[] input) {
		int longestLIS = 0;
		for (int i = 0; i < input.length; i++) {
			longestLIS = Math.max(longestLIS, helper(input, i, i, 1));
		}
		return longestLIS;
	}
	
	private static int helper(int[] input, int currIndex, int lastBigIndex, int length) {
		if (currIndex == input.length) {
			return length;
		}
		
		int len1 = 0;
		if (input[lastBigIndex] < input[currIndex]) {
			len1 = helper(input, currIndex+1, currIndex, length + 1);
		}
		int len2 = helper(input, currIndex+1, lastBigIndex, length);

		return Math.max(len1, len2);
	}
	
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
	
	public static void main(String[] args) {
		// int[] input = {10,9,2,5,3,7,101,18};
		int[] input = {0, 8, 4, 12, 2};
		
		int longest = lengthOfLIS(input);
		System.out.println(longest);
	}
}
