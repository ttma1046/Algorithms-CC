class Solution {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        if (A == null || queries == null) {
            return null;
        }

        int finalResult = 0;
        for (int j = 0; j < A.length; j++) {
            if (A[j] % 2 == 0) {
                finalResult += A[j];
            }
        }

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; ++i) {
            int val = queries[i][0], index = queries[i][1];

            if (A[index] % 2 == 0) {
                finalResult -= A[index];
            }

            A[index] += val;

            if (A[index] % 2 == 0)
            {
                finalResult += A[index];
            }


            result[i] = finalResult;
        }

        return result;
    }
}