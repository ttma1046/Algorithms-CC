package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
/*
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
*/

class K_Closest_Points_to_Origin_973 {
	public int[][] kClosestSlow(int[][] points, int K) {
		Arrays.sort(points, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				// if the heights are equal, compare k-values
				// return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
				return a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1];
			}
		});

		int[][] result = new int[K][2];
		for (int i = 0; i < K; i++) {
			result[i] = points[i];
		}

		return result;
	}

	/*
	Complexity Analysis

	Time Complexity: O(NlogN), where N is the length of points.

	Space Complexity: O(N).
	*/
	public int[][] kClosestIII(int[][] points, int K) {
		int length = points.length;
		int[] distances = new int[length];
		for (int i = 0; i < length; i++) {
			distances[i] = getDis(points[i]);
		}

		Arrays.sort(distances);
		int distance = distances[K - 1];


		int[][] result = new int[K][2];

		int t = 0;
		for (int i = 0; i < length; i++) {
			if (getDis(points[i]) <= distance) {
				result[t++] = points[i];
			}
		}

		return result;
	}

	private int getDis(int[] point) {
		return point[0] * point[0] + point[1] * point[1];
	}

	int[][] points;
	public int[][] kClosestII(int[][] points, int K) {
		this.points = points;
		sort(0, points.length - 1, K);
		return Arrays.copyOfRange(points, 0, K);
	}

	public void sort(int i, int j, int K) {
		if (i >= j) return;

		int k = ThreadLocalRandom.current().nextInt(i, j);
		System.out.println(k);

		swap(i, k);


		int mid = partition(i, j);


		int leftLength = mid - i + 1;


		if (K < leftLength)
			sort(i, mid - 1, K);
		else if (K > leftLength)
			sort(mid + 1, j, K - leftLength);
	}

	public int partition(int i, int j) {
		int oldi = i;

		int pivot = dist(i);
		i++;

		while (true) {
			while (i < j && dist(i) < pivot)
				i++;
			while (i <= j && dist(j) > pivot)
				j--;
			if (i >= j) break;
			swap(i++, j--);
		}
		swap(oldi, j);
		return j;
	}

	public int dist(int i) {
		return points[i][0] * points[i][0] + points[i][1] * points[i][1];
	}

	public void swap(int i, int j) {
		int t0 = points[i][0], t1 = points[i][1];
		points[i][0] = points[j][0];
		points[i][1] = points[j][1];
		points[j][0] = t0;
		points[j][1] = t1;
	}

	// quick sort
	public int[][] kClosestQuickSort(int[][] points, int K) {
		int len = points.length,
		    l = 0, r = len - 1, mid = 0;
		while (l < r) {
			mid = partitionQuickSort(points, l, r);
			if (mid == K) break;
			if (mid < K) l = mid + 1;
			if (mid > K) r = mid - 1;
		}
		return Arrays.copyOfRange(points, 0, K);
	}

	public int partitionQuickSort(int[][] A, int l, int r) {
		int[] pivot = A[l];
		while (l < r) {
			while (l < r && compareQuickSort(A[r], pivot) >= 0) {
				r--;
			}
			A[l] = A[r];
			while (l < r && compareQuickSort(A[l], pivot) <= 0) {
				l++;
			}
			A[r] = A[l];
		}
		A[l] = pivot;
		return l;
	}

	private int compareQuickSort(int[] p1, int[] p2) {
		return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
	}


	public int[][] kClosest(int[][] points, int K) {
		int length = points.length, left = 0, right = length - 1, mid = 0;

		while (left < right) {
			mid = partition(points, left, right);
			if (mid == K) break;

			if (mid < K) {
				left = mid + 1;
			}

			if (mid > K) {
				right = mid - 1;
			}
		}

		return Arrays.copyOfRange(points, 0, K);
	}

	private int partition(int[][] points, int l, int r) {
		int[] pivot = points[l];
		while (l < r) {
			while (l < r && compare(points[r], pivot) >= 0) {
				r--;
			}
			points[l] = points[r];
			while (l < r && compare(points[l], pivot) <= 0) {
				l++;
			}
			points[r] = points[l];
		}

		points[l] = pivot;
		return r;
	}

	private int compare(int[] pI, int[] pJ) {
		return pI[0] * pI[0] + pI[1] * pI[1] - pJ[0] * pJ[0] - pJ[1] * pJ[1];
	}

	public static void main(String[] args) {
		int[][] points = new int[][] {{3, 3}, {5, -1}, { -2, 4}};
		int K = 2;
		int[][] result = new K_Closest_Points_to_Origin_973().kClosest(points, K);
		for (int[] item : result) {
			for (int i : item) {
				System.out.println(i);
			}
		}
		points = new int[][] {{1, 3}, { -2, 2}};
		K = 1;
		result = new K_Closest_Points_to_Origin_973().kClosest(points, K);
		for (int[] item : result) {
			for (int i : item) {
				System.out.println(i);
			}
		}
	}
}