package Medium.p347;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution {

	public int[] topKFrequent(int[] nums, int k) {
		// Thoughts:
		// With putting together all simple DS
		// return custom_freq_map_to_sorted_list(nums, k);
		// return map_to_freq_bucket(nums, k);
		// return treemap(nums, k);
		return priority_queue(nums, k);
	}

	// Time complexity: O(N + Log N + K)
	// Space complexity: O
	private int[] priority_queue(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> min_pq = new PriorityQueue<>( (o1, o2) -> o1.getValue() - o2.getValue() );
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        	min_pq.offer(entry);
        	if (min_pq.size() > k) {
        		min_pq.poll();
        	}
        }
        
//        int size = min_pq.size();
//        int[] result = new int[size];
//        for (int i = 0; i < size; i++) {
//        	result[i] = min_pq.poll().getKey();
//        }
//        
//        return result;
        return min_pq.stream().map(o -> o.getKey()).mapToInt(i -> i).toArray();
	}
	
	// Time complexity: O(N + LogN + K)
	// Space complexity: O(Distinct Freq Elements)
	private int[] treemap(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();

		// Time complexity: O(N)
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		// TreeMap insertion time: O(LogN)
		TreeMap<Integer, List<Integer>> freq = new TreeMap<>();
		for (int n : map.keySet()) {
			int f = map.get(n);
			if (!freq.containsKey(f)) {
				freq.put(f, new LinkedList<Integer>());
			}
			freq.get(f).add(n);
		}

		List<Integer> result = new ArrayList<>();
		while (result.size() < k) {
			Map.Entry<Integer, List<Integer>> entry = freq.pollLastEntry();
			result.addAll(entry.getValue());
		}

		return result.stream().mapToInt(i -> i).toArray();
	}

	// Time complexity: O(N + D + K)
	// Space complexity: O(N)
	private int[] map_to_freq_bucket(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		List<Integer>[] bucket = new List[nums.length + 1];
		for (int n : map.keySet()) {
			int freq = map.get(n);
			if (bucket[freq] == null)
				bucket[freq] = new LinkedList<>();
			bucket[freq].add(n);
		}

		List<Integer> result = new LinkedList<>();
		for (int i = bucket.length - 1; i > 0 && k > 0; i--) {
			if (bucket[i] != null) {
				result.addAll(bucket[i]);
				k = k - bucket[i].size();
			}
		}

		return result.stream().mapToInt(i -> i).toArray();
	}

	// Intutive
	// Time complexity: O(N + NLogN + K)
	// Space complexity: O(distinct elements)
	private int[] custom_sorted_freq_map(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int[] result = new int[k];

		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		Comparator<Map.Entry<Integer, Integer>> valueComparator = new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
				return e2.getValue() - e1.getValue();
			}
		};

		// NOTE: Map cannot be sorted so need to copy to ArrayList<Entry>()
		List<Map.Entry<Integer, Integer>> listOfEntries = new ArrayList<>(map.entrySet());

		Collections.sort(listOfEntries, valueComparator);

		// Collect Top-K values from high to low frequency
		for (int i = 0; i < k; i++) {
			result[i] = listOfEntries.get(i).getKey();
		}

		return result;
	}

	public static void main(String[] args) {
		int[] result = new Solution().topKFrequent(new int[] { 2, 2, 4, 4, 4, 4, 3, 3, 3, 3, 1, 1, 1 }, 2);
		// int[] result = new Solution().topKFrequent(new int[] {2}, 1);
		System.out.println(Arrays.toString(result));
	}

}
