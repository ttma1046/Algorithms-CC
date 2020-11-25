package hashtable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/*
Find an index of maximum occurring element with equal probability

Given an array of integers, find the most occurring element of the array and return any one of its indexes randomly with equal probability.

Examples:

Input:
arr[] = [-1, 4, 9, 7, 7, 2, 7, 3, 0, 9, 6, 5, 7, 8, 9]

Output:
Element with maximum frequency present at index 6
OR
Element with maximum frequency present at Index 3
OR
Element with maximum frequency present at index 4
OR
Element with maximum frequency present at index 12

All outputs above have equal probability.
Recommended: Please try your approach on {IDE} first, before moving on to the solution.
The idea is to iterate through the array once and find out the maximum occurring element and its frequency n. Then we generate a random number r between 1 and n and finally return the r’th occurrence of maximum occurring element in the array.
*/

class Pair {
	int fre;
	ArrayList<Integer> indexArr;

	Pair(int x, ArrayList<Integer> y) {
		this.fre = x;
		this.indexArr = y;
	}

	Pair() {
		this.fre = 0;
		this.indexArr = new ArrayList<Integer>();
	}
}

class Maximum_occurring_element_with_equal_probability_gfg {
	public int[] findRandomIndexOfMax(int arr[], int n) {
		Map<Integer, Pair> map = new HashMap<Integer, Pair>();

		Pair most = new Pair();
		for (int i = 0; i < n; i++) {
			Pair pair = map.getOrDefault(arr[i], new Pair());

			int fre = pair.fre + 1;
			ArrayList<Integer> indexArr = pair.indexArr;

			indexArr.add(i);

			Pair newPair = new Pair(fre, indexArr);

			map.put(arr[i], newPair);

			if (fre > most.fre) {
				most = newPair;
			}
		}

		ArrayList<Integer> mostFreqsArray = most.indexArr;
		int length = mostFreqsArray.size();

		int[] mostFreqs = new int[length];

		int j = 0;
		for (int i : mostFreqsArray) {
			mostFreqs[j++] = i;
		}

		return mostFreqs;

		// return mostFreqs[(new Random().nextInt(length)) % length];
	}

	public void findRandomIndexOfMaxII(int[] arr, int n) {
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
		int numberWithBiggestOccurance = Integer.MIN_VALUE;
		int biggestOccurance = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {

			int occurrance = hash.getOrDefault(arr[i], 0) + 1;

			if (occurrance > biggestOccurance) {
				biggestOccurance = occurrance;
				numberWithBiggestOccurance = arr[i];
			}

			hash.put(arr[i], occurrance);
		}

		int r = (int)(new Random().nextInt(biggestOccurance) % biggestOccurance);

		for (int i = 0, count = 0; i < n; i++) {
			if (arr[i] == numberWithBiggestOccurance) {
				if (count == r) {
					System.out.print("Element with maximum frequency present "
					                 + "at index " + i + "\n");
					break;
				}
				count++;
			}
		}
	}

	public ArrayList<Integer> findRandomIndexOfMaxIII(int[] arr, int n) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int mostOcc = 0;
		int mostNumber = Integer.MIN_VALUE;

		for (int i = 0; i < n; ++i) {
			int occu = map.getOrDefault(arr[i], 0) + 1;

			if (occu > mostOcc) {
				mostOcc = occu;
				mostNumber = arr[i];
			}

			map.put(arr[i], occu);
		}

		ArrayList<Integer> res = new ArrayList<Integer>();

		int randomNum = new Random().nextInt(mostOcc) % mostOcc;

		for(int i = 0, count = 0; i < n; i++) {
			if (arr[i] == mostNumber) {
				res.add(i);

				count++;
			}
		}

		return res;
	}


	public static void main(String[] args) {
		int arr[] = { -1, 4, 9, 7, 7, 2, 7, 3, 0, 9, 6, 5, 7, 8, 9 };
		int n = arr.length;
		// System.out.println(new Maximum_occurring_element_with_equal_probability_gfg().findRandomIndexOfMax(arr, n));

		ArrayList<Integer> res = new Maximum_occurring_element_with_equal_probability_gfg().findRandomIndexOfMaxIII(arr, n);
		// System.out.println(res);
		
		for (int i : res) {
			System.out.println(i);
		}
	}
}