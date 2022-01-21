package stack;

import java.util.Stack;

/*
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
return the area of the largest rectangle in the histogram.

Example 1:

Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Example 2:

Input: heights = [2,4]
Output: 4

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104
*/

/*
class Largest_Rectangle_in_Histogram_84_Segment_Tree {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] segment = buildSegmentTree(heights);
        return divideConquer(heights, 0, n - 1, segment);
    }

    private static int divideConquer(int[] height, int start, int end, int[] segment) {
        if (start <= end) {
            if (start == end) return height[start] * 1;
            int minIndex = query(segment, height, start, end);
            int currArea = height[minIndex] * (end - start + 1);
            int leftArea = divideConquer(height, start, minIndex - 1, segment);
            int rightArea = divideConquer(height, minIndex + 1, end, segment);
            return Math.max(Math.max(currArea, leftArea), rightArea);
        }
        return 0;
    }

    private static int[] buildSegmentTree(int[] heights) {
        int n = heights.length;
        int[] segment = new int[2 * n];
        for (int i = n - 1, j = 2 * n - 1; i >= 0; i--, j--) {
            segment[j] = i;
        }
        for (int i = n - 1; i > 0; i--) {
            if (heights[segment[2 * i]] < heights[segment[2 * i + 1]]) segment[i] = segment[2 * i];
            else segment[i] = segment[2 * i + 1];
        }
        return segment;
    }

    private static int query(int[] segment, int[] heights, int start, int end) {
        int n = heights.length;
        int p = start + n;
        int q = end + n;
        int min = Integer.MAX_VALUE;
        int index = -1;
        while (p <= q) {
            if (p % 2 == 1) {
                if (heights[segment[p]] < min) {
                    min = heights[segment[p]];
                    index = segment[p];
                }
                p++;
            }
            if (q % 2 == 0) {
                if (heights[segment[q]] < min) {
                    min = heights[segment[q]];
                    index = segment[q];
                }
                q--;
            }
            p = p >> 1;
            q = q >> 1;
        }
        return index;
    }
}
*/

/*
Time complexity : O(nlogn). Segment tree takes logn for a total of n times.

Space complexity : O(n). Space required for Segment Tree.
*/

class Largest_Rectangle_in_Histogram_84 {
    public int largestRectangleAreaBFI(int[] heights) {
        int maxArea = 0;
        int n = heights.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                int minHeight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++)
                    minHeight = Math.min(minHeight, heights[k]);

                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }

        return maxArea;
    }
    /*
    Complexity Analysis

    Time complexity : O(n^3). We have to find the minimum height bar O(n) lying between every pair O(n^2).

    Space complexity : O(1). Constant space is used.
    */

    public int largestRectangleAreaBFII(int[] heights) {
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i < n; ++i) {
            int minHeight = Math.MAX_VALUE;
            for (int j = i; j < n; ++j) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }

        return maxArea;
    }
    /*
    Time complexity : O(n^2). Every possible pair is considered

    Space complexity : O(1). No extra space is used.
    */

    private int calculate(int[] heights, int start, int end) {
        if (start > end) return 0;

        int minIndex = start;

        for (int i = start; i <= end; ++i)
            if (heights[i] < heights[minIndex])
                minIndex = i;

        return Math.max(heights[minIndex] * (end - start + 1),
                        Math.max(calculate(heights, start, minIndex - 1), calculate(heights, minIndex + 1, end)));
    }

    public int largestRectangleAreaBFII(int[] heights) {
        return calculate(heights, 0, heights.length - 1);
    }

    /*
    Time complexity :

    Average Case: O(nlogn).

    Worst Case: O(n^2). If the numbers in the array are sorted, we don't gain the advantage of divide and conquer.

    Space complexity: O(n). Recursion with worst case depth n
    */

    /*
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int length = heights.length;
        int maxArea = 0;

        for (int i = 0; i < length; i++) {
            while ((stack.peek() != -1)
                    && (heights[stack.peek()] >= heights[i])) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                if (currentHeight * currentWidth > maxArea) {
                    maxArea = currentHeight * currentWidth;
                };
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = length - stack.peek() - 1;

            if (currentHeight * currentWidth > maxArea) {
                maxArea = currentHeight * currentWidth;
            };
        }

        return maxArea;
    }
    */

    public int largestRectangleAreaStack(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        int height = heights.length;
        int max = 0;

        for (int i = 0; i < height; ++i) {
            while (stack.peek() != -1 && heights[i] <= heights[stack.peek()]) {
                int currentHeight = heights[stack.pop()];

                int currentWidth = i - stack.peek() - 1;

                if (currentHeight * currentWidth > max)
                    max = currentHeight * currentWidth;
            }

            stack.push(i);
        }

        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = height - stack.peek() - 1;

            if (currentHeight * currentWidth > max)
                max = currentHeight * currentWidth;
        }

        return max;
    }

    /*
    Complexity Analysis

    Time complexity : O(n)O(n). nn numbers are pushed and popped.

    Space complexity : O(n)O(n). Stack is used.
    */

    public int largestRectangleAreaStackII(int[] heights) {
        int height = heights.length, i = 0, max = 0;

        Deque<Integer> s = new ArrayDeque<>();

        while (i < height) {
            // as long as the current bar is shorter than the last one in the stack
            // we keep popping out the stack and calculate the area based on
            // the popped bar
            while (s.size() > 0 && heights[i] < heights[s.peek()])
                // tricky part is how to handle the index of the left bound
                max = Math.max(max, heights[s.pop()] * (i - (s.isEmpty() ? 0 : s.peek() + 1)));

            // put current bar's index to the stack
            s.push(i++);
        }

        // finally pop out any bar left in the stack and calculate the area based on it
        while (!s.isEmpty())
            max = Math.max(max, heights[s.pop()] * (height - (s.isEmpty() ? 0 : s.peek() + 1)));

        return max;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        if (n == 0) return 0;

        int[] lessFromLeft = new int[n]; // idx of the first bar in the left that is lower than current
        int[] lessFromRight = new int[n]; // idx of the first bar the right that is lower than current

        lessFromLeft[0] = -1;
        lessFromRight[n - 1] = n;

        for (int i = 1; i < n; i++) {
            int p = i - 1;

            while (p >= 0 && heights[p] >= heights[i])
                p = lessFromLeft[p];

            lessFromLeft[i] = p;
        }

        for (int i = n - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < n && heights[p] >= heights[i])
                p = lessFromRight[p];

            lessFromRight[i] = p;
        }

        int maxArea = 0;

        for (int i = 0; i < n; i++)
            maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        
        return maxArea;
    }

    /*
    Complexity Analysis

    Time complexity: O(n). n numbers are pushed and popped.

    Space complexity: O(n). Stack is used.
    */

    public static void main(String[] args) {
        System.out.println(new Largest_Rectangle_in_Histogram_84().largestRectangleArea(new int[] {2, 1, 5, 6, 2, 3}));
        // System.out.println(new Largest_Rectangle_in_Histogram_84().largestRectangleArea(new int[] {2, 4}));
    }
}