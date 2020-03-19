package Hard.p642;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Trie {
	
	class Node {
		char c;
		Node[] links;
		int occurances;
		
		public Node(char c) {
			this.c = c;
			this.links = new Node[ASCII_SIZE];
		}
		
		public boolean contains(char c) {
			int i = (int) c;
			return (this.links[i] != null);
		}
		
		public Node get(char c) {
			int i = (int) c;
			return links[i];
		}
		
		public void set(char c) {
			int i = (int) c;
			this.links[i] = new Node(c);
		}
	}

	private final int ASCII_SIZE = 128;
	private Node root;
	Map<String, Integer> sentencesBasedOnPrefix = new HashMap<>();
	
	public Trie() {
		root = new Node('0');
	}
	
	public void addSentence(String s, int n) {
		Node curr = root;
		for (char c : s.toCharArray()) {
			if (!curr.contains(c)) {
				curr.set(c);
			}
			curr = curr.get(c);
		}
		curr.occurances += n;
	}

	// Two comments: PQ not required and findAll need to be simplified into two traversing
	public List<String> search(String s) {
		// System.out.println("seach: " + s);
		sentencesBasedOnPrefix.clear();
		findAll(root, s, 0, new StringBuilder());
		
		// NOTE: We don't need PQ, Custom sorting could do the same.
		List<String> topResults = new ArrayList<>();
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(3, 
				(a,b) -> (a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) 
														: b.getValue() - a.getValue()) );
		pq.addAll(sentencesBasedOnPrefix.entrySet());
			
		for (int i = 0; i < Math.min(3, sentencesBasedOnPrefix.size()); i++) {
			topResults.add(pq.poll().getKey());
		}
		
		return topResults;
	}
	
	// Find all sentences
	private void findAll(Node curr, String s, int sIndex, StringBuilder sb) {
		if (curr.occurances > 0 && s.length() <= sb.length()) {
			sentencesBasedOnPrefix.put(sb.toString(), curr.occurances);
		}

		// NOTE: two kind of traversing - traverse user input string and later find sentences on that path
		if (sIndex < s.length()) {
			char ch = s.charAt(sIndex);
			if (!curr.contains(ch)) return;
			findAll(curr.get(ch), s, sIndex + 1, sb.append(ch));
		} else {
			for (int n = 0; n < ASCII_SIZE; n++) {
				char ch = (char) n;
				if (curr.get(ch) != null) {
					findAll(curr.get((char)n), s, sIndex + 1, sb.append(ch));
					sb.deleteCharAt(sb.length()-1);
				}
			}
		}
	}
	
//	private void printMap(Map<String, Integer> map) {
//		for (Map.Entry<String, Integer> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + " = " + entry.getValue()); 
//		}
//	}
}
