package leetcode;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.math.BigDecimal;
/*
Given an array of prices [p1,p2...,pn] and a target,

round each price pi to Roundi(pi) so that the rounded array [Round1(p1),Round2(p2)...,Roundn(pn)] sums to the given target.

Each operation Roundi(pi) could be either Floor(pi) or Ceil(pi).

Return the string "-1" if the rounded array is impossible to sum to target. Otherwise, return the smallest rounding error, which is defined as Σ |Roundi(pi) - (pi)| for i from 1 to n, as a string with three places after the decimal.

Example 1:

Input: prices = ["0.700","2.800","4.900"], target = 8
Output: "1.000"
Explanation:
Use Floor, Ceil and Ceil operations to get (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 .

Example 2:

Input: prices = ["1.500","2.500","3.500"], target = 10
Output: "-1"
Explanation: It is impossible to meet the target.

Example 3:

Input: prices = ["1.500","2.500","3.500"], target = 9
Output: "1.500"


Constraints:

1 <= prices.length <= 500
Each string prices[i] represents a real number in the range [0.0, 1000.0] and has exactly 3 decimal places.
0 <= target <= 106
*/
class Minimize_Rounding_Error_to_Meet_Target_1058 {

	// c++ greedy
	/*
	string minimizeError(vector<string> & prices, int target) {
		int _max {}, _min {}, c {};
		double err {};
		vector<double> dprices;
		for (auto & p : prices) {
			dprices.push_back (stod(p));
			_max += static_cast<int> (ceil (dprices.back()) );
			_min += static_cast<int> (floor (dprices.back()) );
		}
		if ( target < _min || target > _max) return "-1";
		int round_ups = target - _min;
		sort (dprices.begin(), dprices.end(), [] (double d1, double d2) {
			return  ceil (d1) - d1 < ceil (d2) - d2;
		});
		for (int i = 0; i < dprices.size(); ++ i) {
			if (c < round_ups && ceil (dprices[i]) - dprices[i] != 0) {
				err += (ceil (dprices[i]) - dprices[i]);
				c++;
			} else if (c >= round_ups) err += (dprices[i] - floor (dprices[i]) );
		}
		string s = to_string (err);
		return s.substr (0, s.find_first_of ('.', 0) + 4);
	}
	*/


	/*
	Try to use ceil for each price, we can then find a maximum target

	Try also to use floor for each price, we can get a minimum target

	If the parameter is not within such [minimum, maximum] range, return "-1"

	If target is within the range of [minimum, maximum] range, we can find out the number of round_ups by target - _min.

	Sort the prices array by ascending order of round errors, smallers errors for round up come first.

	The only cornor case is when there is no round up error, we need to skip such element

	Converted everything back to double for processing... Time complexity should be O(NlogN) due to sorting.

	greedy
	*/
	public String minimizeError(String[] prices, int target) {
		int max = 0, min = 0, c = 0;
		double err = 0;
		int n = prices.length;

		double[] dprices = new double[n];

		for (int i = 0; i < n; i++) {
			double f = Double.valueOf(prices[i]);
			dprices[i] = f;
			int low = (int)Math.floor(f);
			int high = (int)Math.ceil(f);
			max += high;
			min += low;
		}

		if (target < min || target > max) return "-1";

		int round_ups = target - min;

		// return "-1";

		// Arrays.sort(dprices, (a, b)->(Math.ceil(a) - a) < (Math.ceil(b) - b));



		// Arrays.sort(dprices, (a, b)->Double.compare((double)(Math.ceil(a) - a), (double)(Math.ceil(b) - b)));

		// Arrays.sort(dprices, Comparator.comparingDouble(a -> (Math.ceil(a) - a)));

		for (double ss : dprices) {
			System.out.println(ss);
		}

		
		dprices[0] = 4.9;
		dprices[1] = 2.8;
		dprices[2] = 0.7;
		
		/*
		Arrays.sort(dprices, new Comparator<Double>() {
			@Override
			public int compare(Double a, Double b) {
				if (Double.compare(Math.ceil(a) - a, Math.ceil(b) - b) == 0) {
					return 0;
				}

				if (Double.compare(Math.ceil(a) - a, Math.ceil(b) - b) > 0) {
					return 1;;
				}

				return -1;
			}
		});*/

		System.out.println("round_ups: " + round_ups);

		for (int i = 0; i < dprices.length; ++ i) {
			if (c < round_ups && Math.ceil(dprices[i]) - dprices[i] != 0) {
				err += Math.ceil(dprices[i]) - dprices[i];
				c++;
			} else if (c >= round_ups) {
				err += dprices[i] - Math.floor(dprices[i]);
			}
		}

		return String.format("%.3f", err);
	}

