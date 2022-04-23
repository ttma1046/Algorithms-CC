package array;
/*
Given a positive integer n, find the smallest integer 

which has exactly the same digits existing in the integer n and is greater in value than n. 

If no such positive integer exists, return -1.

Note that the returned integer should fit in 32-bit integer, 

if there is a valid answer but it does not fit in 32-bit integer, return -1.

Example 1:

Input: n = 12
Output: 21

Example 2:

Input: n = 21
Output: -1

Constraints:

1 <= n <= 231 - 1
*/
public class Next_Greater_Element_III_556 {
    public int nextGreaterElement(int n) {
        String s = String.valueOf(n);

        char[] cs = s.toCharArray();

        int last = cs[cs.length - 1];

        int index = 0;

        for(int i = cs.length - 1; i > 0; --i) {
            if(cs[i - 1] < cs[i]) {
                index = i;
                break;
            }
        }

        if(index == 0)
            return -1;

        for(int i = cs.length - 1; i >= index; i--) {
            if(cs[i] > cs[index - 1]) {
                swap(cs, i, index - 1);
                break;
            }
        }

        reverse(cs, index, cs.length - 1);
        
        s = String.valueOf(cs);
        
        long res = Long.valueOf(s);
        
        if(res > Integer.MAX_VALUE)
            return -1;

        return (int)res;
    }

    private void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    private void reverse(char[] cs, int i, int j) {
        while(i < j)
        	swap(cs, i++, j--);
    }

    public static void main(String[] args) {
    	Next_Greater_Element_III_556 obj = new Next_Greater_Element_III_556();
    }
}