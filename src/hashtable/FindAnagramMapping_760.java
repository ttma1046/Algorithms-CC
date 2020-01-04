package hashtable;

import java.util.HashMap;
import java.util.Map;

public class FindAnagramMapping_760 {
    public int[] anagramMappings(int[] A, int[] B) {
        if (A.length <= 0 || B.length <= 0) {
            return null;
        }

        Map<Integer, Integer> count2 = new HashMap<Integer, Integer>();
        for (int i = 0; i < B.length; i++) {
            count2.put(B[i], i);
        }

        int[] result = new int[A.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = count2.get(A[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] result = new FindAnagramMapping_760().anagramMappings(new int[] {12, 28, 46, 32, 50}, new int[] { 50, 12, 32, 46, 28 });

        for (int i: result) {
            System.out.println(i);
        }

    }
}