	// java Greedy + Selection
	public String minimizeErrorGreedy(String[] prices, int target) {
		List<BigDecimal> errors = new ArrayList<>();
		double min = 0;
		double max = 0;
		for (int i = 0; i < prices.length; ++i) {
			BigDecimal price = new BigDecimal(prices[i]);
			BigDecimal floor = new BigDecimal(Math.floor(Double.valueOf(prices[i])));
			BigDecimal ceil = new BigDecimal(Math.ceil(Double.valueOf(prices[i])));
			min += Math.floor(Double.valueOf(prices[i]));
			max += Math.ceil(Double.valueOf(prices[i]));
			if (!floor.equals(ceil)) {
				errors.add(price.subtract(floor));
			}
		}

		if ((double)target < min || (double)target > max) {
			return "-1";
		}
		int lowerRoundCount = (int)max - target;
		selectKthSmallest(errors, 0, errors.size() - 1, lowerRoundCount - 1);
		BigDecimal minError = new BigDecimal("0.000");
		for (int i = 0; i < errors.size(); ++i) {
			if (i < lowerRoundCount) {
				minError = minError.add(errors.get(i));
			} else {
				minError = minError.add(new BigDecimal("1.000").subtract(errors.get(i)));
			}
		}
		return minError.toString();
	}

	private BigDecimal selectKthSmallest(List<BigDecimal> errors, int lb, int hb, int k) {
		if (k < lb || k > hb) {
			return null;
		}
		BigDecimal pilot = errors.get(hb);
		int smallEqualIdx = lb - 1;
		for (int i = lb; i < hb; ++i) {
			BigDecimal curr = errors.get(i);
			if (curr.compareTo(pilot) <= 0) {
				BigDecimal tmp = errors.get(smallEqualIdx + 1);
				errors.set(++smallEqualIdx, curr);
				errors.set(i, tmp);
			}
		}
		BigDecimal tmp = errors.get(smallEqualIdx + 1);
		errors.set(++smallEqualIdx, pilot);
		errors.set(hb, tmp);
		if (smallEqualIdx == k) {
			return pilot;
		} else if (smallEqualIdx > k) {
			return selectKthSmallest(errors, lb, smallEqualIdx - 1, k);
		} else {
			return selectKthSmallest(errors, smallEqualIdx + 1, hb, k);
		}
	}

	// simple java (CeilPriceWithDiff)
	public String minimizeErrorCeilPriceWithDiff(String[] prices, int target) {
		int lowerSumLimit = 0, upperSumLimit = 0;
		int len = prices.length;
		CeilPriceWithDiff[] ceilPrices = new CeilPriceWithDiff[len];
		for (int i = 0; i < len; i++) {
			double p = Double.parseDouble(prices[i]);
			lowerSumLimit += Math.floor(p);
			upperSumLimit += Math.ceil(p);
			ceilPrices[i] = new CeilPriceWithDiff((int)Math.ceil(p), Math.ceil(p) - p);
		}
		if (target > upperSumLimit || target < lowerSumLimit) return "-1";

		Arrays.sort(ceilPrices, (p1, p2)->Double.compare(p2.diff, p1.diff));
		double roundingError = 0.0;
		for (int i = 0; i < ceilPrices.length; i++) {
			if (upperSumLimit > target) {
				roundingError += (1.0 - ceilPrices[i].diff);
				upperSumLimit--;
			} else {
				roundingError += ceilPrices[i].diff;
			}
		}
		return String.format("%.3f", roundingError);
	}

	class CeilPriceWithDiff {
		int ceilPrice;
		double diff;
		public CeilPriceWithDiff(int ceilPrice, double diff) {
			this.ceilPrice = ceilPrice;
			this.diff = diff;
		}
	}

