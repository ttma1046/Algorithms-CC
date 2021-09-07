package monotonicstack;
import java.util.Stack;

/*
Given an array of integers temperatures represents the daily temperatures,

return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.

If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]

Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]

Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
*/

// 小变种， instead of stack里面直接储存value, 我们现在储存index，因为要算距离
class Daliy_temperatures_739 {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; --i) {
            while(stack.size() > 0 && temperatures[stack.peek()] <= temperatures[i]) stack.pop();
            if (stack.size() > 0) {
                res[i] = stack.peek() - i;
            }
            stack.push(i);
        }

        return res;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int [] result = new int[temperatures.length];

        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.empty() && temperatures[stack.peek()] < temperatures[i]) {
                int value = stack.pop();
                result[value] = i - value;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}