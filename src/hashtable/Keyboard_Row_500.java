package hashtable;
import java.util.List;
import java.util.ArrayList;

/*
Given an array of strings words, 
return the words that can be typed using letters of the alphabet on only one row of American keyboard like the image below.

In the American keyboard:

the first row consists of the characters "qwertyuiop",
the second row consists of the characters "asdfghjkl", and
the third row consists of the characters "zxcvbnm".

 

Example 1:

Input: words = ["Hello","Alaska","Dad","Peace"]
Output: ["Alaska","Dad"]
Example 2:

Input: words = ["omk"]
Output: []
Example 3:

Input: words = ["adsdf","sfd"]
Output: ["adsdf","sfd"]
 

Constraints:

1 <= words.length <= 20
1 <= words[i].length <= 100
words[i] consists of English letters (both lowercase and uppercase). 
*/
class Keyboard_Row_500 {
    public String[] findWords(String[] words) {
        int[] rowOne = new int[58];

        // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 
        // A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z 

        // 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57
        // a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
        
        String one = "qwertyuiop";

        for (char c: one.toCharArray()) {
        	rowOne[c - 'A'] = 1;
        	rowOne[c - 'A' - 32] = 1;
        }

        one = "asdfghjkl";

        for (char c: one.toCharArray()) {
        	rowOne[c - 'A'] = 2;
        	rowOne[c - 'A' - 32] = 2;
        }

        one = "zxcvbnm";

        for (char c: one.toCharArray()) {
        	rowOne[c - 'A'] = 3;
        	rowOne[c - 'A' - 32] = 3;
        }	

        List<String> res = new ArrayList<String>();

        for (String s: words) {
        	int rowNumber = 0;
        	int i = 0;
        	for (i = 0; i < s.length(); ++i) {
        		char c = s.charAt(i);
        		if (rowNumber == 0) { 
        			rowNumber = rowOne[c - 'A']; 
        			continue; 
        		}

        		if (rowNumber != rowOne[c - 'A']) break;
        	}

        	if (i == s.length()) res.add(s);
        }

        return res.toArray(new String[0]);
    }

    public static void main(String[] args) {
    	String[] res = new Keyboard_Row_500().findWords(new String[] { "Hello","Alaska","Dad","Peace" });

    	for(String k: res) {
    		System.out.println(k);
    	}

    	res = new Keyboard_Row_500().findWords(new String[] { "omk" });

    	for(String k: res) {
    		System.out.println(k);
    	}

    	res = new Keyboard_Row_500().findWords(new String[] { "adsdf","sfd" });

    	for(String k: res) {
    		System.out.println(k);
    	}
    }
}