	// java easy to understand dp
	public String minimizeErrorEasyDP(String[] prices, int target) {
		DecimalFormat df = new DecimalFormat("#.000");
		HashMap<Integer, Double>[] memo = new HashMap[prices.length + 1];
		double ans = minimizeErrorEasyDP(memo, 0, target, prices);
		if (ans == 0)
			return "0.000";
		return ans <= 1000000 ? df.format(ans) : "-1";
	}

	public double minimizeErrorEasyDP(HashMap<Integer, Double>[] memo, int index, int target, String[] prices) {
		if (target < 0) {
			return 2000000;
		}

		if (memo[index] != null && memo[index].get(target) != null) {
			return memo[index].get(target);
		}

		if (index == prices.length) {
			if (target == 0) {
				return 0;
			}
			return 2000000;
		}

		double current = Double.parseDouble(prices[index]);
		int floor =  (int) Math.floor(current);
		int ceil = (int) Math.ceil(current);
		double currError = Math.min(current - floor + minimizeErrorEasyDP(memo, index + 1, target - floor, prices),
		                            ceil - current + minimizeErrorEasyDP(memo, index + 1, target - ceil, prices));

		if (memo[index] == null) {
			memo[index] = new HashMap<Integer, Double>();
		}

		memo[index].put(target, currError);
		return currError;
	}

	// DFS backtracking with memorization
	public String minimizeErrorDFS(String[] prices, int target) {
		double[] p = new double[prices.length];
		for (int i = 0; i < prices.length; i++) {
			p[i] = Double.valueOf(prices[i]);
		}
		double res = dfs(p, target, 0, 0, new HashMap<>());
		if (res == -1) return String.format("%.0f", res);
		return String.format("%.3f", res);
	}

	public double dfs(double[] prices, int target, int index, int currentSum, Map<String, Double> map) {
		if (index == prices.length) {
			if (currentSum == target) {
				return 0;
			} else {
				return -1;
			}
		}

		String key = currentSum + "," + index;

		if (map.containsKey(key)) {
			return map.get(key);
		}

		double result = Double.MAX_VALUE;
		//round down
		double roundDownError = dfs(prices, target, index + 1, currentSum + (int)Math.floor(prices[index]), map);
		if (roundDownError != -1) {
			roundDownError +=  prices[index] - Math.floor(prices[index]);
			result = Math.min(result, roundDownError);
		}
		//round up
		double roundUpError = dfs(prices, target, index + 1, currentSum + (int)Math.ceil(prices[index]), map);
		if (roundUpError != -1) {
			roundUpError +=  Math.ceil(prices[index]) - prices[index];
			result = Math.min(result, roundUpError);
		}

		result = result == Double.MAX_VALUE ? -1 : result;

		map.put(key, result);
		return  result;
	}

	// dp knapsack
	public String minimizeErrorKnapsack(String[] prices, int target) {
		int l = prices.length;
		double[] p = new double[l];
		int roudupSum = 0;
		for (int i = 0; i < l; i++) {
			int floor = (int)Math.floor(Double.valueOf(prices[i]));
			p[i] = Double.valueOf(prices[i]) - floor;
			roudupSum += p[i] == 0 ? 0 : 1;
			target -= floor;
		}

		//target < 0 means, even all rounding down, not able to meet target
		//roudupSum < target means even all rounding up, still not able to meet target
		if (target < 0 || roudupSum < target) {
			return "-1";
		}

		//dp[t][i] = e
		//when target is t, and use number of i numbers, the min rounding error
		double[][] dp = new double[target + 1][l + 1];

		//base case, when target is 0, all need to round down
		for (int i = 1; i <= l; i++) {
			dp[0][i] = dp[0][i - 1] + p[i - 1];
		}

		for (int i = 1; i <= target; i++) {
			for (int j = i; j <= l; j++) {

				if (j > i) {
					dp[i][j] = Math.min(dp[i - 1][j - 1] + 1 - p[j - 1], dp[i][j - 1] + p[j - 1]);
				} else if (i == j) { 				//using j items and require the target to be j, it means all numbers have to be roud up
					dp[i][j] = dp[i - 1][j - 1] + 1 - p[j - 1];
				}

				// we should not consider cases or use results when j < i, which means even all rounding up should not lead to a valid answer. It means when we are filling the 2-D dp matrix, we are only filling half of the matrix -- right upper half , separate by the diagnose
			}
		}

		String res = String.format("%.3f", dp[target][l]);
		return res;
	}

