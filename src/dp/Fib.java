package dp;

public class Fib {
    static int fib(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 0;
        } else if (n == 2) {
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
        }  else if (mem[n - 1] > 0) { // if (exist(mem, n))
            return mem[n - 1];
        }

        int f = fib(n - 1, mem) + fib( n - 2, mem);
        mem[n] = f;
        return f;
    } // O(N)

    public static void main(String[] args) {
        System.out.println(fib(6));
    }
}

class Program {
    public static int getNthFib(int n) {
        int mem [] = new int[n];

        return getNthFibWithMem(n - 1, mem);
    }

    private static int getNthFibWithMem(int n, int [] mem) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (mem[n] > 0) {
            return mem[n];
        }

        int f = getNthFibWithMem(n - 1, mem) + getNthFibWithMem(n - 2, mem);
        mem[n] = f;
        return f;
    }

    public static void main(String[] args) {
        System.out.println(getNthFib(6));
    }
}
/*
                                                       fib(7)

                                    fib(6)                               fib(5)

                     fib(5)                      fib(4)             fib(4)           fib(3)

                fib(4)       fib(3)         fib(3)    fib(2)      fib(3) fib(2)    fib(2)  fib(1)

            fib(3) fib(2) fib(2) fib(1) fib(2) fib(1)          fib(2) fib(1)

*/
