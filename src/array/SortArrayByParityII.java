class Solution {
    public int[] sortArrayByParity(int[] A) {
        int length = A.length;
        int[] ans = new int[length];

        int t = 0;
        for (int x: A) if (x % 2 == 0) {
            ans[t] = x;
            t += 2;
        }

        t = 1;
        for (int x: A) if (x % 2 == 1) {
            ans[t] = x;
            t += 2;
        }

        return ans;
    }

    public int[] sortArrayByParityII(int[] A) {
        if (A == null || A.length <= 0) {
            return null;
        }

        int length = A.length;

        if (length < 2) { return A; }

        int i = 0, j = 1;

        while(i < A.length) {
            if (A[i] % 2 == 0) {
                i += 2;
            } else {
                while(j < A.length && A[j] % 2 == 1) {
                    j += 2;
                }

                swap(A, i, j);
                i += 2;
            }
        }
    }

    public int[] sortArrayByParityIII(int[] A) {

        if (A == null || A.length <= 0) {
            return null;
        }

        int j = 1;
        for (int i = 0; i < A.length; i += 2)
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1)
                    j += 2;

                // Swap A[i] and A[j]
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

        return A;
    }

    public int[] sortArrayByParityIV(int[] A) {

        if (A == null || A.length <= 0) {
            return null;
        }

        int i = 0, j = 1, n = A.length;
        while (i < n && j < n) {
            while (i < n && A[i] % 2 == 0) {
                i += 2;
            }
            while (j < n && A[j] % 2 == 1) {
                j += 2;
            }
            if (i < n && j < n) {
                swap(A, i, j);
            }
        }
        return A;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}