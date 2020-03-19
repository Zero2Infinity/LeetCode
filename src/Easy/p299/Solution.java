package Easy.p299;

public class Solution {
	
    public String getHint(String secret, String guess) {
    	String result = "";
    	boolean[] selectedInSecret = new boolean[secret.length()];	// index in secret string
    	boolean[] selectedInGuess = new boolean[guess.length()];	// index in guess string
    	
    	int N = guess.length(); 	// same for secret
    	int bulls = 0, cows = 0;
    	for (int i = 0; i < N; i++) {
    		if (secret.charAt(i) == guess.charAt(i)) {
    			selectedInSecret[i] = true;
    			selectedInGuess[i] = true;
    			bulls++;
    		}
    	}
    	
    	for (int g = 0; g < N; g++) {
    		for (int s = 0; s < N; s++) {
    			if (selectedInGuess[g] != true 
    					&& selectedInSecret[s] != true 
    					&& guess.charAt(g) == secret.charAt(s)) {
    				selectedInSecret[s] = true;
    				selectedInGuess[g] = true;
    				cows++;
    			}
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	result = sb.append(bulls).append("A").append(cows).append("B").toString();

    	return result;
    }
    
	public static void main(String[] args) {
    	String secret = "1122";
    	String guess = "0001";
    	String res = new Solution().getHint(secret, guess);
    	System.out.println(res);
    }
    
}
