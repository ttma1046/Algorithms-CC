package array;
/*
Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 

Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3

Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.

Constraints:

1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length
*/

class Jump_Game_III_1306 {
    public boolean canReach(int[] arr, int st) {
        if (st >= 0 && st < arr.length && arr[st] < arr.length) {
            int jump = arr[st];
            arr[st] += arr.length;
            return jump == 0 || canReach(arr, st + jump) || canReach(arr, st - jump);
        }
        return false;
    }

    public boolean canReach(int[] arr, int st) {
        int n = arr.length;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()) {
            int node = q.poll();

            if (arr[node] == 0) return true;

            if (arr[node] < 0) continue;

            if (node + arr[node] < n) q.add(node + arr[node]);
            if (node - arr[node] >= 0) q.add(node - arr[node]);

            arr[node] = -arr[node];
        }
        return false;
    }

    // jump game III dfs
    public boolean canReach(int[] arr, int start) {
        if (start < 0 || start >= arr.length || arr[start] < 0) return false;
        if (arr[start] == 0) return true;
        arr[start] *= -1;
        return canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
    }

    public static void main(String[] args) {
        Jump_Game_III_1306 obj = new Jump_Game_III_1306();
    }
}