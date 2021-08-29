package divideconquer;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/*
We are given an array of n points in the plane, and the problem is to find out the closest pair of points in the array. This problem arises in a number of applications. For example, in air-traffic control, you may want to monitor planes that come too close together, since this may indicate a possible collision. Recall the following formula for distance between two points p and q.
\left \|pq \right \| = \sqrt{(p_{x}-q_{x})^{2}+ (p_{y}-q_{y})^{2}}     
The Brute force solution is O(n^2), compute the distance between each pair and return the smallest. We can calculate the smallest distance in O(nLogn) time using Divide and Conquer strategy. In this post, a O(n x (Logn)^2) approach is discussed. We will be discussing a O(nLogn) approach in a separate post.

Algorithm 
Following are the detailed steps of a O(n (Logn)^2) algorithm. 
Input: An array of n points P[] 
Output: The smallest distance between two points in the given array.
As a pre-processing step, the input array is sorted according to x coordinates.
1) Find the middle point in the sorted array, we can take P[n/2] as middle point. 
2) Divide the given array in two halves. The first subarray contains points from P[0] to P[n/2]. The second subarray contains points from P[n/2+1] to P[n-1].
3) Recursively find the smallest distances in both subarrays. Let the distances be dl and dr. Find the minimum of dl and dr. Let the minimum be d.
4) From the above 3 steps, we have an upper bound d of minimum distance. Now we need to consider the pairs such that one point in pair is from the left half and the other is from the right half. Consider the vertical line passing through P[n/2] and find all points whose x coordinate is closer than d to the middle vertical line. Build an array strip[] of all such points. 
5) Sort the array strip[] according to y coordinates. This step is O(nLogn). It can be optimized to O(n) by recursively sorting and merging. 
6) Find the smallest distance in strip[]. This is tricky. From the first look, it seems to be a O(n^2) step, but it is actually O(n). It can be proved geometrically that for every point in the strip, we only need to check at most 7 points after it (note that strip is sorted according to Y coordinate). See this for more analysis.
7) Finally return the minimum of d and distance calculated in the above step (step 6)
*/
class Point {
	int x;
	int y;
	public Point(int xx, int yy) {
		this.x = xx;
		this.y = yy;
	}
}

class Cloest_Pair_of_Points {
	private long findClosedPain(int n, int[] xs, int[] ys) {
		int len = xs.length;

		Point[] points = new Point[len];
		for (int i = 0; i < len; i++) {
			points[i] = new Point(xs[i], ys[i]);
		}
		Arrays.sort(points, (a, b) -> a.x - b.x);
		return divide(0, len - 1, points);
	}

	public long divide(int left, int right, Point[] points) {
		long curMinDis = Long.MAX_VALUE; // 当前 最小两点距离， 初始值设置为无穷大

		if (left == right) return curMinDis; // 如果只有一个点，则不存在最近两点距离，返回无穷大。

		// 这里是判断是否为只有两个点， 如果只有两个点的话那么直接求解。
		if (left + 1 == right) return distance(points[left], points[right]); 

		// 分治法： 第一步： 分区， 并求取左右分区最小两点距离
		int mid = left + (right - left) / 2;

		// 通过右移运算出除2，对区域进行合理的划分，使得左右两边保持大致相等个数点。
		long leftMinDis = divide(left, mid, points); 
		long rightMinDis = divide(mid, right, points);

		curMinDis = Math.min(leftMinDis, rightMinDis);

		// 分治法：第二步： 假设距离最近的两个点分别在左右分区中
		// 关键代码，距离最近的两个点，一个位于左边区域，一个位于右边区域，
		// x轴搜索范围【mid - curMinDis, mid + curMinDis]
		// 记录搜索区间内的点的索引，便于进一步计算最小距离
		List<Integer> validPointIndex = new ArrayList<>();
		for (int i = left; i <= right; i++)	
			if (Math.pow(Math.abs(points[mid].x - points[i].x), 2) <= curMinDis)
				validPointIndex.add(i);
		Collections.sort(validPointIndex, (a, b) -> (points[a].y - points[b].y));

		for (int i = 0; i < validPointIndex.size() - 1; i++) { // 基于索引， 进一步计算区间内最小的两点距离
			for (int j = i + 1; j < validPointIndex.size(); j++) {
				// 如果区间内的两件y轴距离大雨curMinDis, 则没有必要计算了，因为，它们的距离肯定大于curMinDis,
				if (Math.pow(Math.abs(points[validPointIndex.get(i)].y
					- points[validPointIndex.get(j)].y), 2) > curMinDis) continue;
				long tempDis = distance(points[validPointIndex.get(i)], points[validPointIndex.get(j)]);
				curMinDis = Math.min(tempDis, curMinDis);
			}
		}

		return curMinDis;
	}

	public long distance(Point p1, Point p2) {
		return (p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x); 
	}
}

