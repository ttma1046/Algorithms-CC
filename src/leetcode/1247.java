  public int minimumSwap(String s1, String s2) {
        int x1 = 0; // number of 'x' in s1 (skip equal chars at same index)
		int y1 = 0; // number of 'y' in s1 (skip equal chars at same index)
		int x2 = 0; // number of 'x' in s2 (skip equal chars at same index)
		int y2 = 0; // number of 'y' in s2 (skip equal chars at same index)

        for(int i = 0; i < s1.length(); i ++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(c1 == c2){ // skip chars that are equal at the same index in s1 and s2
                continue;
            }
            if(c1 == 'x'){
                x1 ++;
            }else{
                y1 ++;
            }
            if(c2 == 'x'){
                x2 ++;
            }else{
                y2 ++;
            }
        } // end for
        
        // After skip "c1 == c2", check the number of  'x' and 'y' left in s1 and s2.
        if((x1 + x2) % 2 != 0 || (y1 + y2) % 2 != 0){
            return -1; // if number of 'x' or 'y' is odd, we can not make s1 equals to s2
        }
        
        int swaps = x1 / 2 + y1 / 2 + (x1 % 2) * 2;
        // Cases to do 1 swap:
        // "xx" => x1 / 2 => how many pairs of 'x' we have ?
        // "yy" => y1 / 2 => how many pairs of 'y' we have ?
        // 
        // Cases to do 2 swaps:
        // "xy" or "yx" =>  x1 % 2
                 
        return swaps;        
    } 
Example:
Input:
s1 = "xxyyxyxyxx",
s2 = "xyyxyxxxyx"

Skip equals chars, then:
s1 = "xyxyyx",
s2 = "yxyxxy"

x1 = 3, y1 = 3
x2 = 3, y2 = 3

There are 6 'x' and 6 'y', so it's valid to do swaps.

check 'x' pairs in s1
s1 = "xyxyyx",
s2 = "yxyxxy"

do 1 swap
s1 = "yyxyyx",
s2 = "yxxxxy"

ignore the equal chars
s1 = "yyyx"
s2 = "xxxy"

check 'y' pairs in s1
s1 = "yyyx"
s2 = "xxxy"

do 1 swap
s1 = "yxyx"
s2 = "yxxy"

ignore the equal chars
s1 = "yx"
s2 = "xy"

do 2 swaps
s1 = "xy"
s2 = "xy"

Here it is.