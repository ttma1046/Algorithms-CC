package array;

public class Monotonic_896 {
    public static boolean isMonotonicMy(int[] A) {
        if (A == null || A.length <= 0) {
            return false;
        }

        boolean increase;

        increase = A[0] < A[1] ? true: false;

        for (int i = 1;i < A.length;i++) {
            if (A[i] > A[i + 1] && increase) {
                return false;
            }

            if (!increase && A[i] < A[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static boolean isMonotonic(int[] A) {
        return increasing(A) || decreasing(A);
    }

    public static boolean increasing(int[] A) {
        for (int i = 0; i < A.length - 1; ++i)
            if (A[i] > A[i+1]) return false;
        return true;
    }

    public static boolean decreasing(int[] A) {
        for (int i = 0; i < A.length - 1; ++i)
            if (A[i] < A[i+1]) return false;
        return true;
    }

    public static boolean isMonotonicII(int[] A) {
        int store = 0;
        for (int i = 0; i < A.length - 1; ++i) {
            int c = Integer.compare(A[i], A[i+1]);
            if (c != 0) {
                if (c != store && store != 0)
                    return false;
                store = c;
            }
        }

        return true;
    }

    public boolean isMonotonicIII(int[] A) {
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = 0; i < A.length - 1; ++i) {
            if (A[i] > A[i+1])
                increasing = false;
            if (A[i] < A[i+1])
                decreasing = false;
        }

        return increasing || decreasing;
    }

    public static void main(String[] args) {
        System.out.println(isMonotonic(new int[] {1,2,2,3}));
    }
}
