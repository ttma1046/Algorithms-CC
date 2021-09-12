
package leetcode;

import java.util.List;
import java.util.ArrayList;

class Maximum_Product_of_the_Length_of_Two_Palindromic_Subsequences_2002 {
   public int maxProduct(String s)  {
        int ans = 1;
        int n = s.length();
        List <List<String>> combs = getAllSubSeq(s, (int)Math.pow(2, n));
        for (List<String> comb : combs) {
            String s1 = comb.get(0);
            String s2 = comb.get(1);
            if (s1.length() == 0 || s2.length() == 0) continue;
            ans = Math.max(ans, longestSubSeq(s1) * longestSubSeq(s2));
        }
        return ans;
    }

    private int longestSubSeq(String s) {
        int n = s.length();
        
        // if (n == 0) return 0;
        
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    private List<List<String>> getAllSubSeq(String s, int val) {
        List<List<String>> res = new ArrayList<>();
        int len = s.length();

        for (int n = 1; n < val; n++) {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            for (int d = len - 1; d >= 0; d--) {
                int bit = n >> d & 1;
                if (bit == 1) s1.append(s.charAt(len - d - 1));
                else s2.append(s.charAt(len - d - 1));
            }

            List<String> tmp = new ArrayList<>();
            tmp.add(s1.toString());
            tmp.add(s2.toString());
            res.add(tmp);
        }
        
        return res;
    }

    public static void main(String[] args) {
    	Maximum_Product_of_the_Length_of_Two_Palindromic_Subsequences_2002 obj = new Maximum_Product_of_the_Length_of_Two_Palindromic_Subsequences_2002();

    	System.out.println(obj.maxProduct("leetcodecom"));

    	System.out.println(obj.maxProduct("bb"));

    	System.out.println(obj.maxProduct("accbcaxxcxx"));
    }
}