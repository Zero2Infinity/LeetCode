/**
 * 
 */
package Medium.p1366;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;

public class Solution {

	/*
	 * 
	 * Time complexity: 
	 * Space complexity: 
	 */
	public String rankTeams(String[] votes) {
		// return using_arr(votes);
		return using_list(votes);
	}
	
	private String using_list(String[] votes) {
		int TEAMS = votes[0].length();
		// Build the Team\VoteCounts
		Map<Character, int[]> map = new HashMap<>();
		
		for (String s : votes) {
			for (int i = 0; i < s.length(); i++) {
				if (!map.containsKey(s.charAt(i)))
					map.put(s.charAt(i), new int[TEAMS]);
				map.get(s.charAt(i))[i] ++;
			}
		}
		
		// How to sort - needs custom sorting algorithm
		List<Character> list = new ArrayList<>(map.keySet());
		Collections.sort(list, new Comparator<Character>() {

			@Override
			public int compare(Character a, Character b) {
				for (int i = 0; i < TEAMS; i++) {
					if (map.get(a)[i] != map.get(b)[i]) {
						return map.get(b)[i] - map.get(a)[i];
					}
				}
				return a - b;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for (Character c : list) {
			sb.append(c);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String[] votes = {"ABC","ACB","ABC","ACB","ACB"};
		Solution s = new Solution();
		String result = s.rankTeams(votes);
		System.out.print(result);
	}

}
