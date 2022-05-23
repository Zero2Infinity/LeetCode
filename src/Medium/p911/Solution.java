package Medium.p911;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class TopVotedCandidate {

	private Map<Integer, Integer> map = new HashMap<>();
	private int[] times;
	private int[] persons;

	// Build freq[person] to count votes and keep track of max at each element
    public TopVotedCandidate(int[] persons, int[] times) {
    	this.times = times; this.persons = persons;
    	
        int[] count = new int[persons.length + 1];
        int winner = -1;
        for (int i = 0; i < persons.length; i++) {
        	count[persons[i]] ++;
        	
        	if (map.isEmpty() || count[winner] <= count[persons[i]]) {
        		winner = persons[i];
        	}
        	map.put(times[i], winner);
        }
        
    }
    
    // BS: learned that build-in method also gives insertion point index if missing value.
    public int q(int t) {
        // int idx = Arrays.binarySearch(times, t);
        // int leading = map.get(times[idx < 0 ? -idx - 2 : idx]);
        // System.out.println(leading);
    	int idx = binarySearch(times, t);
    	System.out.println(times[idx]);
        return map.get(times[idx]);
    }
    
    private int binarySearch(int[] times, int t) {
    	int start = 0, end = times.length - 1;
		int middle = start + (end-start) / 2;
    	while (start <= end) {
			middle = start + (end - start) / 2;
			if (t == times[middle]) 
				return middle;
			else if (t >= times[middle])  		
				start = middle + 1;
			else 
				end = middle - 1;
    	}
		if (end == times.length - 1) return end;
		return start;
    }
}

public class Solution {

	public static void main(String[] args) {
		int[] persons = {0,1,0,1,1};
		int[] times = {24,29,31,76,81};
		TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);
//		topVotedCandidate.q(28); 
//		topVotedCandidate.q(24); 
//		topVotedCandidate.q(29); 
//		topVotedCandidate.q(77); 
//		topVotedCandidate.q(30); 
//		topVotedCandidate.q(25); 
//		topVotedCandidate.q(76); 
//		topVotedCandidate.q(75); 
//		topVotedCandidate.q(81); 
//		topVotedCandidate.q(80); 
		topVotedCandidate.q(23); 
		topVotedCandidate.q(88); 
	}

}