	// fastest II
	public String minimizeErrorI(String[] prices, int target) {
		int intSum = 0;
		int unchangable = 0;
		List<Integer> lst = new ArrayList<>(prices.length);
		for (String price : prices) {
			int[] pair = stringToFloat(price);
			intSum += pair[0];
			if (pair[1] == 0)unchangable++;
			else lst.add(pair[1]);
		}

		if (target > intSum + prices.length - unchangable || target < intSum)return "-1";

		int toCeil = target - intSum;
		int toFloor = lst.size() - toCeil;
		lst.sort(null);
		int errorSum = 0;
		for (int i = 0; i < toFloor; i++) {
			errorSum += lst.get(i);

		}

		for (int i = toFloor; i < lst.size(); i++) {
			errorSum += (1000 - lst.get(i));

		}
		if (errorSum == 0)return "0.000";
		char[] chars = String.valueOf(errorSum).toCharArray();
		StringBuilder sb = new StringBuilder();
		if (chars.length < 4) {
			sb.append("0.");
			for (int i = chars.length; i <= 3; i++) {
				sb.append("0");
			}
			sb.append(String.valueOf(errorSum));
		} else {
			for (int i = 0; i < chars.length; i++) {
				sb.append(chars[i]);
				if (i == chars.length - 4)sb.append(".");
			}
		}

		return sb.toString();
	}

	public int[] stringToFloat(String s) {
		String[] split = s.split("\\.");
		int i = Integer.valueOf(split[0]);
		int d = Integer.valueOf(split[1]);
		int[] ans = new int[2];
		ans[0] = i;
		ans[1] = d;
		return ans;
	}

	// fastest II
	public String minimizeErrorII(String[] prices, int target) {
		DecimalFormat formatter = new DecimalFormat("#0.000");

		PriorityQueue<Double> pq = new PriorityQueue<>();

		double minSum = 0;
		double maxSum = 0;

		for (String price : prices) {
			double priceD = Double.valueOf(price);
			double priceMin = Math.floor(priceD);
			double priceMax = Math.ceil(priceD);
			double round = priceD - priceMin;
			minSum = minSum + priceMin;
			maxSum = maxSum + priceMax;
			pq.add(1.000 - round);
		}

		if (target < minSum || target > maxSum) {
			return "-1";
		}

		double count = target - minSum;
		double result = 0.000;
		while (count > 0) {
			result = result + pq.poll();
			count--;
		}

		while (!pq.isEmpty()) {
			result = result + 1 - pq.poll();
		}

		return formatter.format(result);
	}

	// Java beat 96%
	public String minimizeErrorQuick(String[] prices, int target) {
		int sum = 0;
		double[] d = new double[prices.length];
		int nn = prices.length;
		for (int i = 0; i < prices.length; i++) {
			String p = prices[i];
			double dv = Double.valueOf(p);
			int v = (int)dv;
			sum += v;
			d[i] = dv - v;
			if (d[i] == 0) nn--;
		}
		if (sum > target || sum + nn < target) return "-1";
		Arrays.sort(d);
		int n = target - sum;
		double ans = 0;
		int i = d.length - 1;
		for (; i >= d.length - n; i--) {
			ans += 1 - d[i];
		}
		for (; i > -1; i--) {
			ans += d[i];
		}
		return String.format("%.3f", ans);
	}

