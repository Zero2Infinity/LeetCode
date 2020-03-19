package Easy.p1170;

import java.util.Arrays;

/**
 * Compare Strings by Frequency of the Smallest Character  
 * @author rahul
 *
 */
public class Solution {
	// Time complexity: O(n) + O(n) + O(n log n) + O(log n)
	public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int freqQueries[] = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            freqQueries[q] = freqOfSmallestChar(queries[q]);
        }
		System.out.println(Arrays.toString(freqQueries));
        
        int freqWords[] = new int[words.length];
        for (int w = 0; w < words.length; w++) {
            freqWords[w] = freqOfSmallestChar(words[w]);
        }
        Arrays.sort(freqWords);
		System.out.println(Arrays.toString(freqWords));
        
        int result[] = new int[queries.length]; int i = 0;
        for (int fQ : freqQueries) {
            int numItemsGreater = findIndexUsingBS(freqWords, fQ);
            System.out.println(numItemsGreater);
            result[i++] = words.length - numItemsGreater;
        }
        
        return result;
    }

    // Time complexity: O(chars in string + 26)
    // Space complexity: O(26)
    private int freqOfSmallestChar(String str) {
        int count = 0;
        int freq[] = new int[26];
        for (char c : str.toCharArray())  freq[c-'a']++;
        
        for (int c = 0; c < 26; c++) {
            if (freq[c] > 0) { count = freq[c]; break; }
        }
        
        return count;
    }
    
    // BS on non-unique incremental array [1,1,2,2,4,5,10]
    private int findIndexUsingBS(int[] arr, int num) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (arr[mid] <= num) {
            	lo = mid + 1;
            } else {
            	hi = mid - 1;
            }
                
        }
        
        return lo;
    }
    
    public static void main(String[] args) {
    	String[] queries = {"aabbabbb","abbbabaa","aabbbabaa","aabba","abb","a","ba","aa","ba","baabbbaaaa","babaa","bbbbabaa"};
    	String[] words = {"b","aaaba","aaaabba","aa","aabaabab","aabbaaabbb","ababb","bbb","aabbbabb","aab","bbaaababba","baaaaa"};
    	int[] result = new Solution().numSmallerByFrequency(queries, words);
    	System.out.print(Arrays.toString(result));
    }
}
