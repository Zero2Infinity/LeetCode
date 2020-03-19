package Medium.p208;

import java.util.HashMap;

public class Trie {

	class Node {
		char ch;
		HashMap<Character, Node> childrens = new HashMap<>();
		boolean isEndNode;
		
		public Node(char ch) {
			this.ch = ch;
		}
	}
	
	Node root;

	public Trie() {
		root = new Node('0');
	}
	
	public void insert(String word) {
		Node currNode = root;
		for (char c : word.toCharArray()) {
			if (currNode.childrens.containsKey(c)) {
				currNode = currNode.childrens.get(c);
			} else {
				Node newNode = new Node(c);
				currNode.childrens.put(c, newNode);
				currNode = newNode;
			}
		}
		currNode.isEndNode = true;
	}
	
	public boolean search(String word) {
		Node currNode = root; 
		for (char c : word.toCharArray()) {
			if (currNode.childrens.containsKey(c)) {
				currNode = currNode.childrens.get(c);
			} else {
				return false;
			}
		}
		return currNode.isEndNode;
	}

	public boolean startsWith(String word) {
		return true;
		
	}

	public void delete(char ch) {
		
	}
	
}
