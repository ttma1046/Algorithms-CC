package dp;

class Fibonacci_Number_509 {
    public int fib(int N) {
        if (N == 0) {
            return 0;
        }
        
        int first = 0;
        int second = 1;

        for (int i = 2; i < N + 1; i++) {
            second = second + first;
            first = second - first;
        }

        return second;
    }
}