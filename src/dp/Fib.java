package dp;

public class Fib {
    int fib(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    } // O(2^N)

    int fib(int n, int[] mem) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (mem[n] > 0) { // if (exist(mem, n))
            return mem[n];
        }

        return fib(n - 1, mem) + fib( n - 2, mem);
    } // O(N)
}

/*
                                                       fib(7)

                                    fib(6)                               fib(5)

                     fib(5)                      fib(4)             fib(4)           fib(3)

                fib(4)       fib(3)         fib(3)    fib(2)      fib(3) fib(2)    fib(2)  fib(1)

            fib(3) fib(2) fib(2) fib(1) fib(2) fib(1)          fib(2) fib(1)

*/
