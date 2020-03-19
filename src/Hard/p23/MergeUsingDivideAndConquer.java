package Hard.p23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeUsingDivideAndConquer {

	private static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
		if (lists == null || lists.size() == 0) return null;
		return partition(lists, 0, lists.size()-1);
	}
	
	private static List<Integer> partition(List<List<Integer>> lists, int start, int end) {
		List<Integer> result = new ArrayList<>();
		if (start == end) {
			return lists.get(start);
		} else if (start < end) {
			int mid = start + (end - start) / 2;
			List<Integer> l1 = partition(lists, start, mid);
			List<Integer> l2 = partition(lists, mid + 1, end);
			merge2Lists(result, l1, l2);
		}
		
		return result;
	}
	
	private static void merge2Lists(List<Integer> result, List<Integer> l1, List<Integer> l2) {
		if (l1.isEmpty()) { result.addAll(l2); return; }
		if (l2.isEmpty()) { result.addAll(l1); return; }
		if (l1.get(0) > l2.get(0)) {
			int minVal = l2.remove(0);
			result.add(minVal);
			merge2Lists(result, l1, l2);
		} else {
			int minVal = l1.remove(0);
			result.add(minVal);
			merge2Lists(result, l1, l2);
		}
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
