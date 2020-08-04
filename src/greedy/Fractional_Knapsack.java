package greedy;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.text.DecimalFormat;

class ItemValue implements Comparable<ItemValue> {
	double cost;
	int wt; 
	double val;

	// item value function
	public ItemValue(int wt, int val) {
		this.wt = wt;
		this.val = val;
		this.cost = val / (double)wt;
	}

	@Override
	public int compareTo(ItemValue p) {
		// TODO Auto-generated method stub
		return Double.compare(p.cost, this.cost);
	}
}

class Fractional_Knapsack {
	public double FractionalKnapsack(int[][] bags, int weight) {

		ItemValue[] iVal = new ItemValue[bags.length]; 

		for (int i = 0; i < bags.length; i++) {
			iVal[i] = new ItemValue(bags[i][1], bags[i][0]);
		}

		Arrays.sort(iVal);
			
		double result = 0d;
		int j = weight;

		for (ItemValue item: iVal) {
            int curWt = (int) item.wt; 
            int curVal = (int) item.val; 
			if (j - curWt >= 0) {
				j -= curWt;
				result += curVal;
			} else {
				double fraction = ((double)j / (double)curWt);
				result += (curVal * fraction);
				break;
			}
		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bf.readLine());

		while (t-- > 0) {
			String[] lineOne = bf.readLine().trim().split(" ");

			int bagsLength = Integer.parseInt(lineOne[0]);

			int weigth = Integer.parseInt(lineOne[1]);

			String[] lineTwo = bf.readLine().trim().split(" ");

			int[][] bags = new int[bagsLength][2];
			for (int i = 0; i < bagsLength; i++) {
				bags[i] = new int[] { Integer.parseInt(lineTwo[i * 2]), Integer.parseInt(lineTwo[i * 2 + 1]) };
			}

			double result = new Fractional_Knapsack().FractionalKnapsack(bags, weigth);
			StringBuffer sb = new StringBuffer();
			DecimalFormat db = new DecimalFormat("#.00");
			sb.append(db.format(result) + " ");
			System.out.println(sb);
		}
	}
}