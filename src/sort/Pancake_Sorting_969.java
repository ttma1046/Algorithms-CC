package sort;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Pancake_Sorting_969 {
	public List<Integer> pancakeSort(int[] A) {
		List<Integer> result = new ArrayList<>();

		int N = A.length, largest = N;

		for (int i = 0; i < N; i++) {
			int index = find(A, largest);
			flip(A, index);
			flip(A, largest - 1);

			result.add(index + 1);
			result.add(largest--);
		}

		return result;
	}

	private int find(int[] A, int target) {
		for (int i = 0; i < A.length; i++) 
			if (A[i] == target)
				return i;

		return -1;
	}

	private void flip(int[] A, int index) {
		int i = 0;
		int j = index;
		while(i < j) {
			int temp = A[i];
			A[i++] = A[j];
			A[j--] = temp;
		}
	}

	public static void main(String[] args) {
		int a = 10006;
		int ones = a % 10;
		int tens = a % 100 / 10;
		String base62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		System.out.print(tens);
		System.out.print(ones);

		System.out.println(26 & 15);

		int b = 1350106;

		List<Integer> abc = new ArrayList<>();

		while(b != 0){
            abc.add(b % 62); 
            b /= 62;
        }

        Collections.reverse(abc);

        for (Integer k: abc) {
        	System.out.print(k);
        	System.out.print(",");
        }

	}
}