It is a 2D-DP problem.
DP[i][j] means how many choic for total i dices and the last number is j.
For each DP[i][j] we need to remove some invalid choice since there is a restriction for consecutive times.

Let's take an example:
Assuming restriction for 1 is 2 times and we meet the case axxb (a, b are indexes of uncertain values). We all know we can not take 1 at index-b when xx = '11', these are all invaild so we need to remove them.

First of all, the total possible cases of dp[b][1] are sum of dp[b-1][1~6]
for index-b if we want to choose 1 so we need to consider the case that the two consecutive number before b is '11'. Also we need to be careful about value at the index-a. It shouldn't be '1'. In short we need to remove all possible cases of a11 and a is not 1.
The transition equation = dp[b][1] = sum(dp[b-1][1~6]) - sum(dp[a][2~6(except 1)])

Following is my code:
I save the sum of dp[i][1~6] at dp[i][7] for easier calculation.

class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        long divisor = (long)Math.pow(10, 9) + 7;
        long[][] dp = new long[n][7];
        for(int i = 0; i < 6; i++) {
            dp[0][i] = 1;
        }
        dp[0][6] = 6;
        for(int i = 1; i < n; i++) {
            long sum = 0;
            for(int j = 0; j < 6; j++) {
                dp[i][j] = dp[i - 1][6];
                if(i - rollMax[j] < 0) {
                    sum = (sum + dp[i][j]) % divisor;
                }
                else {
                    if(i - rollMax[j] - 1 >= 0) dp[i][j] = (dp[i][j] - (dp[i - rollMax[j] - 1][6] - dp[i - rollMax[j] - 1][j])) % divisor + divisor;
                    else dp[i][j] = (dp[i][j] - 1) % divisor;
                    sum = (sum + dp[i][j]) % divisor;
                }

            }
            dp[i][6] = sum;
        }
        return (int)(dp[n - 1][6]);
    }
    
}