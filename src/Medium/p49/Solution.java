package Medium.p49;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lower case.
The order of your output does not matter.
 */

public class Solution {
	public static List<List<String>> groupAnagrams_v1(String[] strs) {
		if (strs.length == 0) return new ArrayList<>();

		Map<String, List<String>> map = new HashMap<>();
		for (String s : strs) {
			char[] ch = s.toCharArray();
			// TODO: Costly computations
			Arrays.sort(ch);
			String key = String.valueOf(ch);
			if (!map.containsKey(key)) 
				map.put(key, new ArrayList<String>());
			map.get(key).add(s);
		}
		return new ArrayList<>(map.values());
	}

	public static List<List<String>> groupAnagrams_v2(String[] strs) {
		if (strs.length == 0) return new ArrayList<>();
		
		Map<String, List<String>> map = new HashMap<>();
		for (String s : strs) {
			// Build unique key string
			int[] count = new int[26];
			for (char c : s.toCharArray()) count[c-'a']++;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 26; i++) {
				sb.append('#').append(count[i]);
			}
			
			String key = sb.toString();
			System.out.println(key);
			if (!map.containsKey(key))
				map.put(key, new ArrayList<String>());
			map.get(key).add(s);
		}
		
		return new ArrayList<>(map.values());
	}

	public static void main(String[] args) {

		String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
		List<List<String>> result;
		// result = groupAnagrams_v1(input);
		result = groupAnagrams_v2(input);
		
		for (List<String> l : result) {
			System.out.println(l.toString());
		}
	}
}