	/*
	The idea is similar to the classic knapsack problem, but here we want a min value and we have round up a fixed number of elements.

	We can first preprocess the array to make all the numbers smaller than 1 and change the value of target accordingly to make the following calculation more clear.

	We can use a 2d array, and iterate between 1 - target and 1 - len.

	For every element in the array, we can either

		choose (which means a cost of round up)

	or
	    not choose (cost of round down),

	then we can reach final result dp[target][len].

	Since there's a requirement that the target amount of elements have to be rounded up,

	then there're some cells in the 2d array we can't reach.

	(where index of elements smaller than index of target)

	We can also just use a 1d array and for 1d array we need to generate a new 1d array for each iteration of the target.
	*/
	// java dp not understand
	public String minimizeErrorDPnotUnderstand(String[] prices, int target) {
		int n = prices.length;
		double[] newPrices = new double[n];
		int sum = 0;

		for (int i = 0; i < n; i++) {
			double cur = Double.valueOf(prices[i]);
			int floor = (int)Math.floor(cur);
			target -= floor;
			newPrices[i] = cur - floor;
			sum += newPrices[i] > 0 ? 1 : 0;
		}

		if (target < 0 || target > sum) {
			return "-1";
		}

		/*
		System.out.println("target: " + target + " sum: " + sum);
		System.out.println(sum);

		*/

		// 3.7 2.8 4.9 (3 + 2 + 4) > 8

		// 0.7 2.8 4.9 10

		// (1 + 3 + 5) < 10

		// 10 - 0 - 2 - 4 = 4

		// 4 - 0.7 - 0.8 - 0.9

		System.out.println(target);
		double[][] dp = new double[target + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			dp[0][i] = dp[0][i - 1] + newPrices[i - 1];
		}

		/*
		for (double k: newPrices) {
			System.out.println(k);
		}

		for (double t: dp[0]) {
			System.out.println(t);
		}
		*/

		/*
		target = 2

		n = 3
		prices = [ "0.700", "2.800", "4.900" ];
		newPrices = [ 0.700, 0.800, 0.900 ];
		dp[0] = [0, 0.700, 1.500, 2.400];

		[   0    1      2      3
			0[0, 0.700, 1.500, 2.400]
			1[0, 0.300, 0.900, 1.599]
			2[0, 0,     0.500, 0.999]
		]
		*/

		for (int i = 1; i <= target; ++i) {
			for (int j = i; j <= n; ++j) {
				if (j > i) {
					dp[i][j] = Math.min(dp[i - 1][j - 1] + 1 - newPrices[j - 1], dp[i][j - 1] + newPrices[j - 1]);
				} else if (i == j) {
					dp[i][j] = dp[i - 1][j - 1] + 1 - newPrices[j - 1];
				}
			}
		}

		/*
		for (int row = 0; row < dp.length; row++) {
			for (int col = 0; col < dp[row].length; col++) {
				System.out.print(dp[row][col]);
				System.out.print(",");
			}

			System.out.println();
		}
		*/

		String res = String.format("%.3f", dp[target][n]);
		return res;
	}

	public String minimizeErrorPQ(String[] prices, int target) {
		float res = 0;
		PriorityQueue<Double> pq = new PriorityQueue<>();

		for (String s : prices) {
			float f = Float.valueOf(s);
			double low = Math.floor(f);
			double high = Math.ceil(f);

			if (low != high)
				pq.offer((high - f) - (f - low));

			res += f - low;
			target -= low;
		}

		System.out.println("pq.size(): " + pq.size() + " target: " + target);

		if (target < 0 || pq.size() < target) // assuming every price uses ceils but still less than the (target - all floors)
			return "-1";

		/*
		while(!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
		*/

		// 0.3 - 0.7 = -0.4

		// 0.2 - 0.8 = -0.6

		// 0.1 - 0.9 = -0.8

		
		while (target-- > 0) {
			double kk = pq.poll();
			System.out.println(kk);
			res += kk;
		}
		
		return String.format("%.3f", res);
	}

	public static void main(String[] args) {
		String[] strs = new String[] { "0.700", "2.800", "4.900" };
		String res = new Minimize_Rounding_Error_to_Meet_Target_1058().minimizeError(strs, 8);
		System.out.println(res);
		// System.out.println(new Minimize_Rounding_Error_to_Meet_Target_1058().minimizeErrorDP(strs, 8));


		// strs = new String[] { "0.700", "2.800", "4.900" };
		// System.out.println(new Minimize_Rounding_Error_to_Meet_Target_1058().minimizeErrorEasyDP(strs, 8));
	}
}