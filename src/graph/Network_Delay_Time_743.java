package graph;
/*

You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

 

Example 1:


Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
 

Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
*/

class Network_Delay_Time_743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] table = new int[n][n];
        
        for(int[] time: times) {
            table[time[0] - 1][time[1] - 1] = time[2];
        }
        
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (i != k - 1 && ) {
                int t = table[k - 1][i];
                if (t == 0) return -1;
                res += t;


            }
            
        }
        
        return res;
    }
}
