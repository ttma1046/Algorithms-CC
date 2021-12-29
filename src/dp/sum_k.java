package dp;
class sum_k {
    public int getUniqueComb(int sum, int k) {
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int j = 1; j <= k; ++j) 
            for (int i = j; i <= sum; i++) 
                dp[i] += dp[i - j];
            
        
        return dp[sum];
    }

    public static void main(String[] args) {
        sum_k obj = new sum_k();
        System.out.println(obj.change(8, 3));
    }
}