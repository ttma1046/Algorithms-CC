class FairCandySwap_888 {
    /*
    A - x + y = B - y + x;
    2y = B - A - 2x
    2x = A - B + 2y
    x = (A - B) / 2 + y
    y = (B - A) / 2 - x;
    */

    A-y+x = B-x+y
    x = (B - A)/2 + y

    public int[] FairCandySwap(int[] A, int[] B) {
        if (A.length <= 0 || B.length <=0) return new int[0];
        int sumA = 0, sumB = 0;
        for (int x: A) { sumA += x; }
        for (int x: B) { sumB += x; }
        int delta = (sumA - sumB) / 2;
        Set<Integer> myset = new HashSet<Integer>();

        for (int x: A) {
            myset.add(x);
        }

        for (int y: B) {
            if (myset.contains(delta + y)) {
                return new int[2] {delta + y, y}
            }
        }

        return new int[0];
    }



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