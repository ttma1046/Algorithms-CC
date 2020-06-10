package recursion;

import java.util.HashMap;

public class Fib {
    static int fib(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    } // O(2^N)

    int fib(int n, int[] memo) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        if (memo[n] > 0) { // if (exist(memo, n))
            return memo[n];
        }

        if (memo[n] == 0) {
            memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        }

        return memo[n];
    } // O(N)

    public int getNthFib(int n) {
        int mem[] = new int[n];                                                               

        return getNthFibWithMem(n - 1, mem);
    }

    private  int getNthFibWithMem(int n, int[] mem) {
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

    int fibonacci(int n) {
        if (n == 0)
            return 0;
        int a = 0;
        int b = 1;
        // int temp = 0;
        for (int i = 2; i < n; i++) {
            /* temp = a + b;
            a = b;
            b = temp; */
            
            b = b - a;            
            a = b + a;
            b = 2 * a - b;
        }

        return a + b;
    }
    /*
        * fib(7)
        * 
        * fib(6) fib(5)
        * 
        * fib(5) fib(4) fib(4) fib(3)
        * 
        * fib(4) fib(3) fib(3) fib(2) fib(3) fib(2) fib(2) fib(1)
        * 
        * fib(3) fib(2) fib(2) fib(1) fib(2) fib(1) fib(2) fib(1)
        * 
        */

        
    public static void main(String[] args) {
        System.out.println(new Fib().fibonacci(0));
        System.out.println(new Fib().fibonacci(1));
        System.out.println(new Fib().fibonacci(2));
        System.out.println(new Fib().fibonacci(3));
        System.out.println(new Fib().fibonacci(4));
        System.out.println(new Fib().fibonacci(5));
        System.out.println(new Fib().fibonacci(6));
    }
}

class Fact {
    private static int mem[] = new int[100];
    private static HashMap<Integer, Integer> myHash = new HashMap<Integer, Integer>();

    private static int getNthFactWithMem(int n) {
        if (n <= 1) {
            return 1;
        } else if (mem[n] > 0) {
            System.out.println("return from memo");
            return mem[n];
        }

        mem[n] = n * getNthFactWithMem(n - 1);

        return mem[n];
    }

    private static int getNthFactWithHashMap(int n) {
        if (n <= 1) {
            return 1;
        } else if (myHash.containsKey(n)) {
            System.out.println("return from memo");
            return myHash.get(n);
        }

        int result = n * getNthFactWithHashMap(n - 1);
        myHash.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        // System.out.println(getNthFactWithMem(6));
        // System.out.println(getNthFactWithMem(5));
        System.out.println(getNthFactWithHashMap(5));
        System.out.println(getNthFactWithHashMap(6));
    }
}