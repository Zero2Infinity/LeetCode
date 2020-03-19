package Medium.p208;

public class Solution {

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("apple");
		System.out.println(trie.search("apple"));   // returns true
		System.out.println(trie.search("app"));     // returns false
		System.out.println(trie.startsWith("app")); // returns true
		trie.insert("appl");   
		System.out.println(trie.search("app"));     // returns false
		System.out.println(trie.search("appl"));     // returns true
	}
}
