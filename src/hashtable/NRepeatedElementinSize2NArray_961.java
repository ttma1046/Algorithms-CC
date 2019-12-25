package hashtable;

import java.util.HashSet;
import java.util.Set;

public class NRepeatedElementinSize2NArray_961 {
    public int repeatedNTimes(int[] A) {
        if (A.length <= 0) { return 0; }

        for (int i = 2; i < A.length; ++i)
            if (A[i] == A[i - 1] || A[i] == A[i - 2])
                return A[i];
        return A[0];

        /*
        Set<Integer> mySet = new HashSet<Integer>();
        for (int a: A) {
            if (mySet.contains(a)) {
                return a;
            } else {
                mySet.add(a);
            }
        }

        return -1;
        */
    }

    public static void main(String[] args) {
        System.out.println(new NRepeatedElementinSize2NArray_961().repeatedNTimes(new int[]{2,1,2,5,3,2}));

        System.out.println(new NRepeatedElementinSize2NArray_961().repeatedNTimes(new int[]{5,1,5,2,5,3,5,4}));

        System.out.println(new NRepeatedElementinSize2NArray_961().repeatedNTimes(new int[]{1,2,3,3}));
    }
}
