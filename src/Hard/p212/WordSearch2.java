package Hard.p212;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class TrieNode {
	char c;
	TrieNode links[];
	String word;
	
	public TrieNode(char c) { this.c = c; links = new TrieNode[26]; }
}

public class WordSearch2 {

	static List<String> result = new ArrayList<>();						// Output
	static Set<String> set = new HashSet<>();							// collection unique words for output
	static int rows, cols;												// rows and cols of board
	static TrieNode root = new TrieNode('0');							// first root node init
	static int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} }; // right , down, left, up
	
	// add new word to Trie; not special pre-processing required
	private static void addWord(String word) {
		TrieNode curr = root;
		for (char c : word.toCharArray()) {
			if (curr.links[c - 'a'] == null) 
				curr.links[c - 'a'] = new TrieNode(c);
			curr = curr.links[c - 'a'];
		}
		curr.word = word;
	}
	
	public static List<String> findWords(char[][] board, String[] words) {
		// Build Prefix Tree of words
		for (String s : words) addWord(s); 

		rows = board.length; 
		cols = board[0].length;
		
		// Pick starting character
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				char start = board[i][j];
				StringBuilder sb = new StringBuilder();
				boolean[][] visited = new boolean[rows][cols];
				// Using Trie: I could check if root node contains link to first char of word; if not stop taking that path
				if (root.links[start - 'a'] != null) {
					sb.append(start);
					visited[i][j] = true;
					findWords(board, i, j, visited, root.links[start - 'a'], sb);	// Note: (oat, oath)
				}
			}
		}

		result.addAll(set);
		return result;
	}
	
	// Pick the direction in board based on two criteria: 
	// Char at boarders need isSafe condition
	// Adjacent char present in current TrieNode links; then recurse to find complete word
	// To me it's look like DFS where I completely go deeper in one direction and then return -> null | word
	private static void findWords(char[][] board, int i, int j, boolean[][] visited, TrieNode curr, StringBuilder sb) {
		if (sb.toString().equals(curr.word)) 
			set.add(curr.word);

		for (int d = 0; d < directions.length; d++) {
			int newY = i + directions[d][0];
			int newX = j + directions[d][1];
			if (isSafe(newY, newX) && !visited[newY][newX]) {
				char c = board[newY][newX];
				if (curr.links[c - 'a'] != null) {
					sb.append(c);
					visited[newY][newX] = true;
					findWords(board, newY, newX, visited, curr.links[c - 'a'], sb);
					visited[newY][newX] = true;
					sb.deleteCharAt(sb.length() - 1);
				}
			}
		}
	}
	
	private static boolean isSafe(int y, int x) {
		return (x >= 0 && x < cols && y >=0 && y < rows);
	}

	public static void main(String[] args) {
		// Test: oat, oath = follow the path till your cover all words in the same path.
//		char[][] board = {  {'o', 'a', 'a', 'n'},
//							{'e', 't', 'a', 'e'},
//							{'i', 'h', 'k', 'r'},
//							{'i', 'f', 'l', 'v'}  };
//		List<String> words = Arrays.asList("oat", "oath", "pea", "eat", "rain");

		// Test: word is bigger means chances of revisiting same cell so used visited[][] to track previous path
//		char[][] board = { {'a', 'a'} };
//		List<String> words = Arrays.asList("aaa");
		
		// Test: No backtracking at all
//		char[][] board = { {'a', 'b'}, {'c', 'd'} };
//		List<String> words = Arrays.asList("ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb");

		// Test: ??
		char[][] board = {  {'a', 'b', 'c'}, 
							{'a', 'e', 'd'}, 
							{'a', 'f', 'g'} };
		List<String> words = Arrays.asList("abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade");

		List<String> result = findWords(board, words.toArray(new String[0]));
		
		System.out.println(result.toString());
	}
	
}
