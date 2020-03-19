package Easy.p1;

import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum
 * @author rahul
 *
 */
public class Solution {
    // O(n^2)
    public int[] twoSum_n2(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
    
    // Time Complexity = O(n)
    // Space Complexity = O(n)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> memory = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complements = target - nums[i];
            if (memory.containsKey(complements)) {
                return new int[] {memory.get(complements), i};
            }
            memory.put(nums[i], i);
        }
        
        throw new IllegalArgumentException("No two sum solution!");
    }
    
    public static void main(String[] args) {
    	// Entry point to test above methods
    }
}
