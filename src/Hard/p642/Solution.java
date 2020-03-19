package Hard.p642;

import java.util.Arrays;
import java.util.List;

public class Solution {

	private final Trie trie;
	private StringBuilder sb = new StringBuilder();
			
	public Solution(String[] sentences, int[] times) {
		trie = new Trie();
		for (int i = 0; i < sentences.length; i++)
			trie.addSentence(sentences[i], times[i]);
	}
	
	public List<String> input(String c) {
		if (c == "#") {
			trie.addSentence(sb.toString(), 1);
			sb.delete(0, sb.length());
			return Arrays.asList();
		} else {
			sb.append(c);
			List<String> ans = trie.search(sb.toString());
			System.out.println(ans.toString());
			return ans;
		}
	}
	
	public static void main(String[] args) {
		// test1();
		test2();
	}
	
	private static void test1() {
		String[] se = {"i love you","island","iroman","i love leetcode"};
		int[] t = {5, 3, 2, 2};
		Solution s = new Solution(se, t);
		s.input("i"); s.input(" "); s.input("a"); s.input("#");
		s.input("i"); s.input(" "); s.input("a"); s.input("#");
		s.input("i"); s.input(" "); s.input("a"); s.input("#");
	}
	private static void test2() {
		String[] se = {"abc", "abbc", "a"};
		int[] t = {3,3,3};
		Solution s = new Solution(se, t);
		s.input("b"); s.input("c"); s.input("#");
		s.input("b"); s.input("c"); s.input("#");
		s.input("a"); s.input("b"); s.input("c"); s.input("#");
		s.input("a"); s.input("b"); s.input("c"); s.input("#");
	}
	
}
