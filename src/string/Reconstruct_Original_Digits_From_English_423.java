package string;

/*
Given a string s containing an out-of-order English representation of digits 0-9, 
return the digits in ascending order.

Example 1:

Input: s = "owoztneoer"
Output: "012"

Example 2:

Input: s = "fviefuro"
Output: "45"

Constraints:

1 <= s.length <= 105
s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
s is guaranteed to be valid.
*/
class Reconstruct_Original_Digits_From_English_423 {
    public String originalDigits(String s) {
    	int[] dict = new int[26];
        for (char c: s.toCharArray())
        	dict[c - 'a']++;
        
        int count[] = new int[10];

        /*
        zero one two three four five six seven eight nine 

        a b c d e f g h i j k l m n o p q r s t u v w x y z
        e:9
        o:4
        n:4
        i:4
        t:3
        r:3
        h:2
        f:2
        v:2
        s:2
        u:1
        g:1
        x:1
        z:1
        w:1
		*/
        count[0] = dict[25];
        count[2] = dict[22];
        count[4] = dict[20];
        count[6] = dict[23];
        count[8] = dict[6];
        count[3] = dict[7] - count[8];
        count[5] = dict[5] - count[4];
        count[7] = dict[18] - count[6];
        count[9] = dict[8] - count[5] - count[6] - count[8];
        count[1] = dict[13] - count[7] - 2 * count[9];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count.length; ++i) 
        	for (int j = 0; j < count[i]; ++j)
        		sb.append(i);

        return sb.toString();
    }

    public void main(String[] args) {
    	Reconstruct_Original_Digits_From_English_423 obj =
    		new Reconstruct_Original_Digits_From_English_423();

    	System.out.println(obj.originalDigits("owoztneoer"));


    }
}