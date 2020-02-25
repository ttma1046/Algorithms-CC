package resursion;

class RecursiveMultiply_8point5 {
    int minProduct(int a, int b) {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;

        return minProductHelper(smaller, bigger);
    }

    int minProductHelper(int smaller, int bigger) {
        if (smaller == 0)
            return 0;
        if (smaller == 1)
            return bigger;

        int s = smaller >> 1;
        int side1 = minProduct(s, bigger);
        int side2 = side1;
        if (smaller % 2 == 1) {
            side2 = minProductHelper(smaller - s, bigger);
        }

        return side1 + side2;
    }

    int minProductII(int a, int b) {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;
        int memo[] = new int[smaller];

        return minProductHelper(smaller, bigger, memo);
    }

    int minProductHelper(int smaller, int bigger, int[] memo) {
        if (smaller == 0)
            return 0;
        if (smaller == 1)
            return bigger;
        if (memo[smaller] > 0)
            return memo[smaller];

        int s = smaller >> 1;
        int side1 = minProductHelper(s, bigger, memo);
        int side2 = side1;
        if (smaller % 2 == 1) {
            side2 = minProductHelper(smaller - s, bigger, memo);
        }

        memo[smaller] = side2 + side1;
        return memo[smaller];
    }

    int minProductIII(int a, int b) {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;
        return minProductHelperIII(smaller, bigger);
    }

    int minProductHelperIII(int smaller, int bigger) {
        if (smaller == 0)
            return 0;

        else if (smaller == 0)
            return bigger;

        int s = smaller >> 1; // Divide by 2;
        int halfProd = minProductHelper(s, bigger);

        if (smaller % 2 == 0) {
            return halfProd + halfProd;
        } else {
            return halfProd + halfProd + bigger;
        }
    }

}