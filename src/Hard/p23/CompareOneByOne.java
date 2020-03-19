package Hard.p23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompareOneByOne {
	
	// Time complexity: O(kN) where k is lists (comparison k-1) and N elements in final list
	private static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
		List<Integer> result = new ArrayList<>();
		List<Integer> pos = new ArrayList<>(Collections.nCopies(lists.size(), 0));
		
		// Infinite loop and break when all elements are added to results
		while (true) {
			// End condition:
			int count = 0;
			for (int l = 0; l < lists.size(); l++) {
				if (pos.get(l) == lists.get(l).size())
					count++;
			}
			if (count == lists.size()) {
				break;
			} else {
				int minVal = Integer.MAX_VALUE;
				int minList = 0;
				for (int listIdx = 0; listIdx < lists.size(); listIdx++) {
					// each list pos in less than that list size
					// && value is min than previous
					if (pos.get(listIdx) < lists.get(listIdx).size()
						&& lists.get(listIdx).get(pos.get(listIdx)) < minVal) {
							minVal = lists.get(listIdx).get(pos.get(listIdx));
							minList = listIdx;
					}
				}
				// After finding minimum from list 0 to N, 
				// add to result and increment that list's pointer + 1
				result.add(minVal);
				pos.set(minList, pos.get(minList) + 1);
			}
		}

		return result;
	}
	
	public static void main(String[] args) { 
		List<List<Integer>> sortedLists = Arrays.asList(Arrays.asList(1, 2, 3, 20), 
														Arrays.asList(-1, 4, 5), 
														Arrays.asList(9, 10));
		
		List<Integer> result = mergeKSortedLists(sortedLists);
		System.out.print(Arrays.toString(result.toArray()));
	}
}
