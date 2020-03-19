package Medium.p211;

/**
 * 211. Add and Search Word - Data structure design
 * @author rahul
 *
 */
public class Solution {
	class Node {
		char c;
		Node links[] = new Node[26];
		boolean isEnd;

		public Node(char c) { this.c = c; }
	}

    Node root = new Node('0');

    /* Adds a word into the data structure. */
    public void addWord(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            if (curr.links[c-'a'] == null) {
                curr.links[c-'a'] = new Node(c);
            }
            curr = curr.links[c-'a'];
        } 
        curr.isEnd = true;
    }

    public boolean search(String word) {
        return searchBT(word, 0, root);
    }

    public boolean searchBT(String word, int pos, Node curr) {
        boolean status = false;
        if (pos == word.length()) return curr.isEnd;
        char ch = word.charAt(pos);
        if (ch == '.') {
            // BT on non-null link(s)
            for (int l = 0; l < curr.links.length; l++) {
                if (curr.links[l] != null) {
                    status = searchBT(word, pos + 1, curr.links[l]);
                     if (status == true) break;
                }
            }
        } else {
            // BT path doesn't match word
            if (curr.links[ch - 'a'] == null) {
                return false;
            }
            status = searchBT(word, pos + 1, curr.links[ch - 'a']);
        }
        return status;
	}
    public static void main(String[] args) {
        Solution obj = new Solution();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        System.out.println(obj.search("pad")); // -> false
        System.out.println(obj.search("bad")); // -> true
        System.out.println(obj.search(".ad")); // -> true
        System.out.println(obj.search("b..")); // -> true
        System.out.println(obj.search("b.p")); // -> true
    }
}
