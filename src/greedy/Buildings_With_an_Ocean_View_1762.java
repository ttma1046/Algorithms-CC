
package greedy;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/*
There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.



Example 1:

Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
Example 2:

Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.
Example 3:

Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.
Example 4:

Input: heights = [2,2,2,2]
Output: [3]
Explanation: Buildings cannot see the ocean if there are buildings of the same height to its right.


Constraints:

1 <= heights.length <= 105
1 <= heights[i] <= 109
*/

class Buildings_With_an_Ocean_View_1762 {
	public int[] findBuildings(int[] heights) {
		if (heights == null || heights.length == 0) return null;

		int n = heights.length;
		int top = heights[n - 1];

		List<Integer> ans = new ArrayList<Integer>();
		ans.add(n - 1);

		for (int i = n - 2; i >= 0; --i) {
			if (heights[i] > top) {
				top = heights[i];
				ans.add(i);
			}
		}

        int[] res = new int[ans.size()];
    	for (int i = ans.size() - 1; i >= 0; --i)
            res[ans.size() - 1 - i] = ans.get(i);

		return res;
	}

	public static void main(String[] args) {
		Buildings_With_an_Ocean_View_1762 result = new Buildings_With_an_Ocean_View_1762();

		int[] heights = new int[] { 4, 2, 3, 1 };

		int[] res = result.findBuildings(heights);

		for (int k : res) {
			System.out.print(k);
		}

		System.out.println();

		heights = new int[] {4, 3, 2, 1};
		res = result.findBuildings(heights);

		for (int k : res) {
			System.out.print(k);
		}

		System.out.println();

		heights = new int[] {1, 3, 2, 4};
		res = result.findBuildings(heights);

		for (int k : res) {
			System.out.print(k);
		}

		System.out.println();

		heights = new int[] {2, 2, 2, 2};
		res = result.findBuildings(heights);

		for (int k : res) {
			System.out.print(k);
		}

		System.out.println();
	}
}