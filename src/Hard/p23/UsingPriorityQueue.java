package Hard.p23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UsingPriorityQueue {

	// Using Minimum Heap to reduce the comparing from compare one by one elements in lists
	// Time complexity: O(N*logk) where N is element in final list and logk comparison between k lists
	// logk = cost of every pop and insert to PQ
	private static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
		List<Integer> result = new ArrayList<>();
		PriorityQueue<List<Integer>> pq = new PriorityQueue<>(new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> l1, List<Integer> l2) {
				return l1.get(0) - l2.get(0);
			}
		});	
		
		// Add all elements in PQ
		// Optimization: Add one index that needs to go through comparison
		for (List<Integer> l : lists) {
			pq.offer(l);
		}
		
		while (!pq.isEmpty()) {
			List<Integer> list = pq.poll();
			int minVal = list.remove(0);
			result.add(minVal);
			if (!list.isEmpty()) {
				pq.offer(list);
			}
		}

		return result;
	}

	public static void main(String[] args) { 

		// Immutable vs Mutable where I need to wrap asList to ArrayList
		List<List<Integer>> sortedLists = Arrays.asList(new ArrayList<>(Arrays.asList(1, 2, 3, 20)), 
														new ArrayList<>(Arrays.asList(-1, 4, 5)), 
														new ArrayList<>(Arrays.asList(9, 10)));
		
		List<Integer> result = mergeKSortedLists(sortedLists);
		System.out.print(Arrays.toString(result.toArray()));
	}
}
