package hashtable;
import java.util.HashMap;
import java.util.Random;


class Maximum_occurring_element_with_equal_probability_gfg {

	public void findRandomIndexOfMax(int[] arr, int n) {
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


	public void findRandomIndexOfMax(int[] arr, int n) {
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

	public static void main(String[] args) {
		int arr[] = { -1, 4, 9, 7, 7, 2, 7, 3, 0, 9, 6, 5, 7, 8, 9 };
		int n = arr.length;
		new Maximum_occurring_element_with_equal_probability_gfg().findRandomIndexOfMax(arr, n);
	}
}