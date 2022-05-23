/**
 * 
 */
package Design.RankTeamByVotes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author rahul
 *
 */
public class Solution {
	
	public List<String> rankTeams(List<List<String>> votes) {
		List<String> result = null;
		int TEAMS = votes.get(0).size();
		Map<String, int[]> map = new HashMap<>();
		
		for (List<String> list : votes) {
			for (int i = 0; i < list.size(); i++) {
				String team = list.get(i);
				if (!map.containsKey(team)) 
					map.put(team, new int[TEAMS]);
				map.get(team)[i] ++;
			}
		}
		
		PriorityQueue<String> pq = new PriorityQueue<String>(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				for (int i = 0; i < TEAMS; i++) {
					if (map.get(a)[i] != map.get(b)[i]) {
						return map.get(b)[i] - map.get(a)[i];
					}
				}
				return a.compareTo(b);
			}
		});
		
		for (Map.Entry<String, int[]> entry : map.entrySet()) {
			pq.offer(entry.getKey());
		}
		
		int k = 2;
		while (!pq.isEmpty()) {
			if (k-- == 0) break;
			System.out.println(pq.poll());
		}

		return  result;
	}

	public static void main(String[] args) {
		List<List<String>> votes = Arrays.asList(Arrays.asList("TeamA", "TeamB", "TeamC"),
							   		 			 Arrays.asList("TeamA", "TeamC", "TeamB"),
							   		 			 Arrays.asList("TeamA", "TeamB", "TeamC"),
							   		 			 Arrays.asList("TeamA", "TeamC", "TeamB"),
							   		 			 Arrays.asList("TeamA", "TeamC", "TeamB"));
		Solution solution = new Solution();
		solution.rankTeams(votes);
	}

}
