package contest;
/*
Given a binary string s ​​​​​without leading zeros, 
return true​​​ if s contains at most one contiguous segment of ones. Otherwise, return false.

Example 1:

Input: s = "1001"
Output: false
Explanation: The ones do not form a contiguous segment.

Example 2:

Input: s = "110"
Output: true
 

Constraints:

1 <= s.length <= 100
s[i]​​​​ is either '0' or '1'.
s[0] is '1'.
*/
class Check_if_Binary_String_Has_at_Most_One_Segment_of_One_5679 {
    public boolean checkOnesSegment(String s) {
        if (s == "") {
            return false;
        }
        
        if (s.equals("1")) {
            return true;
        }
        
        if (s.equals("10")) {
            return true;
        }
        
        boolean res = false;
        boolean flag = false;
        for(char c: s.toCharArray()) {
            if (c == '1') {
                if (flag == true) {
                    if (res == true) { 
                        res = false; 
                    } else { 
                        res = true; 
                    }
                    
                } else {
                    flag = true;
                }
            } else {
                flag = false;
            }
        }
        
        
        return res;
    }
}