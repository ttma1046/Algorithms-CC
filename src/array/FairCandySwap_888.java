class FairCandySwap_888 {
    public int[] fairCandySwap(int[] A, int[] B) {
        if (A.length <= 0 || A == null || B.length <= 0 || B == null) return null;
        int [] result = new int[2];
        
        int sumA = 0;
        int sumB = 0;

        for (int i = 0, j = 0; i < A.length, j < B.length; i++, j++) {
            sumA += A[i];
            sumB += B[j];

            if (Math.abs(sumA - sumB) / 2 == (A[i] - B[j])) {
                result.add(A[i]);
                result.add(B[j]);
            }
        }
        
        return result;
    }
}