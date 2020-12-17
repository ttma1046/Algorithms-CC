package tree;
import java.util.List;
import java.util.ArrayList;
/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:

Input: [1,[4,[6]]]
Output: 17
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

class Nested_List_Weight_Sum_II_364 {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		int weighted = 0, unweighted = 0;
		while (!nestedList.isEmpty()) {
			List<NestedInteger> nextList = new ArrayList<>();
			for (NestedInteger item : nestedList) {
				if (item.isInteger()) {
					unweighted += item.getInteger();
				} else {
					nextList.addAll(item.getList());
				}
			}
			weighted += unweighted;
			nestedList = nextList;
		}

		return weighted;
	}
}

class Better_BFS {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		int prevSum = 0, totalSum = 0;
		Deque<NestedInteger> queue = new ArrayDeque();
		for (NestedInteger ni : nestedList) {
			queue.offerLast(ni);
		}

		while (!queue.isEmpty()) {
			int size = queue.size(), levelSum = 0;
			for (int i = 0; i < size; i++) {
				NestedInteger current = queue.pollFirst();
				if (current.isInteger()) {
					levelSum += current.getInteger();
				} else {
					for (NestedInteger ni : current.getList()) {
						queue.offerLast(ni);
					}
				}
			}
			prevSum += levelSum;
			totalSum += prevSum;
		}
		return totalSum;
	}
}

class BFS {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		if (nestedList == null) return 0;
		Queue<NestedInteger> queue = new LinkedList<NestedInteger>();
		int prev = 0;
		int total = 0;
		for (NestedInteger next : nestedList) {
			queue.offer(next);
		}

		while (!queue.isEmpty()) {
			int size = queue.size();
			int levelSum = 0;
			for (int i = 0; i < size; i++) {
				NestedInteger current = queue.poll();
				if (current.isInteger()) levelSum += current.getInteger();
				List<NestedInteger> nextList = current.getList();
				if (nextList != null) {
					for (NestedInteger next : nextList) {
						queue.offer(next);
					}
				}
			}
			prev += levelSum;
			total += prev;
		}
		return total;
	}
}

class OnePass_DFS {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		int depthSum = dfs(nestedList, 1);
		return flatSum * (max + 1) - depthSum;
	}

	int flatSum = 0;
	int max = 1;
	private int dfs(List<NestedInteger> input, int depth) {
		if (input == null || input.size() == 0) return 0;
		int sum = 0;
		for (NestedInteger i : input) {
			if (i.isInteger()) {
				max = Math.max(depth, max);
				sum += i.getInteger() * depth;
				flatSum += i.getInteger();
			} else {
				sum += dfs(i.getList(), depth + 1);
			}
		}
		return sum;
	}
}

class OnePass {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		return helper(nestedList, 0);
	}

	private int helper(List<NestedInteger> niList, int prev) {
		int intSum = prev;
		List<NestedInteger> levelBreak = new ArrayList<>();

		for (NestedInteger ni : niList) {
			if (ni.isInteger()) {
				intSum += ni.getInteger();
			} else {
				levelBreak.addAll(ni.getList());
			}
		}

		int listSum = levelBreak.isEmpty() ? 0 : helper(levelBreak, intSum);

		return listSum + intSum;
	}
}

class TwoPass_DFS {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		if (nestedList == null || nestedList.size() == 0) return 0;
		int h = helper(nestedList);
		int res = getSum(nestedList, h);
		return res;
	}
	private int getSum(List<NestedInteger> l, int layer) {
		int sum = 0;
		if (l == null || l.size() == 0) return sum;
		for (NestedInteger n : l) {
			if (n.isInteger()) sum += n.getInteger() * layer;
			else sum += getSum(n.getList(), layer - 1);
		}
		return sum;
	}
	private int helper(List<NestedInteger> l) {
		if (l == null || l.size() == 0) return 0;
		int max = 0;
		for (NestedInteger n : l) {
			if (n.isInteger()) max = Math.max(max, 1);
			else max = Math.max(max, helper(n.getList()) + 1);
		}
		return max;
	}
}

class HashMap {
	int maxDepth = 0;

	public int depthSumInverse(List<NestedInteger> nestedList) {
		//HashMap solution. We use HashMap to store nums in each depth before we find the maxDepth
		//we will do the sum calculation in the last

		HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();

		DFS( nestedList, 1, hs );

		int sum = 0;

		//get sum
		for ( int i = 1; i <= maxDepth; i++ ) {
			//put a checker here in case we dont have integer in one layer
			if ( hs.containsKey(i ) ) sum += hs.get(i) * (maxDepth + 1 - i);
		}

		return sum;
	}

	private void DFS(List<NestedInteger> nestedList, int depth,  HashMap<Integer, Integer> hs ) {
		//boundary check

		if (nestedList.isEmpty()) return;

		//update maxDepth if possible
		maxDepth = Math.max(maxDepth, depth);

		for ( NestedInteger temp : nestedList ) {
			if ( temp.isInteger() ) {
				//if temp is integer
				if ( !hs.containsKey(depth) ) {
					hs.put( depth, temp.getInteger()  );
				} else {
					hs.put( depth, hs.get(depth) + temp.getInteger()  );
				}
			} else {
				//if temp is list
				DFS(  temp.getList(), depth + 1, hs  );
			}
		}
	}
}

class DFS_C++ {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		List<Integer> result;
		for (NestedInteger ni : nestedList) {
			dfs(ni, 0, result);
		}
		//post processing
		int sum = 0;
		for (int i = result.size() - 1, level = 1; i >= 0; i--, level++) {
			sum += result[i] * level;
		}

		return sum;
	}

	private void dfs(NestedInteger ni, int depth, List<Integer> result) {
		if (result.size() < depth + 1) result.resize(depth + 1);
		if (ni.isInteger()) {
			result[depth] += ni.getInteger();
		} else {
			for (NestedInteger n_ni : ni.getList()) {
				dfs(n_ni, depth + 1, result);
			}
		}

	}
}
